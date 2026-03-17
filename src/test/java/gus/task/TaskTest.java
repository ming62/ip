package gus.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void constructor_creates() {
        Task task = new Task("Eat");
        assertEquals("Eat", task.getTitle());
        assertFalse(task.isDone());
    }

    @Test
    public void markAndUnmark_toggles() {
        Task task = new Task("Eat");

        task.mark();
        assertTrue(task.isDone());

        task.unmark();
        assertFalse(task.isDone());
    }

    @Test
    public void setPriority_reflected() {
        Task task = new Task("Eat");

        task.setPriority(Priority.TOP);

        assertEquals(Priority.TOP, task.getPriority());
        assertEquals("[!][ ] Eat", task.toString());
    }

    @Test
    public void occursOn_false() {
        Task task = new Task("Eat");
        LocalDate date = LocalDate.of(2026, 1, 24);
        assertFalse(task.occursOn(date));
    }
}
