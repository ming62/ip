package gus;

import java.time.LocalDate;
import java.util.ArrayList;

import gus.command.Command;
import gus.command.Parser;
import gus.exception.GusException;
import gus.gui.Ui;
import gus.storage.Storage;
import gus.task.DeadlineTask;
import gus.task.EventTask;
import gus.task.Task;
import gus.task.TodoTask;
import gus.ui.TaskList;

/**
 * Main class for the Gus task management application.
 */
public class Gus {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Gus instance with the file path for storage.
     *
     * @param file The file path where tasks are stored.
     */
    public Gus(String file) {
        ui = new Ui();
        storage = new Storage(file);

        try {
            tasks = new TaskList(storage.load());
        } catch (GusException e) {
            ui.showText(e.getMessage());
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Executes the main program loop.
     * Reads user input, parse command, execute command, and save tasks on bye command.
     */
    public void run() {
        ui.showWelcome();

        String input = ui.readCommand();

        while (Parser.parseCommand(input) != Command.BYE) {
            try {
                Command currCommand = Parser.parseCommand(input);
                executeCommand(currCommand, input);
            } catch (GusException e) {
                ui.showText(e.getMessage());
            }
            input = ui.readCommand();
        }

        try {
            storage.save(tasks.getTasks());
        } catch (GusException e) {
            ui.showText(e.getMessage());
        }

        ui.showBye();
        ui.close();
    }

    /**
     * Gets response for given user input from GUI.
     *
     * @param input The user input string.
     * @return The response string.
     */
    public String getResponseGui(String input) {
        try {

            Command currCommand = Parser.parseCommand(input);

            if (currCommand == Command.BYE) {
                storage.save(tasks.getTasks());
                return "It has been a pleasure serving you.";
            }

            return executeCommandGui(currCommand, input);

        } catch (GusException e) {
            return "Something went wrong.\n" + e.getMessage();
        }
    }

    /**
     * Executes command and returns response string for GUI.
     *
     * @param command The command to execute.
     * @param input The user input.
     * @return The response string.
     * @throws GusException If command execution fails.
     */
    private String executeCommandGui(Command command, String input) throws GusException {
        switch (command) {
        case LIST:
            return handleList();
        case MARK:
            return handleMark(input);
        case UNMARK:
            return handleUnmark(input);
        case DELETE:
            return handleDelete(input);
        case TODO:
            return handleTodo(input);
        case DEADLINE:
            return handleDeadline(input);
        case EVENT:
            return handleEvent(input);
        case ON:
            return handleOn(input);
        case FIND:
            return handleFind(input);
        case ELSE:
            throw new GusException("I'm afraid I don't understand that request. Please clarify.");
        case BYE:
            return "It has been a pleasure serving you.";
        default:
            throw new GusException("I'm afraid I don't understand that request. Please clarify.");
        }
    }


    /**
     * Executes the given command with the provided input.
     *
     * @param command The command to execute.
     * @param input The user input string.
     * @throws GusException If the command execution fails.
     */
    private void executeCommand(Command command, String input) throws GusException {
        String response = executeCommandGui(command, input);
        ui.showText(response);
    }

    /**
     * Returns the list of all tasks.
     *
     * @return The formatted task list string.
     */
    private String handleList() {
        return tasks.getListString();
    }

    /**
     * Marks multiple tasks as done.
     *
     * @param input The user input containing the task indices.
     * @return A formatted string with the marked tasks.
     * @throws GusException If any task index is invalid.
     */
    private String handleMark(String input) throws GusException {
        int[] indices = Parser.parseTaskIndices(input, "mark");
        Task[] markedTasks = tasks.markTask(indices);
        return ui.showTasksMarked(markedTasks);
    }

    /**
     * Marks multiple tasks as not done.
     *
     * @param input The user input containing the task indices.
     * @return A formatted string with the unmarked tasks.
     * @throws GusException If any task index is invalid.
     */
    private String handleUnmark(String input) throws GusException {
        int[] indices = Parser.parseTaskIndices(input, "unmark");
        Task[] unmarkedTasks = tasks.unmarkTask(indices);
        return ui.showTasksUnmarked(unmarkedTasks);
    }

    /**
     * Deletes tasks.
     *
     * @param input The user input containing the task indices.
     * @return A formatted string with the deleted tasks and remaining count.
     * @throws GusException If any task index is invalid.
     */
    private String handleDelete(String input) throws GusException {
        int[] indices = Parser.parseTaskIndices(input, "delete");
        Task[] deletedTasks = tasks.deleteTask(indices);
        return ui.showTasksDeleted(tasks.size(), deletedTasks);
    }

    /**
     * Creates and adds a todo task.
     *
     * @param input The user input containing the task title.
     * @return A formatted string with the added task.
     * @throws GusException If the task title is invalid.
     */
    private String handleTodo(String input) throws GusException {
        String title = Parser.parseDesc(input, "todo");
        Task t = new TodoTask(title);
        tasks.addTask(t);
        return ui.showTaskAdded(t, tasks.size());
    }

    /**
     * Creates and adds a deadline task.
     *
     * @param input The user input containing the task title and deadline.
     * @return A formatted string with the added task.
     * @throws GusException If the input format is invalid.
     */
    private String handleDeadline(String input) throws GusException {
        String[] details = Parser.parseDeadline(input);

        Task t = new DeadlineTask(details[0], details[1]);
        tasks.addTask(t);
        return ui.showTaskAdded(t, tasks.size());
    }

    /**
     * Creates and adds an event task.
     *
     * @param input The user input containing the task title, from and to timestamp.
     * @return A formatted string with the added task.
     * @throws GusException If the input format is invalid.
     */
    private String handleEvent(String input) throws GusException {
        String[] details = Parser.parseEvent(input);
        Task t = new EventTask(details[0], details[1], details[2]);
        tasks.addTask(t);
        return ui.showTaskAdded(t, tasks.size());
    }

    /**
     * Finds the tasks scheduled on a specific date.
     *
     * @param input The user input containing the date.
     * @return A formatted string with the tasks on the specified date.
     * @throws GusException If the date format is invalid.
     */
    private String handleOn(String input) throws GusException {
        LocalDate date = Parser.parseDate(input);
        String formattedDate = date.format(java.time.format.DateTimeFormatter.ofPattern("MMM dd yyyy"));
        Task[] matchingTasks = tasks.getListByDate(date);
        return ui.showOnTasks(formattedDate, matchingTasks);
    }

    /**
     * Finds tasks containing a specific keyword.
     *
     * @param input The user input containing the keyword to search for.
     * @return A formatted string with the matching tasks.
     * @throws GusException If the keyword is missing.
     */
    private String handleFind(String input) throws GusException {
        String k = Parser.parseDesc(input, "find");
        Task[] matchingTasks = tasks.getListByKeyword(k);
        return ui.showFoundTasks(matchingTasks);
    }

    public static void main(String[] args) {
        new Gus("./data/gus.txt").run();
    }
}
