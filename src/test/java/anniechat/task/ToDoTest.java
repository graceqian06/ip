package anniechat.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Tests the behaviour of {@link ToDo}. */
public class ToDoTest {
    @Test
    public void constructor_validDescription_storesTaskDetails() {
        ToDo todo = new ToDo("todo read book");

        assertEquals("read book", todo.getTaskDesc());
        assertEquals("T", todo.getTaskIcon());
        assertEquals("[ ]", todo.statusIcon());
    }
}
