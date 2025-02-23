package task.tasktypes;

import task.Task;

import java.time.LocalDate;

import static data.Constants.*;

public class Event extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Event(String task, LocalDate startDate, LocalDate endDate) {
        super(task);
        this.startDate = startDate;
        this.endDate = endDate;
        super.taskType = TASK_TYPE_EVENT;
    }

    @Override
    public LocalDate dueDate() {
        return endDate;
    }

    @Override
    public String getStartDate() { return startDate.toString(); }

    @Override
    public String getEndDate() { return endDate.toString(); }

    @Override
    public String toString() {
        return super.toString()
            + String.format(" (from: %s,", startDate) + String.format(" to: %s)", endDate);
    }
}