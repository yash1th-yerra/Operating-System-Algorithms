import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoundRobinScheduler {
    private List<Process> processes;
    private int timeQuantum;
    private int currentTime;
    private int idleTime;

    public RoundRobinScheduler(int timeQuantum) {
        this.processes = new ArrayList<>();
        this.timeQuantum = timeQuantum;
        this.currentTime = 0;
        this.idleTime = 0;
    }

    public void addProcess(int pid, String id, int arrivalTime, int burstTime) {
        processes.add(new Process(pid, id, arrivalTime, burstTime));
    }

    public void schedule() {
        Queue<Process> readyQueue = new LinkedList<>();
        List<Process> completed = new ArrayList<>();

        while (completed.size() < processes.size()) {
            // Add newly arrived processes to ready queue
            for (Process p : processes) {
                if (p.getArrivalTime() <= currentTime && p.getRemainingTime() > 0
                        && !readyQueue.contains(p) && !completed.contains(p)) {
                    readyQueue.offer(p);
                }
            }

            if (readyQueue.isEmpty()) {
                currentTime++;
                idleTime++;
                continue;
            }

            Process currentProcess = readyQueue.poll();

            // Record response time for first execution
            if (!currentProcess.hasStarted()) {
                currentProcess.setStarted(true);
                currentProcess.setResponseTime(currentTime - currentProcess.getArrivalTime());
            }

            int executionTime = Math.min(timeQuantum, currentProcess.getRemainingTime());
            currentProcess.setRemainingTime(currentProcess.getRemainingTime() - executionTime);
            currentTime += executionTime;

            if (currentProcess.getRemainingTime() > 0) {
                readyQueue.offer(currentProcess);
            } else {
                currentProcess.setCompletionTime(currentTime);
                currentProcess.setTurnaroundTime(currentProcess.getCompletionTime() -
                        currentProcess.getArrivalTime());
                currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() -
                        currentProcess.getBurstTime());
                completed.add(currentProcess);
            }
        }

        displayMetrics();
    }

    private void displayMetrics() {
        System.out.println("\nRound Robin Scheduling (Time Quantum: " + timeQuantum + ")");
        System.out.println("PID\tID\tArrival\tBurst\tCompletion\tTurnaround\tWaiting\tResponse");

        double totalTurnaround = 0, totalWaiting = 0, totalResponse = 0, totalBurst = 0;
        int maxCompletion = 0;

        for (Process p : processes) {
            System.out.printf("%d\t%s\t%d\t%d\t%d\t\t%d\t\t%d\t%d\n",
                    p.getPid(), p.getId(), p.getArrivalTime(), p.getBurstTime(),
                    p.getCompletionTime(), p.getTurnaroundTime(),
                    p.getWaitingTime(), p.getResponseTime());

            totalTurnaround += p.getTurnaroundTime();
            totalWaiting += p.getWaitingTime();
            totalResponse += p.getResponseTime();
            totalBurst += p.getBurstTime();
            maxCompletion = Math.max(maxCompletion, p.getCompletionTime());
        }

        int n = processes.size();
        double cpuUtilization = ((currentTime - idleTime) / (double) currentTime) * 100;
        double throughput = (double) n / maxCompletion;
        double efficiency = (totalBurst / (currentTime * n)) * 100;

        System.out.println("\nPerformance Metrics:");
        System.out.printf("Average Turnaround Time: %.2f\n", totalTurnaround / n);
        System.out.printf("Average Waiting Time: %.2f\n", totalWaiting / n);
        System.out.printf("Average Response Time: %.2f\n", totalResponse / n);
        System.out.printf("CPU Utilization: %.2f%%\n", cpuUtilization);
        System.out.printf("Throughput: %.2f processes/unit time\n", throughput);
        System.out.printf("Scheduling Efficiency: %.2f%%\n", efficiency);
    }


}