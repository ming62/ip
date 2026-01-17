public class TodoTask extends Task {

    public TodoTask(String t) {
        super(t);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
