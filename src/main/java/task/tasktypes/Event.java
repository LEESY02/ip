package task.tasktypes;

import task.Task;
import static data.Constants.*;

public class Event extends Task {
    private final String startDate;
    private final String endDate;

    /*
     * Instantiates parent Task class, and defines the start and end date of the task
     * @param task Parameters for parent Task constructor
     * @startDate The start date of this Task object
     * @endDate The end date of this Task object
     */
    public Event(String task, String startDate, String endDate) {
        super(task);
        this.startDate = startDate;
        this.endDate = endDate;
        super.taskType = TASK_TYPE_EVENT;
    }

    /*
     * @return Returns startDate, used for saving
     */
    @Override
    public String getStartDate() { return startDate; }

    /*
     * @return Returns endDate, used for saving
     */
    @Override
    public String getEndDate() { return endDate; }

    /*
     * Overrides the toString() method present in Task
     * @return A String representation of the task for printing
     */
    @Override
    public String toString() {
        return super.toString()
            + String.format(" (from: %s,", startDate) + String.format(" to: %s)", endDate);
    }
}