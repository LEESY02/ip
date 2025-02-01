public class Event extends Task {
    private final String startDate;
    private final String endDate;

    public Event(String task, int index, String startDate, String endDate) {
        super(task, index);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return super.toString().replaceFirst("\\[", "[E][")
            + String.format(" (from: %s,", startDate) + String.format(" to: %s)", endDate);
    }
}