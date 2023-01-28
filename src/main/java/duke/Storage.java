package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.enums.Commands;
import duke.enums.Views;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Class to manage storage of persistent files
 */
public class Storage {
    // private final String filePath;

    // Storage(String filePath) {
    // this.filePath = filePath;
    // }

    /**
     * Given a list of tasks, it will save it into a txt file
     *
     * @param tasks
     */
    public void save(TaskList tasks) {
        File dukeData = new File("duke_data.txt");
        try (PrintWriter writer = new PrintWriter(new FileWriter(dukeData));) {
            for (Task item : tasks.getList()) {
                writer.println(item.toExport()
                        .replace("[T]", "todo")
                        .replace("[D]", "deadline")
                        .replace("(by:", "/by")
                        .replace("[E]", "event")
                        .replace("(from:", "/from")
                        .replace("to:", "/to")
                        .replace(")", ""));
            }
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    /**
     * Loads the items from storage
     *
     * @return Array of tasks
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasksList = new ArrayList<Task>(100);
        try {
            Scanner scanner = new Scanner(new File("duke_data.txt"));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                boolean isDone = line.indexOf("[X]") == -1 ? false : true;
                line = line.replace("[ ]", "");
                line = line.replace("[X]", "");
                if (line.startsWith(Commands.TODO.cmd())) {
                    String title = line.substring(Commands.TODO.cmd().length());
                    Task newTask = new Todo(title, isDone);
                    tasksList.add(newTask);
                } else if (line.startsWith(Commands.DEADLINE.cmd())) {
                    if (line.indexOf(Commands.BY.cmd()) == -1) {
                        throw new DukeException(Views.MISSING_ARGS_ERR_STRING.eng());
                    }
                    String title = line.substring(Commands.DEADLINE.cmd().length(),
                            line.indexOf(Commands.BY.cmd()));
                    Task newTask = new Deadline(title,
                            line.substring(line.indexOf(Commands.BY.cmd())), isDone);
                    tasksList.add(newTask);
                } else if (line.startsWith(Commands.EVENT.cmd())) {
                    if (line.indexOf(Commands.FROM.cmd()) == -1 || line.indexOf(Commands.TO.cmd()) == -1) {
                        throw new DukeException(Views.MISSING_ARGS_ERR_STRING.eng());
                    }
                    String title = line.substring(Commands.EVENT.cmd().length(),
                            line.indexOf(Commands.FROM.cmd()));
                    Task newTask = new Event(title,
                            line.substring(line.indexOf(Commands.FROM.cmd()),
                                    line.indexOf(Commands.TO.cmd())),
                            line.substring(line.indexOf(Commands.TO.cmd())), isDone);
                    tasksList.add(newTask);
                }
            }
            scanner.close();
        } catch (Exception e) {
            // System.out.println(e);
        }
        return tasksList;
    }

}
