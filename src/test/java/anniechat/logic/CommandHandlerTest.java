package anniechat.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import anniechat.storage.Storage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/** Tests command execution independently of the CLI and JavaFX interfaces. */
public class CommandHandlerTest {
    /** Temporary directory provided by JUnit for each test. */
    @TempDir
    Path temporaryDirectory;

    @Test
    public void handle_addCommand_addsAndSavesTask() throws IOException {
        Path filePath = temporaryDirectory.resolve("tasks.txt");
        CommandHandler handler = new CommandHandler(new Storage(filePath.toString()));

        CommandResult result = handler.handle("todo read book");

        assertTrue(result.getMessage().contains("I've added this task"));
        assertEquals(List.of("T | 0 | read book"), Files.readAllLines(filePath));
        assertFalse(result.isExitRequested());
    }

    @Test
    public void handle_unknownCommand_returnsSharedResponse() throws IOException {
        Path filePath = temporaryDirectory.resolve("tasks.txt");
        CommandHandler handler = new CommandHandler(new Storage(filePath.toString()));

        CommandResult result = handler.handle("nonsense");

        assertEquals("Sowwy idk what that means :(", result.getMessage());
        assertFalse(result.isExitRequested());
    }

    @Test
    public void handle_byeCommand_requestsExit() throws IOException {
        Path filePath = temporaryDirectory.resolve("tasks.txt");
        CommandHandler handler = new CommandHandler(new Storage(filePath.toString()));

        CommandResult result = handler.handle("bye");

        assertEquals("Byeee! Cya again soon!", result.getMessage());
        assertTrue(result.isExitRequested());
    }
}
