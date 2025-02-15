package task.tasktypes;

import task.Task;
import static data.Constants.*;

public class Event extends Task {
    private final String startDate;
    private final String endDate;

    public Event(String task, String startDate, String endDate) {
        super(task);
        this.startDate = startDate;
        this.endDate = endDate;
        super.taskType = TASK_TYPE_EVENT;
    }

    @Override
    public String getStartDate() { return startDate; }

    @Override
    public String getEndDate() { return endDate; }

    @Override
    public String toString() {
        return super.toString()
            + String.format(" (from: %s,", startDate) + String.format(" to: %s)", endDate);
    }
}