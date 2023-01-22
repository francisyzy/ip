public class EventCommand extends Command {
    private String title;
    private String to;
    private String from;

    EventCommand(String title, String to, String from) {
        this.title = title;
        this.to = to;
        this.from = from;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = new Event(this.title, this.to, this.from);
        tasks.add(newTask);
        ui.showAdd(newTask);
        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }
}