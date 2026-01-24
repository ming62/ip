package gus.task;

/**
 * Represents a todo task.
 */
public class TodoTask extends Task {

    /**
     * Creates a todo task.
     * 
     * @param t The task description.
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
        return String.format("[T]%s", super.toString());
    }
}
