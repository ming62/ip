public class EventTask extends Task {
    private String to;
    private String from;

    public EventTask(String t, String f, String to) {
        super(t);
        this.to = to;
        this.from = f;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }


}
