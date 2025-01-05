import java.util.*;

class Process {
    private int pid;
    private int queueLevel;
    private int arrivalTime;
    private int burstTime;
    private int remainingTime;
    private int completionTime;
    private int turnaroundTime;
    private int waitingTime;
    private int responseTime;
    private int agingCounter;
    private boolean started;

    public Process(int pid, int queueLevel, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.queueLevel = queueLevel;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.agingCounter = 0;
        this.started = false;
    }

    public int getPid() { return pid; }
    public int getQueueLevel() { return queueLevel; }
    public void setQueueLevel(int queueLevel) { this.queueLevel = queueLevel; }
    public int getArrivalTime() { return arrivalTime; }
    public int getBurstTime() { return burstTime; }
    public int getRemainingTime() { return remainingTime; }
    public void setRemainingTime(int remainingTime) { this.remainingTime = remainingTime; }
    public int getCompletionTime() { return completionTime; }
    public void setCompletionTime(int completionTime) { this.completionTime = completionTime; }
    public int getTurnaroundTime() { return turnaroundTime; }
    public void setTurnaroundTime(int turnaroundTime) { this.turnaroundTime = turnaroundTime; }
    public int getWaitingTime() { return waitingTime; }
    public void setWaitingTime(int waitingTime) { this.waitingTime = waitingTime; }
    public int getResponseTime() { return responseTime; }
    public void setResponseTime(int responseTime) { this.responseTime = responseTime; }
    public boolean isStarted() { return started; }
    public void setStarted(boolean started) { this.started = started; }
    public int getAgingCounter() { return agingCounter; }
    public void incrementAgingCounter() { this.agingCounter++; }
    public void resetAgingCounter() { this.agingCounter = 0; }
}