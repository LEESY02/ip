import java.util.Arrays;
import java.util.Scanner;

public class SeungYoon {
    private static void printTaskList(String[] taskList) {
        System.out.println("\t____________________________________________________________");

        for (int i = 0; i < taskList.length; i += 1) {
            if (taskList[i] != null) {
                System.out.println("\t" + (i + 1) + ". " + taskList[i]);
            } else {
                break;
            }
        }
        System.out.println("\t____________________________________________________________\n");
    }

    private static  String[] addTask(String[] taskList, String task) {
        String[] newTaskList = new String[100];
        int i = 0;
        while (i < taskList.length) {
            if (taskList[i] != null) {
                int index = i + 1;
                newTaskList[i] = taskList[i];
            } else {
                break;
            }
            i += 1;
        }
        newTaskList[i] = task;
        return newTaskList;
    }

    public static void main(String[] args) {
        String message =
        "____________________________________________________________\n" +
        "Hello! I'm Seung Yoon\n" +
        "What can I do for you?\n" +
        "____________________________________________________________\n";
        System.out.println(message);

        Scanner sc = new Scanner(System.in);
        String nextLine = sc.nextLine();
        String[] taskList = new String[100];
        while (!nextLine.equals("bye")) {
            /*
            System.out.println(
                "\t____________________________________________________________\n" +
                "\t" + nextLine + "\n" +
                "\t____________________________________________________________\n"); */
            if (nextLine.equals("list")) {
                printTaskList(taskList);
            } else {
                taskList = addTask(taskList, nextLine);
                System.out.println(
                    "\t____________________________________________________________\n" +
                    "\tadded: " + nextLine + "\n" +
                    "\t____________________________________________________________\n"
                );
            }
            nextLine = sc.nextLine();
        }

        message =
            "\t____________________________________________________________\n" +
            "\tBye. Hope to see you again soon! (づ ᴗ _ᴗ)づ♡ ඞSUSSY\n" +
            "\t____________________________________________________________\n";
        System.out.println(message);
    }
}
