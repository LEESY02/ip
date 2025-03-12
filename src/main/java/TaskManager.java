import static data.Constants.*;

import exceptions.*;
import task.Task;
import task.tasktypes.Deadline;
import task.tasktypes.Event;
import task.tasktypes.ToDo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class TaskManager {
    private ArrayList<Task> taskList;


    /**
      * Basic constructor when there is no save state
      */
    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    /**
      * Constructor for loading up a state
      * @param sc Scanner instantiated from the save file
      */
    public TaskManager(Scanner sc) {
        this.taskList = Storage.populateArrayList(sc);
    }

    /**
      * Returns a ArrayList<Task> to be printed out
      * @return Returns the entire taskList for printing
      */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
      * Returns a ArrayList<Task> to be printed out
      * @param keyword A String to be used to filter tasks
      * @return Returns a taskList for printing based on detected keyword
      */
    public ArrayList<Task> getTaskList(String keyword) {
        if (keyword.equals(EMPTY_STRING)) {
            throw new InvalidInputException("Empty keyword");
        }
        ArrayList<Task> taskListWithKeyWord = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.toString().contains(keyword)) {
                taskListWithKeyWord.add(task);
            }
        }
        return taskListWithKeyWord;
    }

    /**
      * Returns a ArrayList<Task> to be printed out
      * @param dueDateString A String to be used to obtain a LocalDate object
      * @return Returns a taskList for printing based on due date of the tasks
      */
    public ArrayList<Task> getTaskListDueDate(String dueDateString) {
        LocalDate dueDate = returnValidDate(dueDateString);
        ArrayList<Task> taskListDueDate = new ArrayList<>();
        for (Task task : this.taskList) {
            if ((task instanceof Deadline || task instanceof Event) && task.dueDate().isBefore(dueDate)) {
                taskListDueDate.add(task);
            }
        }
        return taskListDueDate;
    }

    /**
      * Returns a ArrayList<Task> to be printed out
      * @return Returns a taskList for printing based on whether the task is overdue
      */
    public ArrayList<Task> getTaskListOverdue() {
        LocalDate dueDate = LocalDate.now();
        ArrayList<Task> taskListOverdue = new ArrayList<>();
        for (Task task : this.taskList) {
            if ((task instanceof Deadline || task instanceof Event) &&
                task.dueDate().isBefore(dueDate) &&
                !task.getCompletionStatus()) {

                taskListOverdue.add(task);
            }
        }
        return taskListOverdue;
    }

    /**
      * Add algorithm
      * newTask is either a ToDo, Deadline, Event
      * NoSuchElementException catch checks for valid string input from the user (For deadline and event)
      * @param taskType Defines what type of Task is to be defined
      * @param input Defines the descriptions of each Task object
      * @param ui UI for printing add task message
      * @throws InvalidFlagException if tags such as /by is not present
      */
    public void addTask(String taskType, String input, UI ui) {
        try {
            Task newTask = returnCorrectTask(taskType, input);
            taskList.add(newTask);
            ui.printAddTaskMessage(newTask);
        } catch (NoSuchElementException e) {
            throw new InvalidFlagException(TAG_ERROR);
        }
    }

    /**
      * Returns a correct subclass of Task based on the parameters
      * @param taskType The subclass type of Task
      * @param input The descriptions regarding said task
      * @return An instance of a Task subclass based on taskType
      */
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
            sc.close();
            return returnTask(input);
        }
    }

    /**
      * Returns a ToDo
      * @param sc A Scanner object containing the task description
      * @return A new ToDo
      */
    private static ToDo returnToDo(Scanner sc) {
        String task = sc.nextLine();
        sc.close();
        return new ToDo(task.strip());
    }

    /**
      * Returns a LocalDate object based on the input string
      * Exception catch checks for valid string input from the user (input must be 'YYYY-MM-DD')
      * @param input Defines the value of the to be LocalDate object
      * @throws InvalidInputException if LocalDate object could not be instantiated
      */
    private static LocalDate returnValidDate(String input) {
        try {
            String time[] = input.split("-");
            int year = Integer.parseInt(time[0].strip());
            int month = Integer.parseInt(time[1].strip());
            int day = Integer.parseInt(time[2].strip());
            return LocalDate.of(year, month, day);
        } catch (Exception e) {
            throw new InvalidDateException(INVALID_DATE);
        }
    }

    /**
      * Returns a Deadline
      * @param sc A Scanner object containing the task description
      * @return A new Deadline
      */
    private static Deadline returnDeadline(Scanner sc) {
        String task = EMPTY_STRING;
        String currentWord = EMPTY_STRING;
        while (!currentWord.equals(DEADLINE_BY_FLAG)) {
            task += WHITESPACE + currentWord;
            currentWord = sc.next();
        }
        String deadline = sc.nextLine();

        LocalDate ld = returnValidDate(deadline);

        sc.close();
        return new Deadline(task.strip(), ld /*deadline.strip()*/);
    }

    /**
      * Returns an Event
      * @param sc A Scanner object containing the task description
      * @return A new Event
      */
    private static Event returnEvent(Scanner sc) {
        String task = EMPTY_STRING;
        String currentWord = EMPTY_STRING;
        while (!currentWord.equals(EVENT_FROM_FLAG)) {
            task += WHITESPACE + currentWord;
            currentWord = sc.next();
        }
        String startDate = EMPTY_STRING;
        currentWord = EMPTY_STRING;
        while (!currentWord.equals(EVENT_TO_FLAG)) {
            startDate += WHITESPACE + currentWord;
            currentWord = sc.next();
        }
        String endDate = sc.nextLine();

        LocalDate ldStart = returnValidDate(startDate.strip());
        LocalDate ldEnd = returnValidDate(endDate.strip());

        sc.close();
        return new Event(task.strip(), ldStart, ldEnd);

    }

    /**
      * Returns a Task
      * @param task A String object containing the task description
      * @return A new Task
      */
    private static Task returnTask(String task) {
        return new Task(task);
    }

    /**
      * Delete algorithm
      * NumberFormatException and IndexOutOfBoundsException catches for valid index
      * @param input A String that contains the index of the Task to be deleted
      * @param ui A UI instance to print the delete Task message
      * @throws InvalidIndexException if user inputs an invalid index for deleting
      */
    public void deleteTask(String input, UI ui) {
        try {
            int index = Integer.parseInt(input) - 1;
            ui.printDeleteTaskMessage(this.getTaskList(), index);
            taskList.remove(index);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException(INVALID_INDEX);
        }
    }

    /**
      * Mark a task as done
      * NullPointerException and IndexOutOfBoundsException catches for valid index (or empty list)
      * @param indexString A String that contains the index of the task to be marked as done
      * @param ui A UI instance to print the task completed message
      * @throws InvalidIndexException if user inputs an invalid index for deleting
      */
    public void mark(String indexString, UI ui) {
        if (!indexString.isBlank()) {
            try {
                int index = Integer.parseInt(indexString) - 1;
                taskList.get(index).setComplete();
                ui.printMarkedMessage(this.getTaskList(), index);
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                throw new InvalidIndexException(INVALID_INDEX);
            }
        }
    }

    /**
      * Mark a task as not done
      * NullPointerException and IndexOutOfBoundsException catches for valid index (or empty list)
      * @param indexString A String that contains the index of the task to be marked as not done
      * @param ui A UI instance to print the task incomplete message
      * @throws InvalidIndexException if user inputs an invalid index for deleting
      */
    public void unmark(String indexString, UI ui) {
        if (!indexString.isBlank()) {
            try {
                int index = Integer.parseInt(indexString) - 1;
                taskList.get(index).setIncomplete();
                ui.printUnmarkedMessage(this.getTaskList(), index);
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                throw new InvalidIndexException(INVALID_INDEX);
            }
        }
    }

}
