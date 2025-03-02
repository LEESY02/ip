package data;

/*
 * A Class of static constants used by other classes
 */
public final class Constants {
    private Constants() {
        // No need to instantiate the class, we can hide its constructor
    }

    // String Constants
    public static final String NEW_LINE = "____________________________________________________________\n";
    public static final String NEW_LINE_TAB = "\t____________________________________________________________\n";
    public static final String LINE = "____________________________________________________________";
    public static final String TAB = "\t";
    public static final String ENTER = "\n";
    public static final String EMPTY_STRING = "";
    public static final String WHITESPACE = " ";
    public static final String DOT = ".";
    public static final String STRING_SEPARATOR = " | ";

    // File path and saving
    public static final String SAVE_FILE = "lastKnownState.txt";
    public static final int TASK_TYPE_INDEX = 0;
    public static final int COMPLETION_INDEX = 2;
    public static final int DESCRIPTION_INDEX = 4;
    public static final String INCOMPLETE_FLAG = "0";
    public static final String COMPLETED_FLAG = "1";
    public static String saveFormat(String taskType, String completionState, String description) {
        return String.format("%s" + STRING_SEPARATOR + "%s" + STRING_SEPARATOR + "%s\n",
            taskType, completionState, description);
    }
    public static String deadlineDescriptor(String mainDescription, String deadline) {
        return String.format("%s " + DEADLINE_BY_FLAG + " %s", mainDescription, deadline);
    }
    public static String eventDescriptor(String mainDescription, String from, String to) {
        return String.format("%s " + EVENT_FROM_FLAG + " %s " + EVENT_TO_FLAG +" %s", mainDescription, from, to);
    }

    // Tasktype related flags
    public static final String COMPLETE_MARK = "[X] ";
    public static final String INCOMPLETE_MARK = "[ ] ";
    public static final String TASK_TYPE = "[ ]";
    public static final String TASK_TYPE_TODO = "[T]";
    public static final String TASK_TYPE_DEADLINE = "[D]";
    public static final String TASK_TYPE_EVENT = "[E]";


    // Command names
    public static final String LIST = "list";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String DELETE = "delete";
    public static final String DUE = "due";
    public static final String OVERDUE = "overdue";
    public static final String SEARCH = "search";
    public static final String EXIT_APP = "bye";

    // Task names
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";

    // Flags i.e.: /flagName
    public static final String DEADLINE_BY_FLAG = "/by";
    public static final String EVENT_FROM_FLAG = "/from";
    public static final String EVENT_TO_FLAG = "/to";

    // Error message
    public static final String ERROR_MESSAGE = NEW_LINE_TAB + TAB + "Error caught: %s" + ENTER + NEW_LINE_TAB;
    public static final String RE_ENTER_COMMAND = "Please re-enter a valid command";
    public static final String INVALID_INDEX = "Please re-enter a valid index (i.e.: 'delete 1' to delete task number 1)";
    public static final String TAG_ERROR = "Please re-enter a valid tag" + ENTER +
        TAB + TAB + "- '/by' for deadline" + ENTER +
        TAB + TAB + "- '/from' and '/to' for event" + ENTER +
        TAB + TAB + "- Dates must be in the format of 'YYYY-MM-DD' (Time values not accepted)";
    public static final String INVALID_DATE = "Invalid date format (Must be: 'YYYY-MM-DD')";
    public static final String INVALID_SEARCH_INPUT = "Invalid search input (Check if you have input a valid string)";
    public static void printErrorMessage(String errorType) {
        System.out.println(String.format(ERROR_MESSAGE, errorType));
    }
}
