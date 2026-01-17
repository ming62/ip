import java.util.Scanner;

public class Gus {
    public static String gusPrefix = "[ GUSTAVO ]: ";
    public static String line = "______________________________________________";
    public static String endString = "bye";
    public static String listString = "list";

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
        System.out.println("\nHello my name is Gustavo but you can call me");
        System.out.println(line);
        System.out.println("\n\n" + logo);
        System.out.println(line);
        System.out.println("\n" + gusPrefix + "How can i help you today");
        userPrefix();

        Task[] tasks = new Task[100];
        int taskCount = 0;

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals(endString)) {
            if (input.equals(listString)) {
                System.out.println(gusPrefix + "Heres your list\n");

                if (taskCount==0) {
                    System.out.println("         The list is now empty");
                }
                for (int i = 0; i < taskCount; i++) {
                    System.out.printf("         %d. %s \n", i+1, tasks[i].toString());
                }
                System.out.println();
                userPrefix();

            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                tasks[index].mark();
                System.out.printf("%sI have marked this task as done\n", gusPrefix);
                System.out.println();
                System.out.printf("          %s \n", tasks[index].toString());
                System.out.println();
                userPrefix();

            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                tasks[index].unmark();
                System.out.printf("%sI have marked this task as not done yet\n", gusPrefix);
                System.out.println();
                System.out.printf("          %s \n", tasks[index].toString());
                System.out.println();
                userPrefix();

            } else {
                System.out.printf("%sAdded %s to the list\n", gusPrefix, input);
                userPrefix();
                tasks[taskCount] = new Task(input);
                taskCount += 1;
            }
            input = scanner.nextLine();
        }

        System.out.println(gusPrefix + "Thats all for today");
        scanner.close();

    }
}
