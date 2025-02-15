package task.tasktypes;

import task.Task;

public class Event extends Task {
    private final String startDate;
    private final String endDate;

    // Constants
    private static final String TASK_TYPE = " [E]";

    public Event(String task, String startDate, String endDate) {
        super(task);
        this.startDate = startDate;
        this.endDate = endDate;
        super.taskType = TASK_TYPE;
    }

    @Override
    public String toString() {
        return super.toString()
            + String.format(" (from: %s,", startDate) + String.format(" to: %s)", endDate);
    }
}