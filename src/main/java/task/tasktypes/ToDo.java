package task.tasktypes;

import task.Task;
import static data.Constants.*;

public class ToDo extends Task {
    /*
     * Instantiates parent Task class
     * @param task Parameters for parent Task constructor
     * @deadline The due date of this Task object
     */
    public ToDo(String task) {
        super(task);
        super.taskType = TASK_TYPE_TODO;
    }

    /*
     * Overrides the toString() method present in Task
     * @return A String representation of the task for printing
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
