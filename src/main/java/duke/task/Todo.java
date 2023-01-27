package duke.task;

import duke.DukeException;

public class Todo extends Task {
    public Todo(String title) throws DukeException {
        super(title);
    }

    public Todo(String title, boolean isDone) throws DukeException {
        super(title, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
