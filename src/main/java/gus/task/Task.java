package gus.task;

/**
 * Represents a generic task with a title and completion status.
 */
public class Task {
    private String title;
    private boolean isDone;
    private Priority priority;

    /**
     * Creates a task with the title.
     *
     * @param t The task title.
     */
    public Task(String t) {

        assert t != null : "Task title should not be null";
        assert !t.trim().isEmpty() : "Task title should not be empty";

        this.title = t;
        isDone = false;
        this.priority = Priority.NONE;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Sets the priority of the task.
     *
     * @param priority The priority to set
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Gets the priority of the task.
     *
     * @return The priority of this task
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Checks if the task is done.
     *
     * @return True if done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Gets the title of the task.
     *
     * @return The task title.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Checks if the task occurs on a given date.
     *
     * @param d The date to check.
     * @return True if task occurs on that date, false otherwise.
     */
    public boolean occursOn(java.time.LocalDate d) {
        return false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The formatted task string.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.priority.getSymbol(), this.isDone ? "X" : " ", this.title);
    }
}
