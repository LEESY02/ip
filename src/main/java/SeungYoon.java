import java.util.Scanner;

public class SeungYoon {
    public static void main(String[] args) {
        String message =
        "____________________________________________________________\n" +
        "Hello! I'm Seung Yoon\n" +
        "What can I do for you?\n" +
        "____________________________________________________________\n";
        System.out.println(message);

        Scanner sc = new Scanner(System.in);
        String nextLine = sc.nextLine();
        while (!nextLine.equals("bye")) {
            System.out.println(
                "\t____________________________________________________________\n" +
                "\t" + nextLine + "\n" +
                "\t____________________________________________________________\n");
            nextLine = sc.nextLine();
        }

        message =
            "\t____________________________________________________________\n" +
            "\tBye. Hope to see you again soon! (づ ᴗ _ᴗ)づ♡ ඞSUSSY\n" +
            "\t____________________________________________________________\n";
        System.out.println(message);
    }
}
