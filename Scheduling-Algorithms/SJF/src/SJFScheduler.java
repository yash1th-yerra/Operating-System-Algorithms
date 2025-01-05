import java.util.*;

public class SJFScheduler {

    private List<Process> processes;
    private boolean isPreemptive;
    private int currentTime;

    public SJFScheduler( boolean isPreemptive) {
        this.processes = new ArrayList<>();
        this.isPreemptive = isPreemptive;
        this.currentTime = 0;
    }

    public void addProcess(int pid, String id, int arrivalTime, int burstTime) {
        processes.add(new Process(pid, id, arrivalTime, burstTime));
    }

    private Process findShortestJob(List<Process> readyQueue) {
        return Collections.min(readyQueue);
    }


    public  void schedule(){
        List<Process> completedProcesses = new ArrayList<>();
        List<Process> readyQueue = new ArrayList<>();
        Map<Process,Boolean> started = new HashMap<>();


        while(completedProcesses.size()<processes.size()){
            for(Process p: processes){
                if(p.getArrivalTime() <= currentTime && p.getRemainingTime() > 0 && !readyQueue.contains(p)){
                    readyQueue.add(p);
                }
            }

            if(readyQueue.isEmpty()){
                currentTime++;
                continue;
            }

            Process currentProcess = findShortestJob(readyQueue);

            if(!started.containsKey(currentProcess)){
                started.put(currentProcess,true);
                currentProcess.setResponseTime(currentTime- currentProcess.getArrivalTime());
            }

            if (isPreemptive) {
                currentProcess.setRemainingTime(currentProcess.getRemainingTime() - 1);
                currentTime++;

                if (currentProcess.getRemainingTime() == 0) {
                    completeProcess(currentProcess, completedProcesses, readyQueue);
                }
            } else {
                currentTime += currentProcess.getRemainingTime();
                currentProcess.setRemainingTime(0);
                completeProcess(currentProcess, completedProcesses, readyQueue);
            }

        }

        displayResults();



    }

    private void completeProcess(Process process, List<Process> completed, List<Process> ready) {
        process.setCompletionTime(currentTime);
        process.setTurnaroundTime(process.getCompletionTime() - process.getArrivalTime());
        process.setWaitingTime(process.getTurnaroundTime() - process.getBurstTime());
        completed.add(process);
        ready.remove(process);
    }


    private void displayResults() {
        System.out.println("\nProcess Scheduling Details (" +
                (isPreemptive ? "Preemptive" : "Non-preemptive") + " SJF):");
        System.out.println("PID\tID\tArrival\tBurst\tCompletion\tTurnaround\tWaiting\tResponse");

        double totalTurnaround = 0, totalWaiting = 0, totalResponse = 0;

        for (Process p : processes) {
            System.out.printf("%d\t%s\t%d\t%d\t%d\t\t%d\t\t%d\t%d\n",
                    p.getPid(), p.getId(), p.getArrivalTime(), p.getBurstTime(),
                    p.getCompletionTime(), p.getTurnaroundTime(),
                    p.getWaitingTime(), p.getResponseTime());

            totalTurnaround += p.getTurnaroundTime();
            totalWaiting += p.getWaitingTime();
            totalResponse += p.getResponseTime();
        }

        int n = processes.size();
        System.out.println("\nScheduling Metrics:");
        System.out.printf("Average Turnaround Time: %.2f\n", totalTurnaround / n);
        System.out.printf("Average Waiting Time: %.2f\n", totalWaiting / n);
        System.out.printf("Average Response Time: %.2f\n", totalResponse / n);
        System.out.printf("Throughput: %.2f processes/unit time\n",
                (double) n / currentTime);
    }

    public static void main(String[] args) {
        // Test both non-preemptive and preemptive SJF
        SJFScheduler nonPreemptive = new SJFScheduler(false);
        nonPreemptive.addProcess(0, "P1", 0, 7);
        nonPreemptive.addProcess(1, "P2", 2, 4);
        nonPreemptive.addProcess(2, "P3", 4, 1);
        nonPreemptive.addProcess(3, "P4", 5, 4);
        nonPreemptive.schedule();

        SJFScheduler preemptive = new SJFScheduler(true);
        preemptive.addProcess(0, "P1", 0, 7);
        preemptive.addProcess(1, "P2", 2, 4);
        preemptive.addProcess(2, "P3", 4, 1);
        preemptive.addProcess(3, "P4", 5, 4);
        preemptive.schedule();
    }



}
