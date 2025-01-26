public class Task {
    private final String task;
    private final int index;
    private boolean completionStatus;

    public Task(String task, int index) {
        this.task = task;
        this.index = index;
        completionStatus = false;
    }

    public void setComplete() {
        completionStatus = true;
    }

    public void setIncomplete() {
        completionStatus = false;
    }

    public boolean getCompletionStatus() {
        return completionStatus;
    }

    public String getTask() {
        return task;
    }

    @Override
    public String toString() {
        String isCompletedCheckMark;
        if (this.getCompletionStatus()) {
            isCompletedCheckMark = ".[X] ";
        } else {
            isCompletedCheckMark = ".[ ] ";
        }
        return index + isCompletedCheckMark + task;
    }
}
