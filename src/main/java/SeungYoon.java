//import exceptions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

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

    // Main
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
    // End of Main

}
