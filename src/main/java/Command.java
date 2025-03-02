import exceptions.InvalidDateException;
import exceptions.InvalidFlagException;
import exceptions.InvalidIndexException;
import exceptions.InvalidInputException;

import static data.Constants.*;

public class Command {
    private final String command;
    private final String description;

    /*
     * Command constructor
     * @param command The main instruction that TaskManager has to execute
     * @param description Contents of description will differ based on the command parameter
     */
    public Command(String command, String description) {
        this.command = command;
        this.description = description;
    }

    /*
     * Executes the command, based on the 'command' string of the object
     * Catches custom errors thrown by TaskManager
     * @param ui The UI instance from SeungYoon class, used for printing related tasks
     * @param tm The TaskManager instance from SeungYoon class, used for tasks related to 'Task' instances
     */
    public void execute(UI ui, TaskManager tm) {
        try {
            switch (command) {
            case LIST:
                ui.printList(tm.getTaskList());
                break;
            case MARK:
                tm.mark(description, ui);
                break;
            case UNMARK:
                tm.unmark(description, ui);
                break;
            case TODO:
                tm.addTask(TODO, description, ui);
                break;
            case DEADLINE:
                tm.addTask(DEADLINE, description, ui);
                break;
            case EVENT:
                tm.addTask(EVENT, description, ui);
                break;
            case DELETE:
                tm.deleteTask(description, ui);
                break;
            case DUE:
                ui.printList(tm.getTaskListDueDate(description));
                break;
            case OVERDUE:
                ui.printList(tm.getTaskListOverdue());
                break;
            case SEARCH:
                ui.printList(tm.getTaskList(description));
                break;
            case EXIT_APP:
                break;
            default:
                printErrorMessage(RE_ENTER_COMMAND);
            }
        } catch (
            InvalidFlagException |
            InvalidIndexException |
            InvalidDateException |
            InvalidInputException error
        ) {
            printErrorMessage(error.toString());
        }
    }

}
