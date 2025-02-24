import static data.Constants.*;

public class Command {
    private final String command;
    private final String description;

    public Command(String command, String description) {
        this.command = command;
        this.description = description;
    }

    // Execute the command
    public void execute(UI ui, TaskManager tm) {
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
        case EXIT_APP:
            break;
        default:
            printErrorMessage(RE_ENTER_COMMAND);
        }
    }

}
