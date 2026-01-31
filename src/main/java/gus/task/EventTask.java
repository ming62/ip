package gus.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import gus.exception.GusException;

/**
 * Represents a task that occurs during a time period.
 */
public class EventTask extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private LocalDateTime to;
    private LocalDateTime from;

    /**
     * Creates an event task.
     *
     * @param t  The task title.
     * @param f  The start time.
     * @param to The end time.
     * @throws GusException If end time is before or equal to start time.
     */
    public EventTask(String t, String f, String to) throws GusException {
        super(t);
        this.to = LocalDateTime.parse(to, INPUT_FORMAT);
        this.from = LocalDateTime.parse(f, INPUT_FORMAT);

        if (this.to.isBefore(this.from) || this.to.isEqual(this.from)) {
            throw new GusException("I'm afraid the end time must be after the start time.");
        }
    }

    /**
     * Gets the start time.
     *
     * @return The start time.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Gets the end time.
     *
     * @return The end time.
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Gets the end time as a output formatted string.
     * MMM dd yyyy HH:mm
     *
     * @return The formatted end time.
     */
    public String getToOutputString() {
        return this.to.format(OUTPUT_FORMAT);
    }

    /**
     * Gets the start time as a output formatted string.
     * MMM dd yyyy HH:mm
     *
     * @return The formatted start time.
     */
    public String getFromOutputString() {
        return this.from.format(OUTPUT_FORMAT);
    }

    /**
     * Gets the end time as an input formatted string.
     * yyyy-MM-dd HHmm
     *
     * @return The input formatted end time.
     */
    public String getToInputString() {
        return this.to.format(INPUT_FORMAT);
    }

    /**
     * Gets the start time as an input formatted string.
     * yyyy-MM-dd HHmm
     *
     * @return The input formatted start time.
     */
    public String getFromInputString() {
        return this.from.format(INPUT_FORMAT);
    }

    /**
     * Checks if the event occurs on a given date.
     *
     * @param d The date to check.
     * @return True if event occurs on that date, false otherwise.
     */
    @Override
    public boolean occursOn(LocalDate d) {
        return !d.isBefore(this.from.toLocalDate()) && !d.isAfter(this.to.toLocalDate());
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return The formatted event task string.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from.format(OUTPUT_FORMAT),
                this.to.format(OUTPUT_FORMAT));
    }

}
