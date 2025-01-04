import java.util.LinkedList;

public class Queue {
    private int level;
    private int timeQuantum;
    private LinkedList<Process> processes;
    private boolean isPreemptive;
    private int priorityBoostThreshold;

    public Queue(int level, int timeQuantum, boolean isPreemptive) {
        this.level = level;
        this.timeQuantum = timeQuantum;
        this.processes = new LinkedList<>();
        this.isPreemptive = isPreemptive;
        this.priorityBoostThreshold = 10; // Default priority boost threshold
    }

    public void addProcess(Process process) {
        processes.add(process);
    }

    public Process removeProcess() {
        return processes.pollFirst(); // Remove and return the first process
    }

    public boolean isEmpty() {
        return processes.isEmpty();
    }

    public LinkedList<Process> getProcesses() {
        return processes;
    }

    public void priorityBoost() {
        for (Process p : processes) {
            p.setQueueLevel(0); // Reset to highest priority
            p.resetAgingCounter();
        }
    }

    public int getTimeQuantum() {
        return timeQuantum;
    }

    public boolean isPreemptive() {
        return isPreemptive;
    }
}