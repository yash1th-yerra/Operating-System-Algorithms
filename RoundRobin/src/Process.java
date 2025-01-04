public class Process {
    private int pid;
    private String id;
    private int arrivalTime;
    private int burstTime;
    private int remainingTime;
    private int completionTime;
    private int turnaroundTime;
    private int waitingTime;
    private int responseTime;
    private boolean hasStarted;

    public Process(int pid, String id, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.hasStarted = false;
    }

    // Getters and setters
    public int getPid() { return pid; }
    public String getId() { return id; }
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
    public boolean hasStarted() { return hasStarted; }
    public void setStarted(boolean started) { this.hasStarted = started; }
}