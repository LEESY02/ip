import java.util.Arrays;
import java.util.Scanner;

public class SeungYoon {
    private static int numberOfTasks = 0;

    public static final String NEW_LINE = "____________________________________________________________\n";
    public static final String NEW_LINE_TAB = "\t____________________________________________________________\n";
    public static final String LINE_TAB = "\t____________________________________________________________";

    // "list" command: Print
    private static void printTaskList(Task[] taskList) {
        System.out.print(NEW_LINE_TAB);
        for (int i = 0; i < taskList.length; i += 1) {
            if (taskList[i] != null) {
                System.out.println("\t" + String.format("%d.", i + 1) + taskList[i].toString());
            } else {
                break;
            }
        }
        System.out.println(String.format("\tYou have %d tasks", numberOfTasks));
        System.out.println(NEW_LINE_TAB);
    }

    // Add algorithm
    private static Task[] addTask(Task[] taskList, String input) {
        Task newTask = returnCorrectTask(input, numberOfTasks);
        taskList[numberOfTasks] = newTask;
        printAddTaskMessage(newTask);

        numberOfTasks += 1;
        return taskList;
    }

    private static Task returnCorrectTask(String input, int index) {
        Scanner sc = new Scanner(input);
        String taskType = sc.next();
        String task = "";
        String currentWord = "";

        switch (taskType) {
        case "todo":
            task = sc.nextLine();
            sc.close();
            return new ToDo(task.strip(), index);
        case "deadline":
            task = "";
            currentWord = "";
            while (!currentWord.equals("/by")) {
                task += " " + currentWord;
                currentWord = sc.next();
            }
            String deadline = sc.nextLine();
            sc.close();
            return new Deadline(task.strip(), index, deadline.strip());
        case "event":
            task = "";
            currentWord = "";
            while (!currentWord.equals("/from")) {
                task += " " + currentWord;
                currentWord = sc.next();
            }
            String startDate = "";
            currentWord = "";
            while (!currentWord.equals("/to")) {
                startDate += " " + currentWord;
                currentWord = sc.next();
            }
            String endDate = sc.nextLine();
            sc.close();
            return new Event(task.strip(), index, startDate.strip(), endDate.strip());
        default:
        }

        sc.close();
        return new Task(input, index);
    }

    private static void printAddTaskMessage(Task task) {
        System.out.println(
            NEW_LINE_TAB + "\tadded: " + task.toString() + "\n" + NEW_LINE_TAB
        );
    }
    // end of add algorithm

    // "mark" / "unmark" command: Mark as done or undone
    private static void flipCompletionStatus(Task[] taskList, int taskIndex, boolean completionStatus) {
        if (completionStatus) {
            taskList[taskIndex].setComplete();

            System.out.println(
                NEW_LINE_TAB +
                "\tGood work brudda, the deed has been done, make sure to hydrate \n" +
                "\t\t" + taskList[taskIndex].toString() + "\n" +
                NEW_LINE_TAB
            );
        } else {
            taskList[taskIndex].setIncomplete();

            System.out.println(
                NEW_LINE_TAB +
                "\tGet back to work!\n" +
                "\t\t" + taskList[taskIndex].toString() + "\n" +
                NEW_LINE_TAB
            );
        }
    }

    // Execute commands based on the 1st word of the input
    private static Task[] executeCommand(Task[] taskList, String input) {
        Scanner sc = new Scanner(input);
        String command = sc.next();
        String task = "";
        if (sc.hasNextLine()) {
            task = sc.nextLine().strip();
        }
        sc.close();

        switch (command) {
        case "list":
            printTaskList(taskList);
            break;
        case "mark":
            if (!task.isBlank()) {
                int index = Integer.parseInt(task) - 1;
                flipCompletionStatus(taskList, index, true);
            }
            break;
        case "unmark":
            if (!task.isBlank()) {
                int index = Integer.parseInt(task) - 1;
                flipCompletionStatus(taskList, index, false);
            }
            break;
        default:
            taskList = addTask(taskList, input);
        }

        return taskList;
    }

    public static void main(String[] args) {
        printIntroMessage();

        Scanner sc = new Scanner(System.in);
        String nextLine = sc.nextLine();

        Task[] taskList = new Task[100];
        while (!nextLine.equals("bye")) {
            taskList = executeCommand(taskList, nextLine);
            nextLine = sc.nextLine();
        }

        printOutroMessage();
    }

    private static void printIntroMessage() {
        String message =
            NEW_LINE + "Hello! I'm Seung Yoon\n" + "What can I do for you?\n" + NEW_LINE;
        System.out.println(message);
    }
    private static void printOutroMessage() {
        String message =
            NEW_LINE_TAB + "\tBye. Hope to see you again soon!\n" + LINE_TAB;
        System.out.print(message);
    }
}
