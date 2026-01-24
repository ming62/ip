package gus.command;
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
    ELSE;



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