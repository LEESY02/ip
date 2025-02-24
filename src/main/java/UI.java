import task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;

import static data.Constants.*;

/*
 * A class used for receiving inputs and printing output to the user
 * A Scanner object for reading input
 * A PrintStream object for printing output
 */
public class UI {
    private final Scanner in;
    private final PrintStream out;

    /*
     * Constructor for UI Class
     */
    public UI() {
        this(System.in, System.out);
    }

    /*
     * Constructor for UI Class
     * @param in Defines the input stream from the user
     * @param out Defines the output stream to the user
     */
    public UI(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /*
     * A method used to receive input from user
     */
    public String getUserCommand() {
        out.print("Enter command: ");
        return in.nextLine();
    }

    /*
     * A method used to print output to user
     * @param message A variable number of Strings to print to user
     */
    public void showToUser(String... message) {
        for (String m : message) {
            out.println(TAB + m);
        }
    }

    /*
     * A method to print an introduction message when user opens the app
     */
    public void printIntroMessage() {
        System.out.println(ENTER + SeungYoon.VERSION);
        showToUser(LINE, "Hello! I'm Seung Yoon" + TAB + "What can I do for you?", NEW_LINE);
    }

    /*
     * A method to print a goodbye message when user closes the app
     */
    public void printOutroMessage() {
        showToUser(LINE, "Bye. Hope to see you again soon!", NEW_LINE);
    }

    /*
     * A method to print a list of tasks when given an ArrayList of tasks
     * @param taskList An ArrayList<Task> of different tasks defined by the user previously
     */
    public void printList(ArrayList<?> taskList) {
        showToUser(LINE);
        for (int index = 0; index < taskList.size(); index += 1) {
            showToUser((index + 1) + DOT + WHITESPACE + taskList.get(index).toString());
        }
        showToUser(String.format("You have %d tasks", taskList.size()), NEW_LINE);
    }

    /*
     * A method to print that a task has been added
     * @param task A Task object that was newly added to the TaskManager
     */
    public void printAddTaskMessage(Task task) {
        showToUser(
            LINE,
            "added: " + task.toString(),
            NEW_LINE
        );
    }

    /*
     * A method to print that a task has been deleted
     * @param taskList An ArrayList<Task> of all the tasks
     * @param index The index of the newly deleted task
     */
    public void printDeleteTaskMessage(ArrayList<?> taskList, int index) {
        showToUser(
            LINE,
            "LESS WORK TO DO WOOHOOO: " + taskList.get(index).toString(),
            NEW_LINE
        );
    }

    /*
     * A method to print that a task has been completed
     * @param taskList An ArrayList<Task> of all the tasks
     * @param index The index of the newly completed task
     */
    public void printMarkedMessage(ArrayList<?> taskList, int taskIndex) {
        showToUser(
            LINE,
            "Good work brudda, the deed has been done, make sure to hydrate",
            TAB + taskList.get(taskIndex).toString(),
            NEW_LINE
        );
    }

    /*
     * A method to print that a task has been made incomplete
     * @param taskList An ArrayList<Task> of all the tasks
     * @param index The index of the incomplete task
     */
    public void printUnmarkedMessage(ArrayList<?> taskList, int taskIndex) {
        showToUser(
            LINE,
            "Get back to work!",
            TAB + taskList.get(taskIndex).toString(),
            NEW_LINE
        );
    }

}
