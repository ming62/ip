package gus.task;

/**
 * Represents the priority level of a task.
 */
public enum Priority {
    TOP(1, "TOP", "!"),
    MID(2, "MID", "-"),
    LOW(3, "LOW", "~"),
    NONE(4, "NONE", " ");

    private final int level;
    private final String name;
    private final String symbol;

    Priority(int level, String name, String symbol) {
        this.level = level;
        this.name = name;
        this.symbol = symbol;
    }

    /**
     * Returns the integer value of the priority level.
     *
     * @return the priority level as an integer
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the symbol representing the priority.
     *
     * @return the symbol for this priority
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns the name of the priority level.
     *
     * @return the name of this priority
     */
    public String getName() {
        return name;
    }

}
