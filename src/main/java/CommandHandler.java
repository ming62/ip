import java.time.DateTimeException;
import java.util.ArrayList;

public class CommandHandler {

    private static final String gusPrefix = "[ GUSTAVO ]: ";

    public static void handleList(ArrayList<Task> tasks) {

        System.out.println(gusPrefix + "Heres your list\n");

        if (tasks.isEmpty()) {
            System.out.println("         The list is now empty");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("         %d. %s \n", i + 1, tasks.get(i).toString());
        }
        System.out.println();

    }

    public static void handleMark(String input, ArrayList<Task> tasks) throws GusException {

        if (input.length() <= 5 || input.substring(5).trim().isEmpty()) {
            throw new GusException("You must tell me which task to mark");
        }

        int index = Integer.parseInt(input.substring(5).trim()) - 1;

        if (index < 0 || index >= tasks.size()) {
            throw new GusException("Task number is invalid!");
        }

        tasks.get(index).mark();
        System.out.printf("%sI have marked this task as done\n", gusPrefix);
        System.out.println();
        System.out.printf("          %s \n", tasks.get(index).toString());
        System.out.println();
    }

    public static void handleUnmark(String input, ArrayList<Task> tasks) throws GusException {

        if (input.length() <= 7 || input.substring(7).trim().isEmpty()) {
            throw new GusException("You must tell me which task to unmark");
        }

        int index = Integer.parseInt(input.substring(7).trim()) - 1;

        if (index < 0 || index >= tasks.size()) {
            throw new GusException("Task number is invalid!");
        }

        tasks.get(index).unmark();
        System.out.printf("%sI have marked this task as not done yet\n", gusPrefix);
        System.out.println();
        System.out.printf("          %s \n", tasks.get(index).toString());
        System.out.println();
    }


    public static void handleDelete(String input, ArrayList<Task> tasks) throws GusException {
        
        if (input.length() <= 7 || input.substring(7).trim().isEmpty()) {
            throw new GusException("You must tell me which task to delete");
        }

        int index = Integer.parseInt(input.substring(7)) - 1;
        
        if (index < 0 || index >= tasks.size()) {
            throw new GusException("Task number is invalid!");
        }

        Task curr = tasks.remove(index);
        System.out.printf("%sI removed this task from the list\n", gusPrefix);
        System.out.println();
        System.out.printf("          %s \n", curr.toString());
        System.out.println();
        System.out.printf("%sNow we have %d tasks in the list\n", gusPrefix, tasks.size());
    }

    public static void handleTodo(String input, ArrayList<Task> tasks) throws GusException {

        if (input.length() <= 5) {
            throw new GusException("Hmm I don't know what is the title for this task");
        }

        String title = input.substring(5).trim();

        if (title.isEmpty()) {
            throw new GusException("Hmm I don't know what is the title for this task");
        }

        Task curr = new TodoTask(title);
        tasks.add(curr);
        System.out.printf("%sAdded to-do task to the list\n", gusPrefix);
        System.out.println();
        System.out.printf("          %s \n", curr.toString());
        System.out.println();
        System.out.printf("%sNow we have %d tasks in the list\n", gusPrefix, tasks.size());
    }

    public static void handleDeadline(String input, ArrayList<Task> tasks) throws GusException {

        if (input.length() <= 9) {
            throw new GusException("I need more details to create this task!");
        }

        String details = input.substring(9).trim();

        if (details.isEmpty()) {
            throw new GusException("I need more details to create this task!");
        }

        if (!details.contains(" /by ")) {
            throw new GusException("Give me your deadline with /by!");
        }

        String[] inpStrings = details.split(" /by ");

        try {
            Task curr = new DeadlineTask(inpStrings[0], inpStrings[1]);
            tasks.add(curr);
            System.out.printf("%sAdded deadline task to the list\n", gusPrefix);
            System.out.println();
            System.out.printf("          %s \n", curr.toString());
            System.out.println();
            System.out.printf("%sNow we have %d tasks in the list\n", gusPrefix, tasks.size());
        } catch (DateTimeException e) {
            throw new GusException("Give me your deadline date by the format yyyy-MM-dd HHmm ");
        }
    }

    public static void handleEvent(String input, ArrayList<Task> tasks) throws GusException {

        if (input.length() <= 6) {
            throw new GusException("I need more details to create this task!");
        }

        String details = input.substring(6).trim();

        if (details.isEmpty()) {
            throw new GusException("I need more details to create this task!");
        }

        if (!details.contains(" /from ") || !details.contains(" /to ")) {
            throw new GusException(
                    "Give me both from and to timeline with /from then /to!");
        }

        String[] inpStringsOne = details.split(" /from ");
        String[] inpStringsTwo = inpStringsOne[1].split(" /to ");

        try {
            Task curr = new EventTask(inpStringsOne[0], inpStringsTwo[0], inpStringsTwo[1]);
            tasks.add(curr);
            System.out.printf("%sAdded event task to the list\n", gusPrefix);
            System.out.println();
            System.out.printf("          %s \n", curr.toString());
            System.out.println();
            System.out.printf("%sNow we have %d tasks in the list\n", gusPrefix, tasks.size());
        } catch (DateTimeException e) {
            throw new GusException("Give me your dates by the format yyyy-MM-dd HHmm ");
        }
    }

}
