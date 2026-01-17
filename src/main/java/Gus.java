import java.util.Scanner;

public class Gus {
    public static String gusPrefix = "[ GUSTAVO ]: ";
    public static String line = "______________________________________________";
    public static String endString = "bye";

    public static void main(String[] args) {

        String logo = "          ██████╗ ██╗   ██╗███████╗\n"
                    + "         ██╔════╝ ██║   ██║██╔════╝\n"
                    + "         ██║  ███╗██║   ██║███████╗\n"
                    + "         ██║   ██║██║   ██║╚════██║\n"
                    + "         ╚██████╔╝╚██████╔╝███████║\n"
                    + "          ╚═════╝  ╚═════╝ ╚══════╝\n";
        System.out.println("\nHello my name is Gustavo but you can call me" );
        System.out.println(line);
        System.out.println("\n\n" + logo);
        System.out.println(line);
        System.out.println("\n" + gusPrefix + "How can i help you today");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals(endString)) {
            System.out.println(gusPrefix + input);
            input = scanner.nextLine();
        }

        System.out.println(gusPrefix + "Thats all for today");
        scanner.close();

    }
}
