import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static data.Constants.*;
import task.Task;

public class Storage {
    private Scanner sc;

    public Scanner returnScanner() {
        return sc;
    }

    public Storage() {
        File f = new File(SAVE_FILE);

        if(f.exists())
        {
            try {
                //Scanner sc = new Scanner(f);
                this.sc = new Scanner(f);
                //sy = new TaskManager(sc);
            } catch (FileNotFoundException | NumberFormatException e) {
                printErrorMessage(e.getMessage());
            }
        } else {
            this.sc = new Scanner(EMPTY_STRING);
        }
    }

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
                //fw.write(saveFormat(TODO, t.getCompletionStatus() ? COMPLETED_FLAG : INCOMPLETE_FLAG, t.))
            }
            fw.close();
        } catch (IOException e) {
            printErrorMessage(e.getMessage());
        }
    }
}
