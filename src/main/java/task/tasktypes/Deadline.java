package task.tasktypes;

import task.Task;
import static data.Constants.*;

public class Deadline extends Task {
    private final String deadline;

    /*
     * Instantiates parent Task class, and defines the deadline of the task
     * @param task Parameters for parent Task constructor
     * @deadline The due date of this Task object
     */
    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
        super.taskType = TASK_TYPE_DEADLINE;
    }

    /*
     * @return Returns deadline, used for saving
     */
    @Override
    public String getDeadline() { return deadline; }

    /*
     * Overrides the toString() method present in Task
     * @return A String representation of the task for printing
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", deadline);
    }
}