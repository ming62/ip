package gus.task;

/**
 * Represents a task with a title and completion status.
 */
public class Task {
    private String title;
    private boolean isDone;

    /**
     * Creates a task with the given title.
     * 
     * @param t The task title.
     */
    public Task(String t) {
        this.title = t;
        isDone = false;
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
     * Gets the completion status of the task.
     * 
     * @return True if done, false otherwise.
     */
    public boolean getIsDone() {
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
        return String.format("[%s] %s", this.isDone ? "X": " ", this.title);
    }
}
