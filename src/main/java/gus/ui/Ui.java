package gus.ui;
import java.util.Scanner;

public class Ui {
    public static final String GUS_PREFIX = "[ GUSTAVO ]: ";
    public static final String LINE = "______________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo  =  "         /$$$$$$  /$$   /$$  /$$$$$$        \n"
                      + "       /$$__  $$| $$  | $$ /$$__  $$        \n"
                      + "      | $$  \\__/| $$  | $$| $$  \\__/       \n"
                      + "      | $$ /$$$$| $$  | $$|  $$$$$$        \n"
                      + "      | $$|_  $$| $$  | $$ \\____  $$       \n"
                      + "      | $$  \\ $$| $$  | $$ /$$  \\ $$       \n"
                      + "      |  $$$$$$/|  $$$$$$/|  $$$$$$/       \n"
                      + "      \\______/  \\______/  \\______/         \n";
                                    
                                    
                                    
        System.out.println("\nHello and welcome to Los Pollos Hermanos");
        System.out.println("My name is Gustavo, but you can call me");
        System.out.println(LINE);
        System.out.println("\n\n" + logo);
        System.out.println(LINE);
        System.out.println("\n" + GUS_PREFIX + "How can i help you today");
    }



    public void showBye() {
        System.out.println(GUS_PREFIX + "Thats all for today");
    }

    public String readCommand() {
        System.out.print(">> ");
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }

    public void showText(String input) {
        System.out.println(GUS_PREFIX + input);
    }

    public void showTaskList(String list) {
        System.out.println(GUS_PREFIX + "Heres your list:\n");
        System.out.println(list);
    }

    public void showTaskAdded(String task, int taskCount) {
        System.out.println(GUS_PREFIX + "Added task to the list");
        System.out.println();
        System.out.printf("          %s \n", task);
        System.out.println();
        System.out.printf(GUS_PREFIX + "Now we have %d tasks in the list\n", taskCount);
    }

    public void showTaskDeleted(String task, int taskCount) {
        System.out.println(GUS_PREFIX + "I removed this task from the list");
        System.out.println();
        System.out.printf("          %s \n", task);
        System.out.println();
        System.out.printf(GUS_PREFIX + "Now we have %d tasks in the list\n", taskCount);
    }

    public void showTaskMarked(String task) {
        System.out.printf(GUS_PREFIX + "I have marked this task as done\n");
        System.out.println();
        System.out.printf("          %s \n", task);
        System.out.println();
    }

    public void showTaskUnmarked(String task) {
        System.out.printf(GUS_PREFIX + "I have marked this task as not done yet\n");
        System.out.println();
        System.out.printf("          %s \n", task);
        System.out.println();
    }

    public void showFoundTasks(String date, String foundTasks) {
        System.out.printf(GUS_PREFIX + "Here are the tasks on %s:\n", date);
        System.out.println();
        System.out.println(foundTasks);
    }
}
