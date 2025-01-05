public class ScheduleApp {
    public static void main(String[] args) {
        PriorityScheduler scheduler = new PriorityScheduler();

        // Test processes (pid, priority, arrival time, burst time)
        scheduler.addProcess(1, 2, 0, 4);
        scheduler.addProcess(2, 4, 1, 3);
        scheduler.addProcess(3, 1, 2, 5);
        scheduler.addProcess(4, 3, 3, 2);

        scheduler.schedule();
    }
}