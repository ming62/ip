package gus.ui;
import java.util.Scanner;

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
        String logo  =  "         /$$$$$$  /$$   /$$  /$$$$$$        \n"
                      + "       /$$__  $$| $$  | $$ /$$__  $$        \n"
                      + "      | $$  \\__/| $$  | $$| $$  \\__/       \n"
                      + "      | $$ /$$$$| $$  | $$|  $$$$$$        \n"
                      + "      | $$|_  $$| $$  | $$ \\____  $$       \n"
                      + "      | $$  \\ $$| $$  | $$ /$$  \\ $$       \n"
                      + "      |  $$$$$$/|  $$$$$$/|  $$$$$$/       \n"
                      + "      \\______/  \\______/  \\______/         \n";
                                    
                                    
                                    
        System.out.println("\nBuenos días. Welcome to Los Pollos Hermanos.");
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
        System.out.println(GUS_PREFIX + "It has been a pleasure serving you. Adiós.");
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
     * @param input The message to display.
     */
    public void showText(String input) {
        System.out.println(GUS_PREFIX + input);
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
     * Displays a message when a task is added.
     *
     * @param task The string of the added task.
     * @param taskCount The total number of tasks after adding this task.
     */
    public void showTaskAdded(String task, int taskCount) {
        System.out.println(GUS_PREFIX + "Very well. I have added this task:");
        System.out.println();
        System.out.printf("          %s \n", task);
        System.out.println();
        System.out.printf(GUS_PREFIX + "You now have %d task(s) in total.\n", taskCount);
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task The string of the deleted task.
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
     * Displays a message when a task is marked as done.
     *
     * @param task The string of the marked task.
     */
    public void showTaskMarked(String task) {
        System.out.printf(GUS_PREFIX + "Excellent. I have marked this task as complete:\n");
        System.out.println();
        System.out.printf("          %s \n", task);
        System.out.println();
    }

    /**
     * Displays a message when a task is unmarked.
     *
     * @param task The string of the unmarked task.
     */
    public void showTaskUnmarked(String task) {
        System.out.printf(GUS_PREFIX + "Very well. I have marked this task as incomplete:\n");
        System.out.println();
        System.out.printf("          %s \n", task);
        System.out.println();
    }

    /**
     * Displays tasks found on a specific date.
     *
     * @param date The formatted date string.
     * @param foundTasks The string of tasks on that date.
     */
    public void showFoundTasks(String date, String foundTasks) {
        System.out.printf(GUS_PREFIX + "I have found these tasks scheduled for %s:\n", date);
        System.out.println();
        System.out.println(foundTasks);
    }
}
