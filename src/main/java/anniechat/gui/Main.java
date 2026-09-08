package anniechat.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import anniechat.parser.Parser;
import anniechat.storage.Storage;
import anniechat.task.Task;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** Provides a graphical interface for the Anniechat task manager. */
public class Main extends Application {

    private static final String DATA_FILE_PATH = "data/anniechat.txt";

    private final Storage storage = new Storage(DATA_FILE_PATH);
    private final List<Task> tasks = new ArrayList<>();
    private final VBox conversation = new VBox(6);
    private final ScrollPane chatScrollPane = new ScrollPane(chatScrollPane);
    private final TextField commandInput = new TextField();
    private final Button sendButton = new Button("Send");

    /**
     * Creates the chatbot window and connects its controls to the chatbot logic.
     *
     * @param stage the primary stage provided by JavaFX.
     */
    @Override
    public void start(Stage stage) {
        conversation.setPadding(new Insets(10));
        conversation.setFillWidth(true);
        chatScrollPane.setFitToWidth(true);
        chatScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chatScrollPane.setStyle("-fx-background: white; -fx-border-color: transparent;");

        commandInput.setPromptText("Type a command, e.g. todo read book");
        commandInput.setOnAction(event -> sendCommand());
        sendButton.setOnAction(event -> sendCommand());

        HBox commandBar = new HBox(10, commandInput, sendButton);
        commandBar.setPadding(new Insets(10));
        HBox.setHgrow(commandInput, Priority.ALWAYS);

        Label title = new Label("Anniechat");
        BorderPane root = new BorderPane();
        root.setTop(title);
        root.setCenter(conversation);
        root.setBottom(commandBar);
        BorderPane.setMargin(title, new Insets(10, 10, 0, 10));

        Scene scene = new Scene(root, 640, 480);
        stage.setTitle("Anniechat");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> saveTasks());

        loadTasks();
        appendBotMessage("Hello! I am Anniechat. What can I do for you?");
        stage.show();
    }

    /** Sends the command currently typed in the input field. */
    private void sendCommand() {
        String input = commandInput.getText().trim();
        if (input.isEmpty()) {
            return;
        }

        appendUserMessage(input);
        commandInput.clear();
        executeCommand(input);
    }

    /** Executes one command and displays the corresponding response. */
    private void executeCommand(String input) {
        Parser parser = new Parser(input);

        try {
            switch (parser.getCommandWord()) {
            case "bye":
                appendBotMessage("Bye. See you next time!");
                commandInput.setDisable(true);
                sendButton.setDisable(true);
                break;
            case "list":
                showTaskList(tasks);
                break;
            case "mark":
                markTask(parser.getTaskNumber(), true);
                break;
            case "unmark":
                markTask(parser.getTaskNumber(), false);
                break;
            case "delete":
                deleteTask(parser.getTaskNumber());
                break;
            case "todo":
            case "deadline":
            case "event":
                addTask(parser);
                break;
            case "find":
                showTaskList(parser.findMatchingTasks(tasks));
                break;
            default:
                appendBotMessage("I do not recognise that command. Try list, todo, deadline, event, "
                        + "mark, unmark, delete, find, or bye.");
                break;
            }
        } catch (IllegalArgumentException | IndexOutOfBoundsException exception) {
            appendBotMessage("Sorry, I could not understand that command.");
        }
    }

    /** Loads saved tasks when the GUI starts. */
    private void loadTasks() {
        try {
            tasks.addAll(storage.load());
        } catch (IOException | IllegalArgumentException exception) {
            appendBotMessage("I could not load your saved tasks, so I started with an empty list.");
        }
    }

    /** Adds a newly parsed task and saves the updated task list. */
    private void addTask(Parser parser) {
        Task task = parser.createTask();
        tasks.add(task);
        saveTasks();
        appendBotMessage("Got it. I have added this task:\n" + formatTask(task));
    }

    /** Marks or unmarks a task at the given zero-based index. */
    private void markTask(int taskIndex, boolean done) {
        Task task = getTask(taskIndex);
        if (done) {
            task.markDone();
            appendBotMessage("Nice! I marked this task as done:\n" + formatTask(task));
        } else {
            task.markUndone();
            appendBotMessage("Okay, I marked this task as not done:\n" + formatTask(task));
        }
        saveTasks();
    }

    /** Deletes a task at the given zero-based index and saves the updated list. */
    private void deleteTask(int taskIndex) {
        Task task = getTask(taskIndex);
        tasks.remove(taskIndex);
        Task.removeTask();
        saveTasks();
        appendBotMessage("I have deleted this task:\n" + formatTask(task));
    }

    /** Returns a task or throws an error when the requested index is invalid. */
    private Task getTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        return tasks.get(taskIndex);
    }

    /** Displays all tasks in the supplied list. */
    private void showTaskList(List<Task> tasksToShow) {
        if (tasksToShow.isEmpty()) {
            appendBotMessage("There are no matching tasks.");
            return;
        }

        StringBuilder message = new StringBuilder("Here are the tasks:\n");
        for (int i = 0; i < tasksToShow.size(); i++) {
            message.append(formatNumberedTask(tasksToShow.get(i), i + 1)).append("\n");
        }
        appendBotMessage(message.toString().trim());
    }

    /** Saves the current task list to the configured data file. */
    private void saveTasks() {
        try {
            storage.save(tasks);
        } catch (IOException exception) {
            appendBotMessage("I could not save your latest changes.");
        }
    }

    /** Formats a task for a chatbot response. */
    private String formatTask(Task task) {
        return task.statusIcon() + " " + task.getTaskIcon() + " " + task.getTaskDesc();
    }

    /** Formats a task with its number for the task-list response. */
    private String formatNumberedTask(Task task, int number) {
        return number + ". " + formatTask(task);
    }

    /** Adds a user message to the conversation display. */
    private void appendUserMessage(String message) {
        appendMessage("You", message, Pos.CENTER_RIGHT, "#e7e7e7", "#202124");
    }

    /** Adds an Anniechat response to the conversation display. */
    private void appendBotMessage(String message) {
        appendMessage("Anniechat", message, Pos.CENTER_LEFT, "#d9fdd3", "#1b5e20");
    }

    /** Adds a coloured, aligned chat bubble to the conversation display. */
    private void appendMessage(String sender, String message, Pos alignment,
                               String backgroundColor, String textColor) {
        Label senderLabel = new Label(sender);
        senderLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #666666;");

        Label bubble = new Label(message);
        bubble.setWrapText(true);
        bubble.setMaxWidth(440);
        bubble.setStyle("-fx-background-color: " + backgroundColor + ";"
                + "-fx-text-fill: " + textColor + ";"
                + "-fx-padding: 8 12;"
                + "-fx-background-radius: 14;");

        VBox messageBlock = new VBox(2, senderLabel, bubble);
        messageBlock.setMaxWidth(460);
        HBox messageRow = new HBox(messageBlock);
        messageRow.setAlignment(alignment);
        messageRow.setMaxWidth(Double.MAX_VALUE);
        conversation.getChildren().add(messageRow);
        chatScrollPane.setVvalue(1.0);
    }
}
