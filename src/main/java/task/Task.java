package task;

import static data.Constants.*;

public class Task {
    private final String task;
    private boolean completionStatus;
    protected String taskType;



    public Task(String task) {
        this.task = task;
        this.completionStatus = false;
        this.taskType = TASK_TYPE;
    }

    // Setters
    public void setComplete() {
        completionStatus = true;
    }
    public void setIncomplete() {
        completionStatus = false;
    }

    // Getters
    public boolean getCompletionStatus() { return completionStatus; }
    public String getTask() {
        return task;
    }

    // getters for taskType
    public String getTaskType() {
        return taskType;
    }

    // Getters to be overwritten
    public String getDeadline() { return EMPTY_STRING; }
    public String getStartDate() { return EMPTY_STRING; }
    public String getEndDate() { return EMPTY_STRING; }

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
