package schedulingalgos.fcfs;

import java.util.List;

public class SchedulerMetrics {
    private List<Process> processes;

    public SchedulerMetrics(List<Process> processes) {
        this.processes = processes;
    }

    public void calculateAndDisplayMetrics() {
        double totalTurnaroundTime = 0;
        double totalWaitingTime = 0;
        double totalResponseTime = 0;
        int maxCompletionTime = 0;

        for (Process p : processes) {
            totalTurnaroundTime += p.getTurnaroundTime();
            totalWaitingTime += p.getWaitingTime();
            totalResponseTime += p.getResponseTime();
            maxCompletionTime = Math.max(maxCompletionTime, p.getCompletionTime());
        }

        int n = processes.size();
        double throughput = (double) n / maxCompletionTime;

        // Display process details
        System.out.println("\nProcess Scheduling Details:");
        System.out.println("PID\tID\tArrival\tBurst\tCompletion\tTurnaround\tWaiting\tResponse");
        for (Process p : processes) {
            System.out.printf("%d\t%s\t%d\t\t%d\t\t%d\t\t\t%d\t\t\t%d\t\t%d\n",
                    p.getPid(), p.getId(), p.getArrivalTime(), p.getBurstTime(),
                    p.getCompletionTime(), p.getTurnaroundTime(),
                    p.getWaitingTime(), p.getResponseTime());
        }

        // Display metrics
        System.out.println("\nScheduling Metrics:");
        System.out.printf("Average Turnaround Time: %.2f\n", totalTurnaroundTime / n);
        System.out.printf("Average Waiting Time: %.2f\n", totalWaitingTime / n);
        System.out.printf("Average Response Time: %.2f\n", totalResponseTime / n);
        System.out.printf("Throughput: %.2f processes/unit time\n", throughput);
    }
}