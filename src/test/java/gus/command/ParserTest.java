package gus.command;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import gus.exception.GusException;

public class ParserTest {

    @Test
    public void parseCommand_valid() {
        assertEquals(Command.LIST, Parser.parseCommand("list"));
        assertEquals(Command.BYE, Parser.parseCommand("bye"));
        assertEquals(Command.MARK, Parser.parseCommand("mark 1"));
        assertEquals(Command.UNMARK, Parser.parseCommand("UNMARK 2"));
        assertEquals(Command.DELETE, Parser.parseCommand("delete 3"));
        assertEquals(Command.TODO, Parser.parseCommand("todo read"));
        assertEquals(Command.DEADLINE, Parser.parseCommand("deadline report /by 2026-01-24 1800"));
        assertEquals(Command.EVENT, Parser.parseCommand("event meeting /from 2026-01-24 1800 /to 2026-01-24 1900"));
        assertEquals(Command.ON, Parser.parseCommand("on 2026-01-24"));
        assertEquals(Command.FIND, Parser.parseCommand("find report"));
        assertEquals(Command.PRI, Parser.parseCommand("pri top 1"));
        assertEquals(Command.ELSE, Parser.parseCommand("unknown"));
    }

    @Test
    public void parseTaskIndex_valid() throws GusException {
        int index = Parser.parseTaskIndex("mark 3", "mark");
        assertEquals(2, index);
    }

    @Test
    public void parseTaskIndex_missing_throws() {
        GusException exception = assertThrows(GusException.class, () -> Parser.parseTaskIndex("mark", "mark"));
        assertEquals("Please specify which task number.", exception.getMessage());
    }

    @Test
    public void parseTaskIndex_invalid_throws() {
        GusException exception = assertThrows(GusException.class, () -> Parser.parseTaskIndex("mark x", "mark"));
        assertEquals("I need a valid task number, please.", exception.getMessage());
    }

    @Test
    public void parseTaskIndices_sorts() throws GusException {
        int[] indices = Parser.parseTaskIndices("delete 3 1 2", "delete");
        assertArrayEquals(new int[] { 2, 1, 0 }, indices);
    }

    @Test
    public void parseTaskIndices_invalid_throws() {
        GusException exception = assertThrows(GusException.class, () ->
            Parser.parseTaskIndices("delete 1 x", "delete"));
        assertEquals("Invalid task number: x", exception.getMessage());
    }

    @Test
    public void parseDeadline_valid() throws GusException {
        String[] parts = Parser.parseDeadline("deadline report /by 2026-01-24 1800");
        assertArrayEquals(new String[] { "report", "2026-01-24 1800" }, parts);
    }

    @Test
    public void parseDeadline_missing_throws() {
        GusException exception = assertThrows(GusException.class, () ->
            Parser.parseDeadline("deadline report 2026-01-24 1800"));
        assertEquals("Please specify the deadline using /by.", exception.getMessage());
    }

    @Test
    public void parseEvent_valid() throws GusException {
        String[] parts = Parser.parseEvent("event meeting /from 2026-01-24 1800 /to 2026-01-24 1900");
        assertArrayEquals(new String[] { "meeting", "2026-01-24 1800", "2026-01-24 1900" }, parts);
    }

    @Test
    public void parseEvent_missing_throws() {
        GusException exception = assertThrows(GusException.class, () ->
            Parser.parseEvent("event meeting /from 2026-01-24 1800"));
        assertEquals("Please specify both the start time with /from and end time with /to.", exception.getMessage());
    }

    @Test
    public void parseDate_valid() throws GusException {
        LocalDate date = Parser.parseDate("on 2026-12-31");
        assertEquals(LocalDate.of(2026, 12, 31), date);
    }

    @Test
    public void parseDate_invalid_throws() {
        GusException exception = assertThrows(GusException.class, () -> Parser.parseDate("on 31-12-2026"));
        assertEquals("Please provide the date in the format yyyy-MM-dd.", exception.getMessage());
    }

    @Test
    public void parsePriority_valid() throws GusException {
        String[] parts = Parser.parsePriority("pri top 1 2");
        assertArrayEquals(new String[] { "TOP", "1", "2" }, parts);
    }

    @Test
    public void parsePriority_invalid_throws() {
        GusException exception = assertThrows(GusException.class, () -> Parser.parsePriority("pri urgent 1"));
        assertEquals("Invalid priority level. Please use TOP, MID, LOW, or NONE.", exception.getMessage());
    }
}
