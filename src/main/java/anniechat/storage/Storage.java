package anniechat.storage;

import anniechat.task.Deadline;
import anniechat.task.Event;
import anniechat.task.Task;
import anniechat.task.ToDo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** Manages saving tasks to and loading tasks from the data file. */
public class Storage {
    private final Path filePath;

    /** Creates a storage manager for the specified file path. */
    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    /**
     * Saves every task as one line in the data file.
     *
     * @param tasks tasks to save.
     * @throws IOException if the data file cannot be written.
     */
    public void save(List<Task> tasks) throws IOException {
        Path parentDirectory = filePath.getParent();

        if (parentDirectory != null) {
            Files.createDirectories(parentDirectory);
        }

        List<String> lines = new ArrayList<>();

        for (Task task : tasks) {
            lines.add(task.toSaveFormat());
        }

        Files.write(filePath, lines);
    }

    /**
     * Loads tasks from the data file.
     *
     * @return tasks reconstructed from the saved lines.
     * @throws IOException if the data file cannot be read.
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();

        if (!Files.exists(filePath)) {
            return tasks;
        }

        for (String line : Files.readAllLines(filePath)) {
            if (!line.isBlank()) {
                tasks.add(createTask(line));
            }
        }

        return tasks;
    }

    /**
     * Reconstructs one task from its saved representation.
     *
     * @param line saved task representation.
     * @return reconstructed task.
     */
    private Task createTask(String line) {
        String[] parts = line.split("\\s*\\|\\s*", -1);

        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid task data: " + line);
        }

        Task task = switch (parts[0]) {
        case "T" -> new ToDo("todo " + parts[2]);
        case "D" -> new Deadline("deadline " + parts[2] + " /by " + parts[3]);
        case "E" -> new Event("event " + parts[2] + " /from " + parts[3]
                + "/to " + parts[4]);
        default -> throw new IllegalArgumentException("Unknown task type: " + parts[0]);
        };

        if ("1".equals(parts[1])) {
            task.markDone();
        } else if (!"0".equals(parts[1])) {
            throw new IllegalArgumentException("Invalid task status: " + parts[1]);
        }

        return task;
    }
}
