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
    }

}
