import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private LocalDateTime deadline;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public DeadlineTask(String t, String d) {
        super(t);
        this.deadline = LocalDateTime.parse(d, INPUT_FORMAT);
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public String getDeadlineString() {
        return this.deadline.format(OUTPUT_FORMAT);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by %s)", super.toString(), this.deadline.format(OUTPUT_FORMAT));
    }
}
