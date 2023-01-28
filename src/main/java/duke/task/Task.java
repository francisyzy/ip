package duke.task;

import duke.enums.Views;
import duke.DukeException;

/**
 * Abstract class for the Task. Every todo / deadline / event item is here
 */
public abstract class Task {
    private String title;
    private boolean isDone = false;

    /**
     * Creates a task, throws error if its empty string
     *
     * @param title of the Task that that is being created
     * @throws DukeException
     */
    Task(String title) throws DukeException {
        if (title.trim().length() == 0) {
            throw new DukeException(Views.EMPTY_ERR_STRING.eng());
        }
        this.title = title.trim();
    }

    /**
     * Creates a task, with isDone field. Mostly used by Storage
     *
     * @param title  of the Task that that is being created
     * @param isDone status of the Task
     * @throws DukeException
     */
    Task(String title, boolean isDone) throws DukeException {
        if (title.trim().length() == 0) {
            throw new DukeException(Views.EMPTY_ERR_STRING.eng());
        }
        this.title = title.trim();
        this.isDone = isDone;
    }

    /**
     * Mark the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Method for formatting the Task to store in a txt file
     *
     * @return String of the Task
     */
    public String toExport() {
        return this.toString();
    }

    /**
     * Get a String representation to display to user of a Task
     *
     * @return String representation of the Task
     */
    @Override
    public String toString() {
        String returnString = "";
        if (isDone) {
            returnString = "[X] ";
        } else {
            returnString = "[ ] ";
        }
        return returnString + title;
    }
}
