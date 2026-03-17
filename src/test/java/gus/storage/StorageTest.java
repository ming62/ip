package gus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import gus.exception.GusException;
import gus.task.DeadlineTask;
import gus.task.EventTask;
import gus.task.Priority;
import gus.task.Task;
import gus.task.TodoTask;

public class StorageTest {

    @TempDir
    Path tempDir;

    @Test
    public void load_missing_createsDirectory() throws GusException {
        Path dataFile = tempDir.resolve("nested").resolve("gus.txt");
        Storage storage = new Storage(dataFile.toString());

        ArrayList<Task> tasks = storage.load();

        assertTrue(tasks.isEmpty());
        assertTrue(Files.exists(tempDir.resolve("nested")));
    }

    @Test
    public void parseLine_valid() throws GusException {
        Storage storage = new Storage(tempDir.resolve("gus.txt").toString());

        Task task = storage.parseLine("T | TOP | 1 | read book");

        assertEquals("read book", task.getTitle());
        assertTrue(task.isDone());
        assertEquals(Priority.TOP, task.getPriority());
    }

    @Test
    public void parseLine_invalid_throws() {
        Storage storage = new Storage(tempDir.resolve("gus.txt").toString());

        GusException exception = assertThrows(GusException.class, () -> storage.parseLine("bad line"));

        assertEquals("I'm afraid the data file appears to be corrupted.", exception.getMessage());
    }

    @Test
    public void saveAndLoad_preservesData() throws GusException {
        Path dataFile = tempDir.resolve("data").resolve("gus.txt");
        Storage storage = new Storage(dataFile.toString());

        ArrayList<Task> tasksToSave = new ArrayList<>();
        Task todo = new TodoTask("read");
        todo.mark();
        todo.setPriority(Priority.TOP);

        Task deadline = new DeadlineTask("submit", "2026-08-01 1700");
        deadline.setPriority(Priority.MID);

        Task event = new EventTask("meeting", "2026-08-02 0900", "2026-08-02 1000");
        event.setPriority(Priority.LOW);

        tasksToSave.add(todo);
        tasksToSave.add(deadline);
        tasksToSave.add(event);

        storage.save(tasksToSave);
        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(3, loadedTasks.size());
        assertEquals(storage.formatLine(tasksToSave.get(0)), storage.formatLine(loadedTasks.get(0)));
        assertEquals(storage.formatLine(tasksToSave.get(1)), storage.formatLine(loadedTasks.get(1)));
        assertEquals(storage.formatLine(tasksToSave.get(2)), storage.formatLine(loadedTasks.get(2)));
    }
}
