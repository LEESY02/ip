import static data.Constants.*;

import task.Task;
import task.tasktypes.Deadline;
import task.tasktypes.Event;
import task.tasktypes.ToDo;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class TaskManager {
    private int numberOfTasks;
    private ArrayList<Task> taskList;

    public TaskManager() {
        this.numberOfTasks = 0;
        this.taskList = new ArrayList<>();
    }

    public TaskManager(Scanner sc) {
        this.taskList = Storage.populateArrayList(sc);
        this.numberOfTasks = this.taskList.size();
    }

    // return list to print
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    // Add algorithm
    public void addTask(String taskType, String input, UI ui) {
        try {
            Task newTask = returnCorrectTask(taskType, input);
            taskList.add(newTask);
            ui.printAddTaskMessage(newTask);
            numberOfTasks += 1;
        } catch (NoSuchElementException e) {
            //} catch (InvalidFlagException e) {
            printErrorMessage(TAG_ERROR);
        }
    }

    public static Task returnCorrectTask(String taskType, String input) {
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
    // end of add

    // Delete algorithm
    public void deleteTask(String input, UI ui) {
        try {
            int index = Integer.parseInt(input) - 1;
            ui.printDeleteTaskMessage(this.getTaskList(), index);
            taskList.remove(index);
            numberOfTasks -= 1;
        } catch (NumberFormatException e) {
            printErrorMessage(INVALID_INDEX);
        } catch (IndexOutOfBoundsException e) {
            printErrorMessage(OUT_OF_BOUND_INDEX);
        }
    }
    // end of delete

    // "mark" / "unmark" command: Mark as done or undone
    public void mark(String indexString, UI ui) {
        if (!indexString.isBlank()) {
            try { // in case user inputs invalid index
                int index = Integer.parseInt(indexString) - 1;
                taskList.get(index).setComplete();
                ui.printMarkedMessage(this.getTaskList(), index);
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                //} catch (InvalidIndexException e) {
                printErrorMessage(e.toString());
            }

        }
    }

    public void unmark(String indexString, UI ui) {
        if (!indexString.isBlank()) {
            try { // in case user inputs invalid index
                int index = Integer.parseInt(indexString) - 1;
                taskList.get(index).setIncomplete();
                ui.printUnmarkedMessage(this.getTaskList(), index);
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                //} catch (InvalidIndexException e) {
                printErrorMessage(e.toString());
            }
        }
    }
    // End of mark / unmark
}
