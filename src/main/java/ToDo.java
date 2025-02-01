public class ToDo extends Task {

    public ToDo(String task, int index) {
        super(task, index);
    }

    @Override
    public String toString() {
        return super.toString().replaceFirst("\\[", "[T][");
    }
}
