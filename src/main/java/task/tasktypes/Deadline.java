package task.tasktypes;

import task.Task;
import static data.Constants.*;
import java.time.LocalDate;

public class Deadline extends Task {
    //private final String deadline;
    private final LocalDate deadline;

    public Deadline(String task, LocalDate deadline /*String deadline*/) {
        super(task);
        this.deadline = deadline;
        super.taskType = TASK_TYPE_DEADLINE;
    }

    @Override
    public LocalDate dueDate() {
        return deadline;
    }

    @Override
    public String getDeadline() { return deadline.toString(); /*return deadline;*/ }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", deadline);
    }
}