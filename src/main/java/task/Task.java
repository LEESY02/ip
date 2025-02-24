package task;

import static data.Constants.*;

/*
 * The main Task class that is the parent class of other subtask categories
 * Contains:
 * 1. task: A String to contain the task name
 * 2. completionStatus: A Boolean to check completion
 * 3. taskType: A String used as a symbol to identify the task types when printing (toString())
 */
public class Task {
    private final String task;
    private boolean completionStatus;
    protected String taskType;

    /*
     * @param task A String containing task descriptions
     */
    public Task(String task) {
        this.task = task;
        this.completionStatus = false;
        this.taskType = TASK_TYPE;
    }

    /*
     * Pair of setters for setting the completionStatus Boolean
     */
    public void setComplete() {
        completionStatus = true;
    }
    public void setIncomplete() {
        completionStatus = false;
    }

    /*
     * Getters for getting the class elements
     */
    public boolean getCompletionStatus() { return completionStatus; }
    public String getTask() {
        return task;
    }
    public String getTaskType() {
        return taskType;
    }

    /*
     * Getters to be overwritten by subclasses
     */
    public String getDeadline() { return EMPTY_STRING; }
    public String getStartDate() { return EMPTY_STRING; }
    public String getEndDate() { return EMPTY_STRING; }

    /*
     * Overrides the toString() method present in Object
     * @return A String representation of the task for printing
     */
    @Override
    public String toString() {
        String isCompletedCheckMark;
        if (this.getCompletionStatus()) {
            isCompletedCheckMark = COMPLETE_MARK;
        } else {
            isCompletedCheckMark = INCOMPLETE_MARK;
        }
        return this.getTaskType() + isCompletedCheckMark + this.getTask();
    }
}
