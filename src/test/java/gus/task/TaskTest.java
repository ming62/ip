package gus.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import gus.task.Task;

public class TaskTest {
    
    @Test
    public void testTaskCreation() {
        Task task = new Task("Eat");
        assertEquals("Eat", task.getTitle());
        assertFalse(task.getIsDone());;
    }

    @Test
    public void testOccursOn_returnsFalse() {
        Task task = new Task("Eat");
        LocalDate date = LocalDate.of(2026, 1, 24);
        assertFalse(task.occursOn(date));
    }
}
