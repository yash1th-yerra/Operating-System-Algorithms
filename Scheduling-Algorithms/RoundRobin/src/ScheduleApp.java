public class ScheduleApp {
    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler(2); // Time quantum = 2

        scheduler.addProcess(0, "P1", 0, 5);
        scheduler.addProcess(1, "P2", 1, 4);
        scheduler.addProcess(2, "P3", 2, 2);
        scheduler.addProcess(3, "P4", 4, 1);

        scheduler.schedule();
    }
}
