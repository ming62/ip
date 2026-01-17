public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String t, String d) {
        super(t);
        this.deadline = d;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by %s)", super.toString(), this.deadline);
    }
}
