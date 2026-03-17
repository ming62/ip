package gus.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import gus.exception.GusException;

public class EventTaskTest {

    @Test
    public void constructor_creates() throws GusException {
        EventTask event = new EventTask("Conference", "2026-03-10 0900", "2026-03-10 1100");

        assertEquals("Conference", event.getTitle());
        assertEquals(LocalDateTime.of(2026, 3, 10, 9, 0), event.getFrom());
        assertEquals(LocalDateTime.of(2026, 3, 10, 11, 0), event.getTo());
        assertEquals("2026-03-10 0900", event.getFromInputString());
        assertEquals("2026-03-10 1100", event.getToInputString());
        assertEquals("Mar 10 2026 09:00", event.getFromOutputString());
        assertEquals("Mar 10 2026 11:00", event.getToOutputString());
    }

    @Test
    public void occursOn_rangeBoundaries() throws GusException {
        EventTask event = new EventTask("Trip", "2026-03-10 0900", "2026-03-12 1100");

        assertTrue(event.occursOn(LocalDate.of(2026, 3, 10)));
        assertTrue(event.occursOn(LocalDate.of(2026, 3, 11)));
        assertTrue(event.occursOn(LocalDate.of(2026, 3, 12)));
        assertFalse(event.occursOn(LocalDate.of(2026, 3, 9)));
        assertFalse(event.occursOn(LocalDate.of(2026, 3, 13)));
    }

    @Test
    public void toString_formatted() throws GusException {
        EventTask event = new EventTask("Conference", "2026-03-10 0900", "2026-03-10 1100");

        assertEquals("[E] [ ][ ] Conference (from: Mar 10 2026 09:00 to: Mar 10 2026 11:00)", event.toString());
    }

    @Test
    public void testConstructor_whenEndIsEqualOrBeforeStart_throws() {
        GusException equalTimeException = assertThrows(GusException.class, () ->
            new EventTask("Conference", "2026-03-10 0900", "2026-03-10 0900"));
        assertEquals("I'm afraid the end time must be after the start time.", equalTimeException.getMessage());

        GusException beforeTimeException = assertThrows(GusException.class, () ->
            new EventTask("Conference", "2026-03-10 0900", "2026-03-10 0800"));
        assertEquals("I'm afraid the end time must be after the start time.", beforeTimeException.getMessage());
    }
}
