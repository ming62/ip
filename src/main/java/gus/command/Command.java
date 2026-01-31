package gus.command;

/**
 * Represents the different types of commands the application can process.
 */
public enum Command {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
    ON,
    FIND,
    ELSE;

    /**
     * Parses a command from user input.
     *
     * @param input The user input string.
     * @return The command type.
     */
    public static Command parseCommand(String input) {

        if (input.trim().isEmpty()) {
            return ELSE;
        }

        String command = input.trim().split(" ")[0].toUpperCase();

        try {
            return Command.valueOf(command);
        } catch (IllegalArgumentException e) {
            return ELSE;
        }

    }
}
