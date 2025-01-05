import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityScheduler {

    private List<Process> processes;
    private int currentTime;
    private int idleTime;


    public PriorityScheduler(){
        this.processes = new ArrayList<>();
        this.currentTime=0;
        this.idleTime=0;
    }

    public void addProcess(int pid,int priority,int arrivalTime,int burstTime){
        processes.add(new Process(pid,priority,arrivalTime,burstTime));
    }

    private Process getHighestPriorityProcess(List<Process> readyQueue){
        return Collections.min(readyQueue,(p1,p2) -> Integer.compare(p1.getPriority(),p2.getPriority()));

    }


    public void schedule(){
        List<Process> readyQueue = new ArrayList<>();
        List<Process> completed = new ArrayList<>();
        while(completed.size()<processes.size()){
            for(Process p: processes){
                if(p.getArrivalTime()<=currentTime && p.getRemainingTime()>0 && !readyQueue.contains(p)){
                    readyQueue.add(p);
                }
            }

            if(readyQueue.isEmpty()){
                currentTime++;
                idleTime++;
                continue;
            }


            Process currentProcess = getHighestPriorityProcess(readyQueue);

            if(!currentProcess.isStarted()){
                currentProcess.setStarted(true);
                currentProcess.setResponseTime(currentTime-currentProcess.getArrivalTime());
            }
            currentProcess.setRemainingTime(currentProcess.getRemainingTime()-1);
            currentTime++;

            if(currentProcess.getRemainingTime()==0){
                currentProcess.setCompletionTime(currentTime);
                currentProcess.setTurnaroundTime(currentProcess.getCompletionTime()-currentProcess.getArrivalTime());
                currentProcess.setWaitingTime(currentProcess.getTurnaroundTime()-currentProcess.getBurstTime());
                completed.add(currentProcess);
                readyQueue.remove(currentProcess);
            }
        }
        displayResults();
    }
    private void displayResults() {
        System.out.println("\nPreemptive Priority Scheduling Results:");
        System.out.println("PID\tPriority\tArrival\t\tBurst\tCompletion\tTurnaround\tWaiting\tResponse");

        double totalTurnaround = 0, totalWaiting = 0, totalResponse = 0;
        int maxCompletion = 0;

        for (Process p : processes) {
            System.out.printf("%d\t%d\t\t\t%d\t\t\t%d\t\t\t%d\t\t%d\t\t\t%d\t\t%d\n",
                    p.getPid(), p.getPriority(), p.getArrivalTime(), p.getBurstTime(),
                    p.getCompletionTime(), p.getTurnaroundTime(),
                    p.getWaitingTime(), p.getResponseTime());

            totalTurnaround += p.getTurnaroundTime();
            totalWaiting += p.getWaitingTime();
            totalResponse += p.getResponseTime();
            maxCompletion = Math.max(maxCompletion, p.getCompletionTime());
        }

        int n = processes.size();
        double cpuUtilization = ((currentTime - idleTime) / (double) currentTime) * 100;
        double throughput = (double) n / maxCompletion;

        System.out.println("\nPerformance Metrics:");
        System.out.printf("Average Turnaround Time: %.2f\n", totalTurnaround / n);
        System.out.printf("Average Waiting Time: %.2f\n", totalWaiting / n);
        System.out.printf("Average Response Time: %.2f\n", totalResponse / n);
        System.out.printf("CPU Utilization: %.2f%%\n", cpuUtilization);
        System.out.printf("Throughput: %.2f processes/unit time\n", throughput);
    }



}
