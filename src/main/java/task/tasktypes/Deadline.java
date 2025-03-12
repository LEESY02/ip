package task.tasktypes;

import task.Task;
import static data.Constants.*;
import java.time.LocalDate;

/**
  * Contains a LocalDate object (deadline) to indicate the due date of Deadline
  */
public class Deadline extends Task {
    private final LocalDate deadline;

    /**
      * Instantiates parent Task class, and defines the deadline of the task
      * @param task Parameters for parent Task constructor
      * @param deadline The due date of this Task object
      */
    public Deadline(String task, LocalDate deadline) {
        super(task);
        this.deadline = deadline;
        super.taskType = TASK_TYPE_DEADLINE;
    }

    /**
      * @return Returns deadline in the form of LocalDate
      */
    @Override
    public LocalDate dueDate() {
        return deadline;
    }

    /**
      * @return Returns deadline, used for saving
      */
    @Override
    public String getDeadline() { return deadline.toString(); }

    /**
      * Overrides the toString() method present in Task
      * @return A String representation of the task for printing
      */
    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", deadline);
    }
}