package task.tasktypes;

import task.Task;

public class ToDo extends Task {
    // Constants
    private static final String TASK_TYPE = " [T]";

    public ToDo(String task) {
        super(task);
        super.taskType = TASK_TYPE;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
