package gus.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testTaskCreation() {
        Task task = new Task("Eat");
        assertEquals("Eat", task.getTitle());
        assertFalse(task.isDone());
        ;
    }

    @Test
    public void testOccursOn_returnsFalse() {
        Task task = new Task("Eat");
        LocalDate date = LocalDate.of(2026, 1, 24);
        assertFalse(task.occursOn(date));
    }
}
