package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.enums.Views;
import duke.DukeException;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String title, String by) throws DukeException {
        super(title);
        try {
            this.by = LocalDateTime.parse(by.replace("/by", "").trim());
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException(Views.DATE_PARSE_ERR_STRING.eng());
        }
    }

    public Deadline(String title, String by, boolean isDone) throws DukeException {
        super(title, isDone);
        try {
            this.by = LocalDateTime.parse(by.replace("/by", "").trim());
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException(Views.DATE_PARSE_ERR_STRING.eng());
        }
    }

    @Override
    public String toExport() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmm")) + ")";
    }
}
