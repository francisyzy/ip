package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;

/**
 * Command: creates a deadline Task
 */
public class DeadlineCommand extends Command {
    private String title;
    private String deadline;

    /**
     * Creates a deadline command given a title and deadline String
     *
     * @param title    of the Task that that is being created
     * @param deadline String input from the user
     */
    public DeadlineCommand(String title, String deadline) {
        this.title = title;
        this.deadline = deadline;
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param ui      object to reply to user after the command has executed
     * @param storage object required when command writes to file
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = new Deadline(this.title,
                this.deadline);
        tasks.add(newTask);
        ui.showAdd(newTask);
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
