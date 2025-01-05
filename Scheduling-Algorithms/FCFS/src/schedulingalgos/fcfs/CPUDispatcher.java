package schedulingalgos.fcfs;

public class CPUDispatcher {
    private Process currentProcess;

    public void executeProcess(Process process) {
        currentProcess = process;
        try {
            Thread.sleep(500); // Simulated execution
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean isIdle() {
        return currentProcess == null;
    }
}