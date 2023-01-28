package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command: Clears the task list
 */
public class ClearCommand extends Command {
    /**
     * Executes the command
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.clear();
        ui.showClear();
        storage.save(tasks);
    }

    /**
     * Checks if this command will exit the program
     *
     * @return boolean True if the command will exit the program
     */
    public boolean isExit() {
        return false;
    }
}
