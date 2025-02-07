public class Event extends Task {
    private final String startDate;
    private final String endDate;
    private static final String TASK_TYPE = " [E]";

    public Event(String task, int index, String startDate, String endDate) {
        super(task, index);
        this.startDate = startDate;
        this.endDate = endDate;
        super.taskType = TASK_TYPE;
    }

    @Override
    public String toString() {
        return super.toString()/*.replaceFirst("\\[", "[E][")*/
            + String.format(" (from: %s,", startDate) + String.format(" to: %s)", endDate);
    }
}