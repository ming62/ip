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

    /**
     * Displays the list of all tasks.
     *
     * @param list The string of the whole task list.
     */
    public void showTaskList(String list) {
        System.out.println(GUS_PREFIX + "Here is your task list, as requested:\n");
        System.out.println(list);
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
     * Displays a message when a task is deleted.
     *
     * @param task      The string of the deleted task.
     * @param taskCount The total number of tasks after deleting this task.
     */
    public void showTaskDeleted(String task, int taskCount) {
        System.out.println(GUS_PREFIX + "Understood. I have removed this task:");
        System.out.println();
        System.out.printf("          %s \n", task);
        System.out.println();
        System.out.printf(GUS_PREFIX + "You now have %d task(s) remaining.\n", taskCount);
    }

    /**
     * Returns a message when multiple tasks are deleted.
     *
     * @param taskCount The total number of tasks after deleting these tasks.
     * @param tasks     The tasks that were deleted.
     * @return A formatted string with the deleted tasks and remaining count.
     */
    public String showTasksDeleted(int taskCount, Task... tasks) {
        StringBuilder result = new StringBuilder();
        result.append("Understood. I have removed this task:\n");
        result.append("\n");
        for (Task task : tasks) {
            result.append(String.format("%s \n", task.toString()));
        }
        result.append("\n");
        result.append(String.format("You now have %d task(s) remaining.\n", taskCount));
        return result.toString();
    }

    /**
     * Returns a message when multiple tasks are marked as done.
     *
     * @param tasks The tasks that were marked.
     * @return A formatted string with the marked tasks.
     */
    public String showTasksMarked(Task... tasks) {
        StringBuilder result = new StringBuilder();
        result.append("Excellent. I have marked the task(s) as complete:\n");
        result.append("\n");
        for (Task task : tasks) {
            result.append(String.format("%s \n", task.toString()));
        }
        result.append("\n");
        return result.toString();
    }

    /**
     * Returns a message when multiple tasks are marked as not done.
     *
     * @param tasks The tasks that were unmarked.
     * @return A formatted string with the unmarked tasks.
     */
    public String showTasksUnmarked(Task... tasks) {
        StringBuilder result = new StringBuilder();
        result.append("Excellent. I have marked the task(s) as incomplete:\n");
        result.append("\n");
        for (Task task : tasks) {
            result.append(String.format("%s \n", task.toString()));
        }
        result.append("\n");
        return result.toString();
    }

    /**
     * Returns a message displaying tasks found on a specific date.
     *
     * @param date  The formatted date string.
     * @param tasks The tasks on that date.
     * @return A formatted string with the tasks on the specified date.
     */
    public String showOnTasks(String date, Task... tasks) {
        StringBuilder result = new StringBuilder();

        if (tasks.length == 0) {
            result.append("I found no tasks scheduled for this date.\n");
        } else {
            result.append(String.format("I have found these tasks scheduled for %s:\n", date));
            result.append("\n");
            for (Task task : tasks) {
                result.append(String.format("%s \n", task.toString()));
            }
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * Returns a message displaying tasks found by keyword.
     *
     * @param tasks The tasks matching the keyword.
     * @return A formatted string with the matching tasks.
     */
    public String showFoundTasks(Task... tasks) {
        StringBuilder result = new StringBuilder();
        if (tasks.length == 0) {
            result.append("I found no tasks with matching keyword.\n");
        } else {
            result.append("Here are the matching tasks in your list:\n");
            result.append("\n");
            for (Task task : tasks) {
                result.append(String.format("%s \n", task.toString()));
            }
            result.append("\n");
        }
        return result.toString();
    }
}
