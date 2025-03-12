import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static data.Constants.*;
import task.Task;

/**
  * Reads save state and stores them in a Scanner object
  */
public class Storage {
    private Scanner sc;

    /**
      * A public constructor that reads the txt file defined under 'SAVE_FILE' and creates a scanner object for reading
      */
    public Storage() {
        File f = new File(SAVE_FILE);

        if(f.exists())
        {
            try {
                this.sc = new Scanner(f);
            } catch (FileNotFoundException | NumberFormatException e) {
                printErrorMessage(e.getMessage());
            }
        } else {
            this.sc = new Scanner(EMPTY_STRING);
        }
    }

    /**
      * A static method that returns an ArrayList of Task objects based on the save state defined in the Scanner object
      * @param sc Scanner object with the save state
      * @return an ArrayList<Task> object of tasks
      */
    public static ArrayList<Task> populateArrayList(Scanner sc) {
        ArrayList<Task> newTaskList = new ArrayList<>();
        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            String[] tokens = line.split(STRING_SEPARATOR, 5);
            newTaskList.add(TaskManager.returnCorrectTask(tokens[TASK_TYPE_INDEX], tokens[DESCRIPTION_INDEX]));
            if (Integer.parseInt(tokens[COMPLETION_INDEX]) == 1) {
                newTaskList.get(0).setComplete();
            }
        }
        return newTaskList;
    }

    /**
      * A static method that saves the state of a TaskManager based on the ArrayList given at file location 'SAVE_FILE'
      * @param taskList ArrayList<Task> object of tasks meant for saving
      * IOException catch for FileWriter errors
      */
    public static void saveState(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(SAVE_FILE);
            for (Task t : taskList) {
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
            }
            fw.close();
        } catch (IOException e) {
            printErrorMessage(e.getMessage());
        }
    }

    /**
      * @return Scanner object in this class, used for instantiating a new TaskManager object
      */
    public Scanner returnScanner() {
        return sc;
    }

}
