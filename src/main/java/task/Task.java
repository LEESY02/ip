package task;

public class Task {
    private final String task;
    private boolean completionStatus;
    protected String taskType;

    // Constants
    private final static String COMPLETE_MARK = "[X] ";
    private final static String INCOMPLETE_MARK = "[ ] ";
    private static final String TASK_TYPE = " [ ]";

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

    // Private getters for toString()
    private String getTaskType() {
        return taskType;
    }

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
