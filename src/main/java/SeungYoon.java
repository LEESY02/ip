import task.Task;
import task.tasktypes.*;
import exceptions.*;

import java.util.ArrayList;
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
    private static ArrayList<Task> taskList = new ArrayList<Task>();

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
    private static final String DELETE = "delete";
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
    private static final String INVALID_INDEX = "Please re-enter a valid index";
    private static final String OUT_OF_BOUND_INDEX = "Please re-enter a valid command";
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
            executeCommand(nextLine);
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
    private static void executeCommand(String input) {
        Scanner sc = new Scanner(input);
        String command = sc.next();
        String taskDetails = EMPTY_STRING;
        if (sc.hasNextLine()) {
            taskDetails = sc.nextLine().strip();
        }
        sc.close();

        switch (command) {
        case LIST:
            printTaskList();
            break;
        case MARK:
            mark(taskDetails);
            break;
        case UNMARK:
            unmark(taskDetails);
            break;
        case TODO:
            addTask(TODO, taskDetails);
            break;
        case DEADLINE:
            addTask(DEADLINE, taskDetails);
            break;
        case EVENT:
            addTask(EVENT, taskDetails);
            break;
        case DELETE:
            deleteTask(taskDetails);
            break;
        default:
            printErrorMessage(RE_ENTER_COMMAND);
        }
    }
    // end of command execution algorithm

    // "list" command: Print
    private static void printTaskList() {
        System.out.print(NEW_LINE_TAB);
        for (int i = 0; i < taskList.size(); i += 1) {
            System.out.println(TAB + (i + 1) + WHITESPACE + taskList.get(i).toString());
        }
        System.out.println(String.format(TAB + "You have %d tasks", numberOfTasks));
        System.out.println(NEW_LINE_TAB);
    }
    // end of list

    // Add algorithm
    private static void addTask(String taskType, String input) {
        try {
            Task newTask = returnCorrectTask(taskType, input, numberOfTasks);
            taskList.add(newTask);
            printAddTaskMessage(newTask);
            numberOfTasks += 1;
        } catch (NoSuchElementException e) {
        //} catch (InvalidFlagException e) {
            printErrorMessage(TAG_ERROR);
        }
    }

    private static Task returnCorrectTask(String taskType, String input, int index) {
        Scanner sc = new Scanner(input);

        switch (taskType) {
        case TODO:
            return returnToDo(sc);
        case DEADLINE:
            return returnDeadline(sc);
        case EVENT:
            return returnEvent(sc);
        default:
            return returnTask(sc, input);
        }
    }

    private static ToDo returnToDo(Scanner sc) {
        String task = sc.nextLine();
        sc.close();
        return new ToDo(task.strip());
    }

    private static Deadline returnDeadline(Scanner sc) {
        String task = EMPTY_STRING;
        String currentWord = EMPTY_STRING;
        while (!currentWord.equals(DEADLINE_BY_FLAG)) {
            task += WHITESPACE + currentWord;
            //try {
            currentWord = sc.next();
            //} catch (NoSuchElementException e) {
            //    throw new InvalidFlagException(TAG_ERROR);
            //}
        }
        String deadline = sc.nextLine();
        sc.close();
        return new Deadline(task.strip(), deadline.strip());
    }

    private static Event returnEvent(Scanner sc) {
        String task = EMPTY_STRING;
        String currentWord = EMPTY_STRING;
        while (!currentWord.equals(EVENT_FROM_FLAG)) {
            task += WHITESPACE + currentWord;
            //try {
            currentWord = sc.next();
            //} catch (NoSuchElementException e) {
            //    throw new InvalidFlagException(TAG_ERROR);
            //}
        }
        String startDate = EMPTY_STRING;
        currentWord = EMPTY_STRING;
        while (!currentWord.equals(EVENT_TO_FLAG)) {
            startDate += WHITESPACE + currentWord;
            //try {
            currentWord = sc.next();
            //} catch (NoSuchElementException e) {
            //    throw new InvalidFlagException(TAG_ERROR);
            //}
        }
        String endDate = sc.nextLine();
        sc.close();
        return new Event(task.strip(), startDate.strip(), endDate.strip());

    }

    private static Task returnTask(Scanner sc, String task) {
        sc.close();
        return new Task(task);
    }

    private static void printAddTaskMessage(Task task) {
        System.out.println(
            NEW_LINE_TAB + TAB + "added: " + task.toString() + ENTER + NEW_LINE_TAB
        );
    }
    // end of add

    // Delete algorithm
    private static void deleteTask(String input) {
        try {
            int index = Integer.parseInt(input) - 1;
            printDeleteTaskMessage(index);
            taskList.remove(index);
            numberOfTasks -= 1;
        } catch (NumberFormatException e) {
            printErrorMessage(INVALID_INDEX);
        } catch (IndexOutOfBoundsException e) {
            printErrorMessage(OUT_OF_BOUND_INDEX);
        }
    }

    private static void printDeleteTaskMessage(int index) {
        System.out.println(
            NEW_LINE_TAB + TAB + "LESS WORK TO DO WOOHOOO: " + taskList.get(index).toString() + ENTER + NEW_LINE_TAB
        );
    }
    // end of delete

    // "mark" / "unmark" command: Mark as done or undone
    private static void mark(String indexString) {
        if (!indexString.isBlank()) {
            try { // in case user inputs invalid index
                int index = Integer.parseInt(indexString) - 1;
                taskList.get(index).setComplete();
                printMarkedMessage(index);
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            //} catch (InvalidIndexException e) {
                printErrorMessage(e.toString());
            }

        }
    }

    private static void unmark(String indexString) {
        if (!indexString.isBlank()) {
            try { // in case user inputs invalid index
                int index = Integer.parseInt(indexString) - 1;
                taskList.get(index).setIncomplete();
                printUnmarkedMessage(index);
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            //} catch (InvalidIndexException e) {
                printErrorMessage(e.toString());
            }
        }
    }

    private static void printMarkedMessage(int taskIndex) {
        System.out.println(
            NEW_LINE_TAB +
            TAB + "Good work brudda, the deed has been done, make sure to hydrate" + ENTER +
            TAB + TAB + taskList.get(taskIndex).toString() + ENTER +
            NEW_LINE_TAB
        );
    }

    private static void printUnmarkedMessage(int taskIndex) {
        System.out.println(
            NEW_LINE_TAB +
            TAB + "Get back to work!" + ENTER +
            TAB + TAB + taskList.get(taskIndex).toString() + ENTER +
            NEW_LINE_TAB
        );
    }
    // End of mark / unmark
}
