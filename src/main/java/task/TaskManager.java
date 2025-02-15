package task;

import static data.Constants.*;
import task.tasktypes.Deadline;
import task.tasktypes.Event;
import task.tasktypes.ToDo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class TaskManager<T extends Task> {
    private int numberOfTasks;
    private ArrayList<T> taskList;

    public TaskManager() {
        this.numberOfTasks = 0;
        this.taskList = new ArrayList<>();
    }

    public TaskManager(Scanner sc) {
        this.taskList = populateArrayList(sc);
        this.numberOfTasks = this.taskList.size();
    }

    // Opening with save state
    private /*static*/ ArrayList<T> populateArrayList(Scanner sc) {
        ArrayList<T> newTaskList = new ArrayList<>();
        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            String[] tokens = line.split(STRING_SEPARATOR, 5);
            newTaskList.add(returnCorrectTask(tokens[TASK_TYPE_INDEX], tokens[DESCRIPTION_INDEX]));
            if (Integer.parseInt(tokens[COMPLETION_INDEX]) == 1) {
                newTaskList.get(0).setComplete();
            }
        }
        return newTaskList;
    }

    // saving state
    public void saveState() {
        try {
            FileWriter fw = new FileWriter(SAVE_FILE);
            for (T t : taskList) {
                switch (t.getTaskType()) {
                case TASK_TYPE_TODO:
                    fw.write(saveFormat(TODO, t.getCompletionStatus() ? COMPLETED_FLAG : INCOMPLETE_FLAG,
                        t.getTask()));
                    break;
                case TASK_TYPE_DEADLINE:
                    fw.write(saveFormat(DEADLINE, t.getCompletionStatus() ? COMPLETED_FLAG : INCOMPLETE_FLAG,
                        deadlineDescriptor(t.getTask(), t.getDeadline())));
                    break;
                case TASK_TYPE_EVENT:
                    fw.write(saveFormat(EVENT, t.getCompletionStatus() ? COMPLETED_FLAG : INCOMPLETE_FLAG,
                        eventDescriptor(t.getTask(), t.getStartDate(), t.getEndDate())));
                    break;
                }
                //fw.write(saveFormat(TODO, t.getCompletionStatus() ? COMPLETED_FLAG : INCOMPLETE_FLAG, t.))
            }
            fw.close();
        } catch (IOException e) {
            printErrorMessage(e.getMessage());
        }
    }


    // Execute commands based on the 1st word of the input
    public void executeCommand(String input) {
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
    private void printTaskList() {
        System.out.print(NEW_LINE_TAB);
        for (int index = 0; index < taskList.size(); index += 1) {
            System.out.println(TAB + (index + 1) + DOT + WHITESPACE + taskList.get(index).toString());
        }
        System.out.println(String.format(TAB + "You have %d tasks", numberOfTasks));
        System.out.println(NEW_LINE_TAB);
    }
    // end of list

    // Add algorithm
    private void addTask(String taskType, String input) {
        try {
            T newTask = returnCorrectTask(taskType, input);
            taskList.add(newTask);
            printAddTaskMessage(newTask);
            numberOfTasks += 1;
        } catch (NoSuchElementException e) {
            //} catch (InvalidFlagException e) {
            printErrorMessage(TAG_ERROR);
        }
    }

    private /*static*/ T returnCorrectTask(String taskType, String input) {
        Scanner sc = new Scanner(input);

        switch (taskType) {
        case TODO:
            return (T) returnToDo(sc);
        case DEADLINE:
            return (T) returnDeadline(sc);
        case EVENT:
            return (T) returnEvent(sc);
        default:
            return (T) returnTask(sc, input);
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
    private void deleteTask(String input) {
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

    private void printDeleteTaskMessage(int index) {
        System.out.println(
            NEW_LINE_TAB + TAB + "LESS WORK TO DO WOOHOOO: " + taskList.get(index).toString() + ENTER + NEW_LINE_TAB
        );
    }
    // end of delete

    // "mark" / "unmark" command: Mark as done or undone
    private void mark(String indexString) {
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

    private void unmark(String indexString) {
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

    private void printMarkedMessage(int taskIndex) {
        System.out.println(
            NEW_LINE_TAB +
                TAB + "Good work brudda, the deed has been done, make sure to hydrate" + ENTER +
                TAB + TAB + taskList.get(taskIndex).toString() + ENTER +
                NEW_LINE_TAB
        );
    }

    private void printUnmarkedMessage(int taskIndex) {
        System.out.println(
            NEW_LINE_TAB +
                TAB + "Get back to work!" + ENTER +
                TAB + TAB + taskList.get(taskIndex).toString() + ENTER +
                NEW_LINE_TAB
        );
    }
    // End of mark / unmark
}
