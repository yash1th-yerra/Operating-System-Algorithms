import java.util.ArrayList;
import java.util.List;

public class MultilevelFeedbackQueueScheduler {
    private List<Queue> queues;
    private List<Process> allProcesses;
    private int currentTime;
    private int idleTime;
    private static final int AGING_THRESHOLD = 5;
    private static final int MAX_QUEUE_LEVELS = 3;

    public MultilevelFeedbackQueueScheduler() {
        this.queues = new ArrayList<>();
        this.allProcesses = new ArrayList<>();
        this.currentTime = 0;
        this.idleTime = 0;

        queues.add(new Queue(0, 4, true));   // Highest priority - Round Robin
        queues.add(new Queue(1, 8, true));   // Medium priority - Round Robin
        queues.add(new Queue(2, 16, false)); // Lowest priority - FCFS
    }

    public void addProcess(int pid, int initialQueueLevel, int arrivalTime, int burstTime) {
        Process p = new Process(pid, initialQueueLevel, arrivalTime, burstTime);
        allProcesses.add(p);
    }

    private void applyAgingMechanism() {
        for (Queue queue : queues) {
            for (Process p : queue.getProcesses()) {
                p.incrementAgingCounter();
                if (p.getAgingCounter() >= AGING_THRESHOLD && p.getQueueLevel() > 0) {
                    int newLevel = p.getQueueLevel() - 1;
                    p.setQueueLevel(newLevel);
                    p.resetAgingCounter();
                }
            }
        }
    }

    private void updateQueues() {
        for (Process p : allProcesses) {
            if (p.getArrivalTime() <= currentTime && p.getRemainingTime() > 0) {
                boolean found = false;
                for (Queue q : queues) {
                    if (q.getProcesses().contains(p)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    queues.get(p.getQueueLevel()).addProcess(p);
                }
            }
        }

        if (currentTime % 10 == 0) {
            applyAgingMechanism();
        }
    }

    public void schedule() {
        int completedProcesses = 0;

        while (completedProcesses < allProcesses.size()) {
            updateQueues();
            boolean processed = false;

            for (Queue queue : queues) {
                if (!queue.isEmpty()) {
                    Process currentProcess = queue.removeProcess();

                    if (!currentProcess.isStarted()) {
                        currentProcess.setStarted(true);
                        currentProcess.setResponseTime(currentTime - currentProcess.getArrivalTime());
                    }

                    int executionTime = queue.isPreemptive()
                            ? Math.min(queue.getTimeQuantum(), currentProcess.getRemainingTime())
                            : currentProcess.getRemainingTime();

                    currentProcess.setRemainingTime(currentProcess.getRemainingTime() - executionTime);
                    currentTime += executionTime;
                    processed = true;

                    if (currentProcess.getRemainingTime() > 0) {
                        int currentLevel = currentProcess.getQueueLevel();
                        if (currentLevel < MAX_QUEUE_LEVELS - 1) {
                            queues.get(currentLevel + 1).addProcess(currentProcess);
                            currentProcess.setQueueLevel(currentLevel + 1);
                        }
                    } else {
                        currentProcess.setCompletionTime(currentTime);
                        currentProcess.setTurnaroundTime(
                                currentProcess.getCompletionTime() - currentProcess.getArrivalTime());
                        currentProcess.setWaitingTime(
                                currentProcess.getTurnaroundTime() - currentProcess.getBurstTime());
                        completedProcesses++;
                    }
                    break;
                }
            }

            if (!processed) {
                currentTime++;
                idleTime++;
            }
        }

        displayResults();
    }

    private void displayResults() {
        System.out.println("\nProcess Execution Results:");
        System.out.println("PID\tArrival\tBurst\tCompletion\tTurnaround\tWaiting\tResponse");
        for (Process p : allProcesses) {
            System.out.printf("%d\t%d\t\t%d\t\t%d\t\t\t%d\t\t\t%d\t\t%d\n",
                    p.getPid(),
                    p.getArrivalTime(),
                    p.getBurstTime(),
                    p.getCompletionTime(),
                    p.getTurnaroundTime(),
                    p.getWaitingTime(),
                    p.getResponseTime());
        }

        double avgTurnaroundTime = allProcesses.stream().mapToDouble(Process::getTurnaroundTime).average().orElse(0.0);
        double avgWaitingTime = allProcesses.stream().mapToDouble(Process::getWaitingTime).average().orElse(0.0);
        double cpuUtilization = ((double) (currentTime - idleTime) / currentTime) * 100;

        System.out.printf("\nAverage Turnaround Time: %.2f\n", avgTurnaroundTime);
        System.out.printf("Average Waiting Time: %.2f\n", avgWaitingTime);
        System.out.printf("CPU Utilization: %.2f%%\n", cpuUtilization);
        System.out.println("Total Idle Time: " + idleTime);
    }
}
