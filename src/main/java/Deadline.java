public class Deadline extends Task {
    private final String deadline;

    public Deadline(String task, int index, String deadline) {
        super(task, index);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString().replaceFirst("\\[", "[D][") + String.format(" (by: %s)", deadline);
    }
}