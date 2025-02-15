package task.tasktypes;

import task.Task;
import static data.Constants.*;

public class Deadline extends Task {
    private final String deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
        super.taskType = TASK_TYPE_DEADLINE;
    }

    @Override
    public String getDeadline() { return deadline; }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", deadline);
    }
}