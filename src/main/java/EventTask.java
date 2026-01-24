import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private LocalDateTime to;
    private LocalDateTime from;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public EventTask(String t, String f, String to) throws GusException {
        super(t);
        this.to = LocalDateTime.parse(to, INPUT_FORMAT);
        this.from = LocalDateTime.parse(f, INPUT_FORMAT);

        if (this.to.isBefore(this.from) || this.to.isEqual(this.from)) {
            throw new GusException("To time should be after from time no?");
        }
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    public String getToOutputString() {
        return this.to.format(OUTPUT_FORMAT);
    }

    public String getFromOutputString() {
        return this.from.format(OUTPUT_FORMAT);
    }

    public String getToInputString() {
        return this.to.format(INPUT_FORMAT);
    }

    public String getFromInputString() {
        return this.from.format(INPUT_FORMAT);
    }
    @Override   
    public boolean occursOn(LocalDate d) {
        return !d.isBefore(this.from.toLocalDate()) && !d.isAfter(this.to.toLocalDate());
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from.format(OUTPUT_FORMAT), this.to.format(OUTPUT_FORMAT));
    }


}
