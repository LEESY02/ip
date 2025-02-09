package task;

public class Task {
    private final String task;
    private final int index;
    private boolean completionStatus;
    protected String taskType;

    // Constants
    private final static String COMPLETE_MARK = "[X] ";
    private final static String INCOMPLETE_MARK = "[ ] ";
    private static final String TASK_TYPE = " [ ]";

    public Task(String task, int index) {
        this.task = task;
        this.index = index;
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
    public int getIndex() {
        return index;
    }

    // Private getters for toString()
    private String getTaskType() {
        return taskType;
    }
    private String getIndexString() { return String.format("%d.", getIndex() + 1); }

    @Override
    public String toString() {
        String isCompletedCheckMark;
        if (this.getCompletionStatus()) {
            isCompletedCheckMark = COMPLETE_MARK;
        } else {
            isCompletedCheckMark = INCOMPLETE_MARK;
        }
        return this.getIndexString() + this.getTaskType() + isCompletedCheckMark + this.getTask();
    }
}
