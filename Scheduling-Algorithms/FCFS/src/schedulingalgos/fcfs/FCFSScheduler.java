package schedulingalgos.fcfs;

import schedulingalgos.fcfs.CPUDispatcher;
import schedulingalgos.fcfs.SchedulerMetrics;

import java.util.ArrayList;
import java.util.List;

public class FCFSScheduler {
    private List<Process> processes;
    private CPUDispatcher dispatcher;
    private SchedulerMetrics metrics;

    public FCFSScheduler() {
        this.processes = new ArrayList<>();
        this.dispatcher = new CPUDispatcher();
    }

    public void addProcess(int pid, String id, int arrivalTime, int burstTime) {
        processes.add(new Process(pid, id, arrivalTime, burstTime));
    }

    public void schedule() {
        // Sort by arrival time
        processes.sort((a, b) -> a.getArrivalTime() - b.getArrivalTime());

        int currentTime = 0;

        for (Process process : processes) {
            if (currentTime < process.getArrivalTime()) {
                currentTime = process.getArrivalTime();
            }

            process.setResponseTime(currentTime - process.getArrivalTime());
            process.setWaitingTime(currentTime - process.getArrivalTime());

            dispatcher.executeProcess(process);
            currentTime += process.getBurstTime();

            process.setCompletionTime(currentTime);
            process.setTurnaroundTime(currentTime - process.getArrivalTime());
        }

        metrics = new SchedulerMetrics(processes);
        metrics.calculateAndDisplayMetrics();
    }

    public static void main(String[] args) {
        FCFSScheduler scheduler = new FCFSScheduler();

        // Add test processes
        scheduler.addProcess(0, "P1", 0, 5);
        scheduler.addProcess(1, "P2", 1, 3);
        scheduler.addProcess(2, "P3", 2, 4);
        scheduler.addProcess(3, "P4", 4, 1);

        scheduler.schedule();
    }
}