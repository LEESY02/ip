import java.util.Arrays;
import java.util.Scanner;

/*
 * Coding standard is defined by IDE
 * lowerCamelCase for variables and object instances
 * UpperCamelCase for Class names
 * ALL_CAPS_WITH_UNDERSCORE for constants
 * Print messages encapsulated in private static methods for readability
 */

public class SeungYoon {
    private static int numberOfTasks = 0;

    // String Constants
    public static final String NEW_LINE = "____________________________________________________________\n";
    public static final String NEW_LINE_TAB = "\t____________________________________________________________\n";
    public static final String LINE_TAB = "\t____________________________________________________________";
    public static final String TAB = "\t";
    public static final String ENTER = "\n";
    public static final String EMPTY_STRING = "";
    public static final String WHITESPACE = " ";

    // Command names
    public static final String LIST = "list";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String EXIT_APP = "bye";

    // Task names
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";

    // Flags i.e.: /flagName
    public static final String DEADLINE_BY_FLAG = "/by";
    public static final String EVENT_FROM_FLAG = "/from";
    public static final String EVENT_TO_FLAG = "/to";


    // Main
    public static void main(String[] args) {
        printIntroMessage();

        Scanner sc = new Scanner(System.in);
        String nextLine = sc.nextLine();

        Task[] taskList = new Task[100];
        while (!nextLine.equals(EXIT_APP)) {
            taskList = executeCommand(taskList, nextLine);
            nextLine = sc.nextLine();
        }
        sc.close();

        printOutroMessage();
    }

    private static void printIntroMessage() {
        System.out.println(NEW_LINE + "Hello! I'm Seung Yoon" + TAB + "What can I do for you?" + ENTER + NEW_LINE);
    }

    private static void printOutroMessage() {
        System.out.print(NEW_LINE_TAB + TAB + "Bye. Hope to see you again soon!" + ENTER + LINE_TAB);
    }
    // End of Main

    // Execute commands based on the 1st word of the input
    private static Task[] executeCommand(Task[] taskList, String input) {
        Scanner sc = new Scanner(input);
        String command = sc.next();
        String task = EMPTY_STRING;
        if (sc.hasNextLine()) {
            task = sc.nextLine().strip();
        }
        sc.close();

        switch (command) {
        case LIST:
            printTaskList(taskList);
            break;
        case MARK:
            mark(taskList, task);
            break;
        case UNMARK:
            unmark(taskList, task);
            break;
        default:
            taskList = addTask(taskList, input);
        }

        return taskList;
    }
    // end of command execution algorithm

    // "list" command: Print
    private static void printTaskList(Task[] taskList) {
        System.out.print(NEW_LINE_TAB);
        for (int i = 0; i < taskList.length; i += 1) {
            if (taskList[i] != null) {
                System.out.println(TAB + taskList[i].toString());
            } else {
                break;
            }
        }
        System.out.println(String.format(TAB + "You have %d tasks", numberOfTasks));
        System.out.println(NEW_LINE_TAB);
    }
    // end of list

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

        switch (taskType) {
        case TODO:
            return returnToDo(sc, index);
        case DEADLINE:
            return returnDeadline(sc, index);
        case EVENT:
            return returnEvent(sc, index);
        default:
            return returnTask(sc, input, index);
        }
    }

    private static ToDo returnToDo(Scanner sc, int index) {
        String task = sc.nextLine();
        sc.close();
        return new ToDo(task.strip(), index);
    }

    private static Deadline returnDeadline(Scanner sc, int index) {
        String task = EMPTY_STRING;
        String currentWord = EMPTY_STRING;
        while (!currentWord.equals(DEADLINE_BY_FLAG)) {
            task += WHITESPACE + currentWord;
            currentWord = sc.next();
        }
        String deadline = sc.nextLine();
        sc.close();
        return new Deadline(task.strip(), index, deadline.strip());
    }

    private static Event returnEvent(Scanner sc, int index) {
        String task = EMPTY_STRING;
        String currentWord = EMPTY_STRING;
        while (!currentWord.equals(EVENT_FROM_FLAG)) {
            task += WHITESPACE + currentWord;
            currentWord = sc.next();
        }
        String startDate = EMPTY_STRING;
        currentWord = EMPTY_STRING;
        while (!currentWord.equals(EVENT_TO_FLAG)) {
            startDate += WHITESPACE + currentWord;
            currentWord = sc.next();
        }
        String endDate = sc.nextLine();
        sc.close();
        return new Event(task.strip(), index, startDate.strip(), endDate.strip());
    }

    private static Task returnTask(Scanner sc, String task, int index) {
        sc.close();
        return new Task(task, index);
    }

    private static void printAddTaskMessage(Task task) {
        System.out.println(
            NEW_LINE_TAB + TAB + "added: " + task.toString() + ENTER + NEW_LINE_TAB
        );
    }
    // end of add

    // "mark" / "unmark" command: Mark as done or undone
    private static void mark(Task[] taskList, String indexString) {
        if (!indexString.isBlank()) {
            int index = Integer.parseInt(indexString) - 1;
            taskList[index].setComplete();
            printMarkedMessage(taskList, index);
        }
    }

    private static void unmark(Task[] taskList, String indexString) {
        if (!indexString.isBlank()) {
            int index = Integer.parseInt(indexString) - 1;
            taskList[index].setIncomplete();
            printUnmarkedMessage(taskList, index);
        }
    }

    private static void printMarkedMessage(Task[] taskList, int taskIndex) {
        System.out.println(
            NEW_LINE_TAB +
            TAB + "Good work brudda, the deed has been done, make sure to hydrate" + ENTER +
            TAB + TAB + taskList[taskIndex].toString() + ENTER +
            NEW_LINE_TAB
        );
    }

    private static void printUnmarkedMessage(Task[] taskList, int taskIndex) {
        System.out.println(
            NEW_LINE_TAB +
            TAB + "Get back to work!" + ENTER +
            TAB + TAB + taskList[taskIndex].toString() + ENTER +
            NEW_LINE_TAB
        );
    }
    // End of mark / unmark
}
