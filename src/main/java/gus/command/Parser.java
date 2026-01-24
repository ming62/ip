package gus.command;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import gus.exception.GusException;

/**
 * Parses user input and returns the corresponding command.
 * 
 * @param input The user input string.
 * @return The command type.
 */
/**
 * Parses user input into commands and extracts information.
 */
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

    /**
     * Parses the task index from user input.
     * 
     * @param input The user input string.
     * @param command The command type.
     * @return The task index.
     * @throws GusException If the task index is missing or invalid.
     */
    public static int parseTaskIndex(String input, String command) throws GusException {
        if (input.length() <= command.length() + 1) {
            throw new GusException("Please specify which task number.");
        }
        
        String details = input.substring(command.length()).trim();

        if (details.isEmpty()) {
            throw new GusException("Please specify which task number.");
        }

        try {
            return Integer.parseInt(details) - 1;
        } catch (NumberFormatException e) {
            throw new GusException("I need a valid task number, please.");
        }
    }

    /**
     * Parses the description from user input.
     * 
     * @param input The user input string.
     * @param command The command type.
     * @return The task description.
     * @throws GusException If the description is missing.
     */
    public static String parseDesc(String input, String command) throws GusException {
        if (input.length() <= command.length()) {

            if (command == "on") {
                throw new GusException("Please provide the date in the format yyyy-MM-dd.");
            }
            throw new GusException("I'm afraid I need a description for this task.");
        }
        
        String details = input.substring(command.length()).trim();

        if (details.isEmpty()) {
            throw new GusException("I'm afraid I need a description for this task.");
        }
        
        return details;
    }

    /**
     * Parses deadline information from user input.
     * 
     * @param input The user input string.
     * @return Array containing task description and deadline.
     * @throws GusException If the format is invalid.
     */
    public static String[] parseDeadline(String input) throws GusException {
        String details = parseDesc(input, "deadline");


        if (!details.contains(" /by ")) {
            throw new GusException("Please specify the deadline using /by.");
        }
        
        String[] parts = details.split(" /by ", 2);

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime.parse(parts[1], formatter);
        } catch (DateTimeParseException e) {
            throw new GusException("Please provide the date in the format yyyy-MM-dd HHmm.");
        }
        
        return parts;
    }

    
    /**
     * Parses event information from user input.
     * 
     * @param input The user input string.
     * @return Array containing task description, start time, and end time.
     * @throws GusException If the format is invalid.
     */
    public static String[] parseEvent(String input) throws GusException {
        String details = parseDesc(input, "event");

        if (!details.contains(" /from ") || !details.contains(" /to ")) {
            throw new GusException("Please specify both the start time with /from and end time with /to.");
        }

        String[] firstSplit = details.split(" /from ", 2);
        String[] secondSplit = firstSplit[1].split(" /to ", 2);

        String[] parts = new String[]{firstSplit[0], secondSplit[0], secondSplit[1]};
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime.parse(parts[1], formatter);
            LocalDateTime.parse(parts[2], formatter);
        } catch (DateTimeParseException e) {
            throw new GusException("Please provide the date in the format yyyy-MM-dd HHmm.");
        }
        
        return parts;
    }

    
    /**
     * Parses a date from user input.
     * 
     * @param input The user input string.
     * @return The parsed date.
     * @throws GusException If the date format is invalid.
     */
    public static LocalDate parseDate(String input) throws GusException {
        String dateString = parseDesc(input, "on");

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new GusException("Please provide the date in the format yyyy-MM-dd.");
        }
    }

}
