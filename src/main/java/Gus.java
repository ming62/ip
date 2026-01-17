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

            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                tasks[index].mark();
                System.out.printf("%sI have marked this task as done\n", gusPrefix);
                System.out.println();
                System.out.printf("          %s \n", tasks[index].toString());
                System.out.println();

            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                tasks[index].unmark();
                System.out.printf("%sI have marked this task as not done yet\n", gusPrefix);
                System.out.println();
                System.out.printf("          %s \n", tasks[index].toString());
                System.out.println();

            } else if (input.startsWith("todo ")) {
                String title = input.substring(5);
                tasks[taskCount] = new TodoTask(title);
                taskCount++;
                System.out.printf("%sAdded to-do task to the list\n", gusPrefix);
                System.out.println();
                System.out.printf("          %s \n", tasks[taskCount - 1].toString());
                System.out.println();
                System.out.printf("%sNow we have %d tasks in the list\n", gusPrefix, taskCount);

            } else if (input.startsWith("deadline ")) {
                String[] inpStrings = input.substring(9).split(" /by ");
                tasks[taskCount] = new DeadlineTask(inpStrings[0], inpStrings[1]);
                taskCount++;
                System.out.printf("%sAdded deadline task to the list\n", gusPrefix);
                System.out.println();
                System.out.printf("          %s \n", tasks[taskCount - 1].toString());
                System.out.println();
                System.out.printf("%sNow we have %d tasks in the list\n", gusPrefix, taskCount);

            } else if (input.startsWith("event ")) {
                String[] inpStringsOne = input.substring(6).split(" /from ");
                String[] inpStringsTwo = inpStringsOne[1].split(" /to ");
                tasks[taskCount] = new EventTask(inpStringsOne[0], inpStringsTwo[0], inpStringsTwo[1]);
                taskCount++;
                System.out.printf("%sAdded event task to the list\n", gusPrefix);
                System.out.println();
                System.out.printf("          %s \n", tasks[taskCount - 1].toString());
                System.out.println();
                System.out.printf("%sNow we have %d tasks in the list\n", gusPrefix, taskCount);

            } else {
                System.out.printf("%sAdded %s to the list\n", gusPrefix, input);
                tasks[taskCount] = new Task(input);
                taskCount += 1;
            }
            input = scanner.nextLine();
        }

        System.out.println(gusPrefix + "Thats all for today");
        scanner.close();

    }
}
