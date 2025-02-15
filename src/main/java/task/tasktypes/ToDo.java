package task.tasktypes;

import task.Task;
import static data.Constants.*;

public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
        super.taskType = TASK_TYPE_TODO;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
