import task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;

import static data.Constants.*;

public class UI {
    private final Scanner in;
    private final PrintStream out;

    public UI() {
        this(System.in, System.out);
    }

    public UI(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserCommand() {
        out.print("Enter command: ");
        return in.nextLine();
    }

    public void showToUser(String... message) {
        for (String m : message) {
            out.println(TAB + m);
        }
    }

    public void printIntroMessage() {
        System.out.println(ENTER + SeungYoon.VERSION);
        showToUser(LINE, "Hello! I'm Seung Yoon" + TAB + "What can I do for you?", NEW_LINE);
    }

    public void printOutroMessage() {
        showToUser(LINE, "Bye. Hope to see you again soon!", NEW_LINE);
    }

    public void printList(ArrayList<?> taskList) {
        showToUser(LINE);
        for (int index = 0; index < taskList.size(); index += 1) {
            showToUser((index + 1) + DOT + WHITESPACE + taskList.get(index).toString());
        }
        showToUser(String.format("You have %d tasks", taskList.size()), NEW_LINE);
    }

    public void printAddTaskMessage(Task task) {
        showToUser(
            LINE,
            "added: " + task.toString(),
            NEW_LINE
        );
    }

    public void printDeleteTaskMessage(ArrayList<?> taskList, int index) {
        showToUser(
            LINE,
            "LESS WORK TO DO WOOHOOO: " + taskList.get(index).toString(),
            NEW_LINE
        );
    }

    public void printMarkedMessage(ArrayList<?> taskList, int taskIndex) {
        showToUser(
            LINE,
            "Good work brudda, the deed has been done, make sure to hydrate",
            TAB + taskList.get(taskIndex).toString(),
            NEW_LINE
        );
    }

    public void printUnmarkedMessage(ArrayList<?> taskList, int taskIndex) {
        showToUser(
            LINE,
            "Get back to work!",
            TAB + taskList.get(taskIndex).toString(),
            NEW_LINE
        );
    }
}
