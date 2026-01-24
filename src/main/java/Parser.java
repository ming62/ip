import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    
    public static Command parseCommand(String input) {
        String lowerCaseInput = input.toLowerCase().trim();

        if (lowerCaseInput.equals("list")) {
            return Command.LIST;
        } else if (lowerCaseInput.equals("bye")) {
            return Command.BYE;
        } else if (lowerCaseInput.equals("mark") || lowerCaseInput.startsWith("mark ")) {
            return Command.MARK;
        } else if (lowerCaseInput.equals("unmark") || lowerCaseInput.startsWith("unmark ")) {
            return Command.UNMARK;
        } else if (lowerCaseInput.equals("delete") || lowerCaseInput.startsWith("delete ")) {
            return Command.DELETE;
        } else if (lowerCaseInput.equals("todo") || lowerCaseInput.startsWith("todo ")) {
            return Command.TODO;
        } else if (lowerCaseInput.equals("deadline") || lowerCaseInput.startsWith("deadline ")) {
            return Command.DEADLINE;
        } else if (lowerCaseInput.equals("event") || lowerCaseInput.startsWith("event ")) {
            return Command.EVENT;
        } else if (lowerCaseInput.equals("on") || lowerCaseInput.startsWith("on ")) {
            return Command.ON;
        } else {
            return Command.ELSE;
        }
    }

    public static int parseTaskIndex(String input, String command) throws GusException {
        if (input.length() <= command.length() + 1) {
            throw new GusException("You must tell me which task");
        }
        
        String details = input.substring(command.length()).trim();

        if (details.isEmpty()) {
            throw new GusException("You must tell me which task");
        }

        try {
            return Integer.parseInt(details) - 1;
        } catch (NumberFormatException e) {
            throw new GusException("I need a valid task number");
        }
    }

    public static String parseDesc(String input, String command) throws GusException {
        if (input.length() <= command.length()) {

            if (command == "on") {
                throw new GusException("I need the date in the format yyyy-MM-dd");
            }
            throw new GusException("Hmm I don't know what is the title for this task");
        }
        
        String details = input.substring(command.length()).trim();

        if (details.isEmpty()) {
            throw new GusException("Hmm I don't know what is the title for this task");
        }
        
        return details;
    }

    public static String[] parseDeadline(String input) throws GusException {
        String details = parseDesc(input, "deadline");


        if (!details.contains(" /by ")) {
            throw new GusException("Give me your deadline with /by!");
        }
        
        String[] parts = details.split(" /by ", 2);

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime.parse(parts[1], formatter);
        } catch (DateTimeParseException e) {
            throw new GusException("I need the date in the format yyyy-MM-dd HHmm");
        }
        
        return parts;
    }

    
    public static String[] parseEvent(String input) throws GusException {
        String details = parseDesc(input, "event");

        if (!details.contains(" /from ") || !details.contains(" /to ")) {
            throw new GusException("Give me both from and to timeline with /from then /to!");
        }

        String[] firstSplit = details.split(" /from ", 2);
        String[] secondSplit = firstSplit[1].split(" /to ", 2);

        String[] parts = new String[]{firstSplit[0], secondSplit[0], secondSplit[1]};
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime.parse(parts[1], formatter);
            LocalDateTime.parse(parts[2], formatter);
        } catch (DateTimeParseException e) {
            throw new GusException("I need the date in the format yyyy-MM-dd HHmm");
        }
        
        return parts;
    }

    
    public static LocalDate parseDate(String input) throws GusException {
        String dateString = parseDesc(input, "on");

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new GusException("I need the date in the format yyyy-MM-dd");
        }
    }

}
