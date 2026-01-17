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

        String[] texts = new String[100];
        int textCount = 0;

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals(endString)) {
            if (input.equals(listString)) {
                System.out.println(gusPrefix + "Heres your list\n");
                for (int i = 0; i < textCount; i++) {
                    System.out.printf("         %d. %s \n", i+1, texts[i]);
                }
                System.out.println();
                userPrefix();

            } else {
                System.out.printf("%sAdded %s to the list\n", gusPrefix, input);
                userPrefix();
                texts[textCount] = input;
                textCount += 1;
            }
            input = scanner.nextLine();
        }

        System.out.println(gusPrefix + "Thats all for today");
        scanner.close();

    }
}
