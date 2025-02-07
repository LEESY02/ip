public class Deadline extends Task {
    private final String deadline;
    private static final String TASK_TYPE = " [D]";

    public Deadline(String task, int index, String deadline) {
        super(task, index);
        this.deadline = deadline;
        super.taskType = TASK_TYPE;
    }

    @Override
    public String toString() {
        return super.toString()/*.replaceFirst("\\[", "[D][")*/ + String.format(" (by: %s)", deadline);
    }
}