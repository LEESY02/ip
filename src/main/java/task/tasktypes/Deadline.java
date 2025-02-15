package task.tasktypes;

import task.Task;

public class Deadline extends Task {
    private final String deadline;

    // Constants
    private static final String TASK_TYPE = " [D]";

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
        super.taskType = TASK_TYPE;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", deadline);
    }
}