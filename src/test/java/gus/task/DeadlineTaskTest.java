package gus.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {

    @Test
    public void constructor_creates() {
        DeadlineTask deadlinet = new DeadlineTask("Eat", "2026-01-24 1800");
        assertEquals("Eat", deadlinet.getTitle());
        assertEquals("[D] [ ][ ] Eat (by Jan 24 2026 18:00)", deadlinet.toString());
    }

    @Test
    void gettersAndFormats_valid() {
        DeadlineTask deadlinet = new DeadlineTask("Eat", "2026-01-24 1800");

        assertEquals(LocalDateTime.of(2026, 1, 24, 18, 0), deadlinet.getDeadline());
        assertEquals("2026-01-24 1800", deadlinet.getDeadlineInputString());
        assertEquals("Jan 24 2026 18:00", deadlinet.getDeadlineOutputString());
    }

    @Test
    void occursOn_differentDate_false() {
        DeadlineTask deadlinet = new DeadlineTask("Eat", "2026-01-24 1800");
        LocalDate date = LocalDate.of(2026, 1, 23);
        assertFalse(deadlinet.occursOn(date));
    }

    @Test
    void occursOn_sameDate_true() {
        DeadlineTask deadlinet = new DeadlineTask("Eat", "2026-01-24 1800");
        LocalDate date = LocalDate.of(2026, 1, 24);
        assertTrue(deadlinet.occursOn(date));
    }
}
