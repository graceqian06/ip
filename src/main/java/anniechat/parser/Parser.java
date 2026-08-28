package anniechat.parser;

import anniechat.task.Deadline;
import anniechat.task.Event;
import anniechat.task.Task;
import anniechat.task.ToDo;

/** Interprets commands entered by the user. */
public class Parser {
    private final String input;

    /** Creates a parser for the given user input. */
    public Parser(String input) {
        this.input = input;
    }

    /**
     * Extracts the command word from the user input.
     *
     * @return the first word of the command, or an empty string for blank input.
     */
    public String getCommandWord() {
        String trimmedInput = input.trim();

        if (trimmedInput.isEmpty()) {
            return "";
        }

        return trimmedInput.split("\\s+", 2)[0];
    }

    /**
     * Extracts a task number and converts it to a zero-based list index.
     *
     * @return the zero-based task index.
     */
    public int getTaskNumber() {
        String[] parts = input.trim().split("\\s+");
        int userNumber = Integer.parseInt(parts[1]);
        return userNumber - 1;
    }

    /**
     * Extracts the part of the input after the command word.
     *
     * @return the task description, or an empty string if none was provided.
     */
    public String getTaskDescription() {
        int firstSpace = input.indexOf(' ');

        if (firstSpace == -1) {
            return "";
        }

        return input.substring(firstSpace + 1).trim();
    }

    /**
     * Creates the task represented by the command.
     *
     * @return the new task.
     */
    public Task createTask() {
        return switch (getCommandWord()) {
        case "todo" -> new ToDo(input);
        case "deadline" -> new Deadline(input);
        case "event" -> new Event(input);
        default -> throw new IllegalArgumentException("Unknown task command");
        };
    }
}
