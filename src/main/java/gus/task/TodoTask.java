package gus.task;

/**
 * Represents a simple todo task without any date/time constraints.
 */
public class TodoTask extends Task {

    /**
     * Creates a todo task.
     *
     * @param t The task title.
     */
    public TodoTask(String t) {
        super(t);
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return The formatted todo task string.
     */
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
