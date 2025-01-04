public class ScheduleApp {
    public static void main(String[] args) {
        MultilevelFeedbackQueueScheduler scheduler = new MultilevelFeedbackQueueScheduler();

        scheduler.addProcess(1, 0, 0, 10);
        scheduler.addProcess(2, 0, 1, 8);
        scheduler.addProcess(3, 1, 2, 6);
        scheduler.addProcess(4, 2, 3, 4);

        scheduler.schedule();
    }
}