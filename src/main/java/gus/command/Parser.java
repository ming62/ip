package gus.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import gus.exception.GusException;

/**
 * Parses user input into commands and extracts command parameters.
 */
public class Parser {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Parses the command from user input.
     *
     * @param input The user input string.
     * @return The parsed command.
     */
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
        } else if (lowerCaseInput.equals("find") || lowerCaseInput.startsWith("find ")) {
            return Command.FIND;
        } else if (lowerCaseInput.equals("pri") || lowerCaseInput.startsWith("pri ")) {
            return Command.PRI;
        } else {
            return Command.ELSE;
        }
    }

    /**
     * Parses multiple task indices from user input
     *
     * @param input   The user input string.
     * @param command The command type.
     * @return The task index.
     * @throws GusException If the task index is missing or invalid.
     */
    public static int[] parseTaskIndices(String input, String command) throws GusException {
        String[] parts = input.trim().split("\\s+");

        if (parts.length < 2) {
            throw new GusException("Please specify which task number.");
        }

        int[] indices = new int[parts.length - 1];

        for (int i = 1; i < parts.length; i++) {
            try {
                int taskNumber = Integer.parseInt(parts[i]);
                indices[i - 1] = taskNumber - 1;
            } catch (NumberFormatException e) {
                throw new GusException("Invalid task number: " + parts[i]);
            }
        }

        indices = java.util.Arrays.stream(indices)
                .boxed()
                .sorted(java.util.Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();

        return indices;
    }

    /**
     * Parses the task index from user input.
     *
     * @param input   The user input string.
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
     * @param input   The user input string.
     * @param command The command type.
     * @return The task description.
     * @throws GusException If the description is missing.
     */
    public static String parseDesc(String input, String command) throws GusException {
        if (input.length() <= command.length()) {

            if (command.equals("on")) {
                throw new GusException("Please provide the date in the format yyyy-MM-dd.");
            }

            if (command.equals("find")) {
                throw new GusException("Please provide the keyword to find for.");
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
     * @return Array containing task title and deadline.
     * @throws GusException If the format is invalid.
     */
    public static String[] parseDeadline(String input) throws GusException {
        String details = parseDesc(input, "deadline");

        if (!details.contains(" /by ")) {
            throw new GusException("Please specify the deadline using /by.");
        }

        String[] parts = details.split(" /by ", 2);

        try {
            LocalDateTime.parse(parts[1], DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new GusException("Please provide the date in the format yyyy-MM-dd HHmm.");
        }

        return parts;
    }

    /**
     * Parses event information from user input.
     *
     * @param input The user input string.
     * @return Array containing task title, from time, and to time.
     * @throws GusException If the format is invalid.
     */
    public static String[] parseEvent(String input) throws GusException {
        String details = parseDesc(input, "event");

        if (!details.contains(" /from ") || !details.contains(" /to ")) {
            throw new GusException("Please specify both the start time with /from and end time with /to.");
        }

        String[] firstSplit = details.split(" /from ", 2);
        String[] secondSplit = firstSplit[1].split(" /to ", 2);

        String[] parts = new String[] { firstSplit[0], secondSplit[0], secondSplit[1] };

        try {
            LocalDateTime.parse(parts[1], DATE_TIME_FORMATTER);
            LocalDateTime.parse(parts[2], DATE_TIME_FORMATTER);
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
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new GusException("Please provide the date in the format yyyy-MM-dd.");
        }
    }

    /**
     * Parses priority command from user input.
     *
     * @param input The user input string.
     * @return Array where first element is priority level, rest are task numbers.
     * @throws GusException If the format is invalid.
     */
    public static String[] parsePriority(String input) throws GusException {
        String details = parseDesc(input, "pri");
        String[] parts = details.trim().split("\\s+");

        if (parts.length < 2) {
            throw new GusException("Please specify priority level and at least one task number.");
        }

        parts[0] = parts[0].toUpperCase();

        try {
            gus.task.Priority.valueOf(parts[0]);
        } catch (IllegalArgumentException e) {
            throw new GusException("Invalid priority level. Please use TOP, MID, LOW, or NONE.");
        }

        return parts;
    }

}
