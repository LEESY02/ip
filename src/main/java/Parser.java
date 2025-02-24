import java.util.Scanner;

import static data.Constants.*;

public class Parser {

    /*
     * Parses commands based on the 1st word of the input
     * @param input The string input taken by the UI class
     * @return a new Command instance
     */
    public static Command parseCommand(String input) {
        Scanner sc = new Scanner(input);
        String command = sc.next();
        String taskDetails = EMPTY_STRING;
        if (sc.hasNextLine()) {
            taskDetails = sc.nextLine().strip();
        }
        sc.close();

        return new Command(command, taskDetails);
        /*
        switch (command) {
        case LIST:
            return new Command(LIST, taskDetails);
        case MARK:
            return new Command(MARK, taskDetails);
        case UNMARK:
            return new Command(UNMARK, taskDetails);
        case TODO:
            return new Command(TODO, taskDetails);
        case DEADLINE:
            return new Command(DEADLINE, taskDetails);
        case EVENT:
            return new Command(EVENT, taskDetails);
        case DELETE:
            return new Command(DELETE, taskDetails);
        case DUE:
            return new Command(DUE, taskDetails);
        case OVERDUE:
            return new Command(OVERDUE, taskDetails);
        case SEARCH:
            return new Command(SEARCH, taskDetails);
        case EXIT_APP:
            return new Command(EXIT_APP, taskDetails);
        default:
            return new Command("INVALID COMMAND", EMPTY_STRING);
        } */
    }
    // end of command execution algorithm
}
