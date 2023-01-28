package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command: Creates a command to exit the program
 */
public class ExitCommand extends Command {
    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param ui      object to reply to user after the command has executed
     * @param storage object required when command writes to file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showEnd();
        storage.save(tasks);
    }

    /**
     * Checks if this command will exit the program
     *
     * @return boolean True if the command will exit the program
     */
    public boolean isExit() {
        return true;
    }
}
