//import exceptions.*;

import static data.Constants.*;

/*
 * Coding standard is defined by IDE
 * lowerCamelCase for variables and object instances
 * UpperCamelCase for Class names
 * ALL_CAPS_WITH_UNDERSCORE for constants
 * Print messages encapsulated in private static methods for readability
 */

public class SeungYoon {
    public static final String VERSION = "Seung Yoon - Version 1.0";

    /*
     * Main function of the entire chatbot,
     * creates new instances of
     * 1. UI for printing strings and receiving inputs from user
     * 2. Storage for loading and saving previous tasks
     * 3. TaskManager to handle the operations regarding the tasks
     * Input received from the UI(user) is parsed using the Parser class and made into a Command instance for execution
     * Chatbot will wait for exit command (EXIT_APP) before saving and exiting program
     */
    public static void main(String[] args) {
        UI ui = new UI();
        ui.printIntroMessage();

        // save and read
        Storage storage = new Storage();
        TaskManager sy = new TaskManager(storage.returnScanner());

        String nextLine = EMPTY_STRING;

        while (!nextLine.equals(EXIT_APP)) {
            nextLine = ui.getUserCommand();
            Command command = Parser.parseCommand(nextLine);
            command.execute(ui, sy);
        }
        Storage.saveState(sy.getTaskList());
        ui.printOutroMessage();
    }

}
