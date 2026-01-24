package gus.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeadlineTaskTest {
        
    @Test
    public void testDeadlineCreation() {
        DeadlineTask deadlinet = new DeadlineTask("Eat", "2026-01-24 1800");
        assertEquals("Eat", deadlinet.getTitle());
        assertEquals("[D][ ] Eat (by Jan 24 2026 18:00)", deadlinet.toString());
    }

    @Test void testOccursOn_differentDate() {
        DeadlineTask deadlinet = new DeadlineTask("Eat", "2026-01-24 1800");
        LocalDate date = LocalDate.of(2026, 1, 23);
        assertFalse(deadlinet.occursOn(date));
    }

    @Test void testOccursOn_sameDate() {
        DeadlineTask deadlinet = new DeadlineTask("Eat", "2026-01-24 1800");
        LocalDate date = LocalDate.of(2026, 1, 24);
        assertTrue(deadlinet.occursOn(date));
    }
}
