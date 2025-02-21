//import exceptions.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

import static data.Constants.*;
import task.TaskManager;

/*
 * Coding standard is defined by IDE
 * lowerCamelCase for variables and object instances
 * UpperCamelCase for Class names
 * ALL_CAPS_WITH_UNDERSCORE for constants
 * Print messages encapsulated in private static methods for readability
 */

public class SeungYoon {
    // Main
    public static void main(String[] args) {
        printIntroMessage();

        // save and read
        TaskManager sy = new TaskManager();
        File f = new File(SAVE_FILE);

        if (f.exists()) {
            try {
                Scanner sc = new Scanner(f);
                sy = new TaskManager(sc);
            } catch (FileNotFoundException | NumberFormatException e) {
                printErrorMessage(e.getMessage());
            }
        }
        else {
            try {
                boolean fileCreated = f.createNewFile();
            } catch (IOException e) {
                printErrorMessage(e.getMessage());
            }
        }

        Scanner sc = new Scanner(System.in);
        String nextLine = sc.nextLine();

        while (!nextLine.equals(EXIT_APP)) {
            sy.executeCommand(nextLine);
            nextLine = sc.nextLine();
        }
        sy.saveState();
        sc.close();

        printOutroMessage();
    }

    private static void printIntroMessage() {
        System.out.println(NEW_LINE + "Hello! I'm Seung Yoon" + TAB + "What can I do for you?" + ENTER + NEW_LINE);
    }

    private static void printOutroMessage() {
        System.out.print(NEW_LINE_TAB + TAB + "Bye. Hope to see you again soon!" + ENTER + NEW_LINE_TAB);
    }
    // End of Main

}
