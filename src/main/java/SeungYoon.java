import java.util.NoSuchElementException;
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
    private static Task[] taskList = new Task[100];

    // String Constants
    private static final String NEW_LINE = "____________________________________________________________\n";
    private static final String NEW_LINE_TAB = "\t____________________________________________________________\n";
    private static final String LINE_TAB = "\t____________________________________________________________";
    private static final String TAB = "\t";
    private static final String ENTER = "\n";
    private static final String EMPTY_STRING = "";
    private static final String WHITESPACE = " ";

    // Command names
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String EXIT_APP = "bye";

    // Task names
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    // Flags i.e.: /flagName
    private static final String DEADLINE_BY_FLAG = "/by";
    private static final String EVENT_FROM_FLAG = "/from";
    private static final String EVENT_TO_FLAG = "/to";

    // Error message
    private static final String ERROR_MESSAGE = NEW_LINE_TAB + TAB + "Error caught: %s" + ENTER + NEW_LINE_TAB;
    private static final String RE_ENTER_COMMAND = "Please re-enter a valid command";
    private static final String TAG_ERROR = "Please re-enter a valid tag" + ENTER +
        TAB + TAB + "- '/by' for deadline" + ENTER +
        TAB + TAB + "- '/from' and '/to' for event";
    //private static final String IOOBE = "Integer out of bounds";
    private static void printErrorMessage(String errorType) {
        System.out.println(String.format(ERROR_MESSAGE, errorType));
    }


    // Main
    public static void main(String[] args) {
        printIntroMessage();

        Scanner sc = new Scanner(System.in);
        String nextLine = sc.nextLine();

        while (!nextLine.equals(EXIT_APP)) {
            executeCommand(taskList, nextLine);
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
    private static void executeCommand(Task[] taskList, String input) {
        Scanner sc = new Scanner(input);
        String command = sc.next();
        String taskDetails = EMPTY_STRING;
        if (sc.hasNextLine()) {
            taskDetails = sc.nextLine().strip();
        }
        sc.close();

        switch (command) {
        case LIST:
            printTaskList(taskList);
            break;
        case MARK:
            mark(taskList, taskDetails);
            break;
        case UNMARK:
            unmark(taskList, taskDetails);
            break;
        case TODO:
            addTask(taskList, TODO, taskDetails);
            break;
        case DEADLINE:
            addTask(taskList, DEADLINE, taskDetails);
            break;
        case EVENT:
            addTask(taskList, EVENT, taskDetails);
            break;
        default:
            printErrorMessage(RE_ENTER_COMMAND);
        }
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
    private static void addTask(Task[] taskList, String taskType, String input) {
        try {
            Task newTask = returnCorrectTask(taskType, input, numberOfTasks);
            taskList[numberOfTasks] = newTask;
            printAddTaskMessage(newTask);

            numberOfTasks += 1;
        } catch (NoSuchElementException e) {
            printErrorMessage(TAG_ERROR);
        }
    }

    private static Task returnCorrectTask(String taskType, String input, int index) {
        Scanner sc = new Scanner(input);

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
            try { // in case user inputs invalid index
                int index = Integer.parseInt(indexString) - 1;
                taskList[index].setComplete();
                printMarkedMessage(taskList, index);
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                printErrorMessage(e.toString());
            }

        }
    }

    private static void unmark(Task[] taskList, String indexString) {
        if (!indexString.isBlank()) {
            try { // in case user inputs invalid index
                int index = Integer.parseInt(indexString) - 1;
                taskList[index].setIncomplete();
                printUnmarkedMessage(taskList, index);
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                printErrorMessage(e.toString());
            }
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
