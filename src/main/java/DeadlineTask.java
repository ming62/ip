public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String t, String d) {
        super(t);
        this.deadline = d;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by %s)", super.toString(), this.deadline);
    }
}
