package gus.gui;

import java.util.Scanner;

import gus.task.Task;

/**
 * Handles all user interface jobs for the Gus application.
 * Manages input reading and output display to the user.
 */
public class Ui {
    public static final String GUS_PREFIX = "[ GUSTAVO ]: ";
    public static final String LINE = "______________________________________________";
    private Scanner scanner;

    /**
     * Constructs a Ui instance and initializes the scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message and ASCII logo when the application starts.
     */
    public void showWelcome() {
        String logo = "         /$$$$$$  /$$   /$$  /$$$$$$        \n"
                + "       /$$__  $$| $$  | $$ /$$__  $$        \n"
                + "      | $$  \\__/| $$  | $$| $$  \\__/       \n"
                + "      | $$ /$$$$| $$  | $$|  $$$$$$        \n"
                + "      | $$|_  $$| $$  | $$ \\____  $$       \n"
                + "      | $$  \\ $$| $$  | $$ /$$  \\ $$       \n"
                + "      |  $$$$$$/|  $$$$$$/|  $$$$$$/       \n"
                + "      \\______/  \\______/  \\______/         \n";

        System.out.println("\nHello, Welcome to Los Pollos Hermanos.");
        System.out.println("My name is Gustavo Fring, but you may call me");
        System.out.println(LINE);
        System.out.println("\n\n" + logo);
        System.out.println(LINE);
        System.out.println("\n" + GUS_PREFIX + "What can I do for you today?");
    }

    /**
     * Displays the goodbye message when the application exits.
     */
    public void showBye() {
        System.out.println(GUS_PREFIX + "It has been a pleasure serving you.");
    }

    /**
     * Reads a command from the user.
     *
     * @return The user input as a String.
     */
    public String readCommand() {
        System.out.print(">> ");
        return scanner.nextLine();
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Displays a text message to the user with the Gus prefix.
     *
     * @param lines The messages to display.
     */
    public void showText(String... lines) {
        for (String line : lines) {
            System.out.println(GUS_PREFIX + line);
        }
    }


    private String formatTaskList(String header, Task... tasks) {
        StringBuilder result = new StringBuilder();
        result.append(header).append("\n\n");

        for (Task task : tasks) {
            result.append(String.format("%s \n", task.toString()));
        }

        result.append("\n");
        return result.toString();
    }

    /**
     * Returns a message when a task is added.
     *
     * @param task      The task that was added.
     * @param taskCount The total number of tasks after adding this task.
     * @return A formatted string with the added task and count.
     */
    public String showTaskAdded(Task task, int taskCount) {
        StringBuilder result = new StringBuilder();
        result.append("Very well. I have added this task:\n");
        result.append("\n");
        result.append(String.format("%s \n", task.toString()));
        result.append("\n");
        result.append(String.format("You now have %d task(s) in total.\n", taskCount));
        return result.toString();
    }

    /**
     * Returns a message when multiple tasks are deleted.
     *
     * @param taskCount The total number of tasks after deleting these tasks.
     * @param tasks     The tasks that were deleted.
     * @return A formatted string with the deleted tasks and remaining count.
     */
    public String showTasksDeleted(int taskCount, Task... tasks) {

        return formatTaskList("Understood. I have removed this task:", tasks);
    }

    /**
     * Returns a message when multiple tasks are marked as done.
     *
     * @param tasks The tasks that were marked.
     * @return A formatted string with the marked tasks.
     */
    public String showTasksMarked(Task... tasks) {

        return formatTaskList("Excellent. I have marked the task(s) as complete:", tasks);

    }

    /**
     * Returns a message when multiple tasks are marked as not done.
     *
     * @param tasks The tasks that were unmarked.
     * @return A formatted string with the unmarked tasks.
     */
    public String showTasksUnmarked(Task... tasks) {

        return formatTaskList("Excellent. I have marked the task(s) as incomplete:", tasks);

    }

    /**
     * Returns a message displaying tasks found on a specific date.
     *
     * @param date  The formatted date string.
     * @param tasks The tasks on that date.
     * @return A formatted string with the tasks on the specified date.
     */
    public String showOnTasks(String date, Task... tasks) {

        if (tasks.length == 0) {
            return "I found no tasks scheduled for this date.\n";
        }

        String header = String.format("I have found these tasks scheduled for %s:", date);
        return formatTaskList(header, tasks);
    }

    /**
     * Returns a message displaying tasks found by keyword.
     *
     * @param tasks The tasks matching the keyword.
     * @return A formatted string with the matching tasks.
     */
    public String showFoundTasks(Task... tasks) {
        if (tasks.length == 0) {
            return "I found no tasks with matching keyword.\n";
        }
        return formatTaskList("Here are the matching tasks in your list:", tasks);
    }

    /**
     * Returns a message showing the priority updated tasks.
     *
     * @param tasks The tasks with updated priority.
     * @return The formatted message.
     */
    public String showPriorityUpdated(Task[] tasks) {
        return formatTaskList("Excellent. I have updated the task(s):", tasks);
    }
}
