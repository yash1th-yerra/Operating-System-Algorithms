public class Process {
    private int pid;
    private int priority;
    private int arrivalTime;
    private int burstTime;
    private int remainingTime;
    private int completionTime;
    private int turnaroundTime;
    private int waitingTime;
    private int responseTime;
    private boolean started;

    public Process(int pid, int priority, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.started = false;
    }

    // Getters and setters
    public int getPid() { return pid; }
    public int getPriority() { return priority; }
    public int getArrivalTime() { return arrivalTime; }
    public int getBurstTime() { return burstTime; }
    public int getRemainingTime() { return remainingTime; }
    public void setRemainingTime(int time) { this.remainingTime = time; }
    public int getCompletionTime() { return completionTime; }
    public void setCompletionTime(int time) { this.completionTime = time; }
    public int getTurnaroundTime() { return turnaroundTime; }
    public void setTurnaroundTime(int time) { this.turnaroundTime = time; }
    public int getWaitingTime() { return waitingTime; }
    public void setWaitingTime(int time) { this.waitingTime = time; }
    public int getResponseTime() { return responseTime; }
    public void setResponseTime(int time) { this.responseTime = time; }
    public boolean isStarted() { return started; }
    public void setStarted(boolean started) { this.started = started; }
}