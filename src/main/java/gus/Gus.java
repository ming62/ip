package gus;

import java.time.LocalDate;
import java.util.ArrayList;

import gus.command.Command;
import gus.command.Parser;
import gus.exception.GusException;
import gus.storage.Storage;
import gus.task.Task;
import gus.task.TodoTask;
import gus.task.DeadlineTask;
import gus.task.EventTask;

import gus.ui.TaskList;
import gus.ui.Ui;

public class Gus {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
            case ELSE:
                throw new GusException("I don't understand your language");
            case BYE:
                break;
            default:
                break;
        }
    }

    private void handleList() {
        ui.showTaskList(tasks.getListString());
    }

    private void handleMark(String input) throws GusException {
        int index = Parser.parseTaskIndex(input, "mark");
        tasks.markTask(index);
        ui.showTaskMarked(tasks.getTask(index).toString());
    }

    private void handleUnmark(String input) throws GusException {
        int index = Parser.parseTaskIndex(input, "unmark");
        tasks.unmarkTask(index);
        ui.showTaskUnmarked(tasks.getTask(index).toString());
    }

    private void handleDelete(String input) throws GusException {
        int index = Parser.parseTaskIndex(input, "delete");
        Task t = tasks.deleteTask(index);
        ui.showTaskDeleted(t.toString(), tasks.size());
    }

    private void handleTodo(String input) throws GusException {
        String title = Parser.parseDesc(input, "todo");
        Task t = new TodoTask(title);
        tasks.addTask(t);
        ui.showTaskAdded(t.toString(), tasks.size());
    }

    private void handleDeadline(String input) throws GusException {
        String[] details = Parser.parseDeadline(input);

        Task t = new DeadlineTask(details[0], details[1]);
        tasks.addTask(t);
        ui.showTaskAdded(t.toString(), tasks.size());
    }

    private void handleEvent(String input) throws GusException {
        String[] details = Parser.parseEvent(input);
        Task t = new EventTask(details[0], details[1], details[2]);
        tasks.addTask(t);
        ui.showTaskAdded(t.toString(), tasks.size());
    }

    private void handleOn(String input) throws GusException {
        LocalDate date = Parser.parseDate(input);
        String formattedDate = date.format(java.time.format.DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String tasksString = tasks.getListByDate(date);
        ui.showFoundTasks(formattedDate, tasksString);
    }

    public static void main(String[] args) {
        new Gus("./data/gus.txt").run();
    }
}
