package anniechat.gui;

import anniechat.logic.CommandHandler;
import anniechat.logic.CommandResult;
import anniechat.storage.Storage;
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

    private final VBox conversation = new VBox(6);
    private final ScrollPane chatScrollPane = new ScrollPane(conversation);
    private final TextField commandInput = new TextField();
    private final Button sendButton = new Button("Send");
    private CommandHandler commandHandler;

    /**
     * Creates the chatbot window and connects its controls to the chatbot logic.
     *
     * @param stage the primary stage provided by JavaFX.
     */
    @Override
    public void start(Stage stage) {
        commandHandler = new CommandHandler(new Storage(DATA_FILE_PATH));
        configureConversation();
        configureCommandInput();

        BorderPane root = createLayout();
        stage.setTitle("Anniechat");
        stage.setScene(new Scene(root, 640, 480));

        showStartupMessages();
        stage.show();
    }

    /** Configures the scrollable area containing chat bubbles. */
    private void configureConversation() {
        conversation.setPadding(new Insets(10));
        conversation.setFillWidth(true);
        chatScrollPane.setFitToWidth(true);
        chatScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chatScrollPane.setStyle("-fx-background: white; -fx-border-color: transparent;");
    }

    /** Configures the text field and button used to send commands. */
    private void configureCommandInput() {
        commandInput.setPromptText("Type a command, e.g. todo read book");
        commandInput.setOnAction(event -> sendCommand());
        sendButton.setOnAction(event -> sendCommand());
    }

    /** Creates the main layout of the chatbot window. */
    private BorderPane createLayout() {
        HBox commandBar = new HBox(10, commandInput, sendButton);
        commandBar.setPadding(new Insets(10));
        HBox.setHgrow(commandInput, Priority.ALWAYS);

        Label title = new Label("Anniechat");
        BorderPane root = new BorderPane();
        root.setTop(title);
        root.setCenter(chatScrollPane);
        root.setBottom(commandBar);
        BorderPane.setMargin(title, new Insets(10, 10, 0, 10));
        return root;
    }

    /** Displays loading errors and the initial welcome message. */
    private void showStartupMessages() {
        if (!commandHandler.getStartupMessage().isEmpty()) {
            appendBotMessage(commandHandler.getStartupMessage());
        }
        appendBotMessage("Hello! I am Anniechat. What can I do for you?");
    }

    /** Sends the command currently typed in the input field. */
    private void sendCommand() {
        String input = commandInput.getText().trim();
        if (input.isEmpty()) {
            return;
        }

        appendUserMessage(input);
        commandInput.clear();

        CommandResult result = commandHandler.handle(input);
        appendBotMessage(result.getMessage());
        if (result.isExitRequested()) {
            commandInput.setDisable(true);
            sendButton.setDisable(true);
        }
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
