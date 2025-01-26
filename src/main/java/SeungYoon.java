import java.util.Arrays;
import java.util.Scanner;

public class SeungYoon {
    private static void printTaskList(Task[] taskList) {
        System.out.println("\t____________________________________________________________");

        for (int i = 0; i < taskList.length; i += 1) {
            if (taskList[i] != null) {
                System.out.println("\t" + taskList[i].toString());
            } else {
                break;
            }
        }
        System.out.println("\t____________________________________________________________\n");
    }

    private static Task[] addTask(Task[] taskList, String task) {
        Task[] newTaskList = new Task[100];
        int i = 0;
        while (i < taskList.length) {
            if (taskList[i] != null) {
                newTaskList[i] = taskList[i];
            } else {
                break;
            }
            i += 1;
        }
        newTaskList[i] = new Task(task, i + 1);
        System.out.println(
            "\t____________________________________________________________\n" +
            "\tadded: " + task + "\n" +
            "\t____________________________________________________________\n"
        );
        return newTaskList;
    }

    private static void flipCompletionStatus(Task[] taskList, int taskIndex, boolean completionStatus) {
        if (completionStatus) {
            taskList[taskIndex].setComplete();
            System.out.println(
                "\t_________________________________________________________\n" +
                "\tGood work brudda, the deed has been done, make sure to hydrate ദ്ദി(˵ •̀ ᴗ - ˵ ) ✧\n" +
                "\t\t" + taskList[taskIndex].toString() + "\n" +
                "\t_________________________________________________________\n"
            );
        } else {
            taskList[taskIndex].setIncomplete();
            System.out.println(
                "\t_________________________________________________________\n" +
                    "\tGet back to work, you bum ⁽⁽(੭ꐦ •̀Д•́ )੭*⁾⁾\n" +
                    "\t\t" + taskList[taskIndex].toString() + "\n" +
                    "\t_________________________________________________________\n"
            );
        }
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
        //String[] taskList = new String[100];
        Task[] taskList = new Task[100];
        while (!nextLine.equals("bye")) {
            /*
            System.out.println(
                "\t____________________________________________________________\n" +
                "\t" + nextLine + "\n" +
                "\t____________________________________________________________\n"); */
            if (nextLine.equals("list")) {
                printTaskList(taskList);
            } else if (nextLine.startsWith("mark", 0)) {
                if (nextLine.length() > 4)
                    flipCompletionStatus(taskList, Integer.parseInt(nextLine.substring(5)) - 1, true);
            } else if (nextLine.startsWith("unmark", 0)) {
                if (nextLine.length() > 6)
                    flipCompletionStatus(taskList, Integer.parseInt(nextLine.substring(7)) - 1, false);
            } else {
                taskList = addTask(taskList, nextLine);
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
