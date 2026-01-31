package gus;

import java.time.LocalDate;
import java.util.ArrayList;

import gus.command.Command;
import gus.command.Parser;
import gus.exception.GusException;
import gus.storage.Storage;
import gus.task.DeadlineTask;
import gus.task.EventTask;
import gus.task.Task;
import gus.task.TodoTask;
import gus.ui.TaskList;
import gus.ui.Ui;

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
     * Executes the given command with the provided input.
     *
     * @param command The command to execute.
     * @param input The user input string.
     * @throws GusException If the command execution fails.
     */
    private void executeCommand(Command command, String input) throws GusException {
        switch (command) {
        case LIST:
            handleList();
            break;
        case MARK:
            handleMark(input);
            break;
        case UNMARK:
            handleUnmark(input);
            break;
        case DELETE:
            handleDelete(input);
            break;
        case TODO:
            handleTodo(input);
            break;
        case DEADLINE:
            handleDeadline(input);
            break;
        case EVENT:
            handleEvent(input);
            break;
        case ON:
            handleOn(input);
            break;
        case FIND:
            handleFind(input);
            break;
        case ELSE:
            throw new GusException("I'm afraid I don't understand that request. Please clarify.");
        case BYE:
            break;
        default:
            break;
        }
    }

    /**
     * Displays the list of all tasks.
     */
    private void handleList() {
        ui.showTaskList(tasks.getListString());
    }

    /**
     * Marks multiple tasks as done.
     *
     * @param input The user input containing the task index.
     * @throws GusException If the task index is invalid.
     */
    private void handleMark(String input) throws GusException {
        int[] indices = Parser.parseTaskIndices(input, "unmark");
        Task[] markedTasks = tasks.markTask(indices);
        ui.showTasksUnmarked(markedTasks);
    }

    /**
     * Marks multiple tasks as not done.
     *
     * @param input The user input containing the task index.
     * @throws GusException If the task index is invalid.
     */
    private void handleUnmark(String input) throws GusException {
        int[] indices = Parser.parseTaskIndices(input, "unmark");
        Task[] unmarkedTasks = tasks.unmarkTask(indices);
        ui.showTasksUnmarked(unmarkedTasks);
    }

    /**
     * Deletes tasks.
     *
     * @param input The user input containing the task index.
     * @throws GusException If the task index is invalid.
     */
    private void handleDelete(String input) throws GusException {
        int[] indices = Parser.parseTaskIndices(input, "delete");
        Task[] deletedTasks = tasks.deleteTask(indices);
        ui.showTasksDeleted(tasks.size(), deletedTasks);
    }

    /**
     * Creates and adds a todo task.
     *
     * @param input The user input containing the task title.
     * @throws GusException If the task title is invalid.
     */

    private void handleTodo(String input) throws GusException {
        String title = Parser.parseDesc(input, "todo");
        Task t = new TodoTask(title);
        tasks.addTask(t);
        ui.showTaskAdded(t.toString(), tasks.size());
    }

    /**
     * Creates and adds a deadline task.
     *
     * @param input The user input containing the task title and deadline.
     * @throws GusException If the input format is invalid.
     */
    private void handleDeadline(String input) throws GusException {
        String[] details = Parser.parseDeadline(input);

        Task t = new DeadlineTask(details[0], details[1]);
        tasks.addTask(t);
        ui.showTaskAdded(t.toString(), tasks.size());
    }

    /**
     * Creates and adds a deadline task.
     *
     * @param input The user input containing the task title, from and to timestamp.
     * @throws GusException If the input format is invalid.
     */
    private void handleEvent(String input) throws GusException {
        String[] details = Parser.parseEvent(input);
        Task t = new EventTask(details[0], details[1], details[2]);
        tasks.addTask(t);
        ui.showTaskAdded(t.toString(), tasks.size());
    }

    /**
     * Finds the tasks scheduled on a specific date.
     *
     * @param input The user input containing the date.
     * @throws GusException If the date format is invalid.
     */
    private void handleOn(String input) throws GusException {
        LocalDate date = Parser.parseDate(input);
        String formattedDate = date.format(java.time.format.DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String tasksString = tasks.getListByDate(date);
        ui.showOnTasks(formattedDate, tasksString);
    }

    private void handleFind(String input) throws GusException {
        String k = Parser.parseDesc(input, "find");
        String tasksString = tasks.getListByKeyword(k);
        ui.showFoundTasks(tasksString);
    }

    public static void main(String[] args) {
        new Gus("./data/gus.txt").run();
    }
}
