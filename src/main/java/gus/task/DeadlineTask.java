package gus.task;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    private LocalDateTime deadline;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Creates a deadline task.
     * 
     * @param t The task description.
     * @param d The deadline.
     */
    public DeadlineTask(String t, String d) {
        super(t);
        this.deadline = LocalDateTime.parse(d, INPUT_FORMAT);
    }

    /**
     * Gets the deadline.
     * 
     * @return The deadline.
     */
    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * Gets the deadline as an input formatted string.
     * 
     * @return The input formatted deadline.
     */
    public String getDeadlineInputString() {
        return this.deadline.format(INPUT_FORMAT);
    }

    
    /**
     * Gets the deadline as a formatted string.
     * 
     * @return The formatted deadline.
     */
    public String getDeadlineOutputString() {
        return this.deadline.format(OUTPUT_FORMAT);
    }

    /**
     * Checks if the deadline is on a given date.
     * 
     * @param d The date to check.
     * @return True if deadline is on that date, false otherwise.
     */
    @Override   
    public boolean occursOn(LocalDate d) {
        return this.deadline.toLocalDate().equals(d);
    }

    /**
     * Returns the string representation of the deadline task.
     * 
     * @return The formatted deadline task string.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by %s)", super.toString(), this.deadline.format(OUTPUT_FORMAT));
    }
}
