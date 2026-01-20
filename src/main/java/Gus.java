import java.util.ArrayList;
import java.util.Scanner;

public class Gus {
    public static final String gusPrefix = "[ GUSTAVO ]: ";
    public static final String line = "______________________________________________";


    public static void userPrefix() {
        System.out.print("[ USER ]: ");
    }

    public static void main(String[] args) {
        String logo = "          ██████╗ ██╗   ██╗███████╗\n"
                    + "         ██╔════╝ ██║   ██║██╔════╝\n"
                    + "         ██║  ███╗██║   ██║███████╗\n"
                    + "         ██║   ██║██║   ██║╚════██║\n"
                    + "         ╚██████╔╝╚██████╔╝███████║\n"
                    + "          ╚═════╝  ╚═════╝ ╚══════╝\n";
        System.out.println("\nHello and welcome to Los Pollos Hermanos");
        System.out.println("My name is Gustavo, but you can call me");
        System.out.println(line);
        System.out.println("\n\n" + logo);
        System.out.println(line);
        System.out.println("\n" + gusPrefix + "How can i help you today");


        Storage storage = new Storage("./data/gus.txt");
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            tasks = storage.loadTask();
        } catch (GusException e) {
            System.out.println(gusPrefix + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        

        while (Command.parseCommand(input) != Command.BYE) {
            try {
                Command currCommand = Command.parseCommand(input);
                
                switch (currCommand) {
                    case LIST:
                        CommandHandler.handleList(tasks);
                        break;
                    case MARK:
                        CommandHandler.handleMark(input, tasks);
                        break;
                    case UNMARK:
                        CommandHandler.handleUnmark(input, tasks);
                        break;
                    case DELETE:
                        CommandHandler.handleDelete(input, tasks);
                        break;
                    case TODO:
                        CommandHandler.handleTodo(input, tasks);
                        break;
                    case DEADLINE:
                        CommandHandler.handleDeadline(input, tasks);
                        break;
                    case EVENT:
                        CommandHandler.handleEvent(input, tasks);
                        break;
                    case ELSE:
                        throw new GusException("I dont't understand your language");
                    case BYE:
                        break;
                }
            } catch (GusException e) {
                System.out.println(gusPrefix + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(gusPrefix + "Please tell me your task number");
            }
            input = scanner.nextLine();
        }

        try {
            storage.saveTask(tasks);
        } catch (GusException e) {
            System.out.println(gusPrefix + e.getMessage());
        }

        System.out.println(gusPrefix + "Thats all for today");
        scanner.close();

    }
}
