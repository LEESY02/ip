package task.tasktypes;

import task.Task;

import java.time.LocalDate;

import static data.Constants.*;

/**
  * Contains 2 LocalDate objects (startDate, endDate) to indicate the start and end of Event
  */
public class Event extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
      * Instantiates parent Task class, and defines the start and end date of the task
      * @param task Parameters for parent Task constructor
      * @param startDate The start date of this Task object
      * @param endDate The end date of this Task object
      */
    public Event(String task, LocalDate startDate, LocalDate endDate) {
        super(task);
        this.startDate = startDate;
        this.endDate = endDate;
        super.taskType = TASK_TYPE_EVENT;
    }

    /**
      * @return Returns startDate, used for saving
      */
    @Override
    public LocalDate dueDate() {
        return endDate;
    }

    /**
      * @return Returns startDate, used for saving
      */
    @Override
    public String getStartDate() { return startDate.toString(); }

    /**
      * @return Returns endDate, used for saving
      */
    @Override
    public String getEndDate() { return endDate.toString(); }

    /**
      * Overrides the toString() method present in Task
      * @return A String representation of the task for printing
      */
    @Override
    public String toString() {
        return super.toString()
            + String.format(" (from: %s,", startDate) + String.format(" to: %s)", endDate);
    }
}