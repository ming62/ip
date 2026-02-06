package gus.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import gus.exception.GusException;
import gus.task.DeadlineTask;
import gus.task.EventTask;
import gus.task.Task;
import gus.task.TodoTask;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private Path filePath;

    /**
     * Creates a Storage with the given file path.
     *
     * @param s The file path as a string.
     */
    public Storage(String s) {
        this.filePath = Paths.get(s);
    }

    /**
     * Loads tasks from the file.
     *
     * @return The list of tasks.
     * @throws GusException If there is an error loading the file.
     */
    public ArrayList<Task> load() throws GusException {

        ArrayList<Task> tasks = new ArrayList<>();

        Path directory = filePath.getParent();
        if (directory != null && !Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                throw new GusException("I apologize, but there was an error creating the directory.");
            }
        }

        if (!Files.exists(filePath)) {
            return tasks;
        }

        try {
            File taskFile = filePath.toFile();
            Scanner scanner = new Scanner(taskFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseLine(line);
                if (task != null) {
                    tasks.add(task);
                }
                ;
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            throw new GusException("I'm afraid the data file could not be found.");
        }

        return tasks;
    }

    /**
     * Parses a line from the data file into a task.
     *
     * @param s The line to parse.
     * @return The parsed task.
     * @throws GusException If the line is corrupted.
     */
    public Task parseLine(String s) throws GusException {
        try {
            String[] details = s.split(" \\| ");

            String type = details[0];
            boolean isDone = details[1].equals("1");
            String title = details[2];

            Task task = null;

            switch (type) {
            case "T":
                task = new TodoTask(title);
                break;
            case "D":
                task = new DeadlineTask(title, details[3]);
                break;
            case "E":
                String[] furDetails = details[3].split(" to: ");
                String from = furDetails[0].replace("from: ", "");
                String to = furDetails[1];
                task = new EventTask(title, from, to);
                break;
            default:
                break;
            }

            if (task != null) {
                if (isDone) {
                    task.mark();
                }
            }

            return task;
        } catch (Exception e) {
            throw new GusException("I'm afraid the data file appears to be corrupted.");
        }
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks The list of tasks to save.
     * @throws GusException If there is an error saving the file.
     */
    public void save(ArrayList<Task> tasks) throws GusException {

        Path directory = filePath.getParent();
        if (directory != null && !Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                throw new GusException("I apologize, but there was an error creating the directory.");
            }
        }

        try {
            FileWriter writer = new FileWriter(filePath.toFile());
            String content = tasks.stream().map(task -> formatLine(task)).collect(Collectors.joining("\n"));

            writer.write(content + "\n");
            writer.close();
        } catch (IOException e) {
            throw new GusException("I apologize, but there was an error saving your tasks.");
        }

    }

    /**
     * Formats a task into a line for saving to file.
     *
     * @param task The task to format.
     * @return The formatted line.
     */
    public String formatLine(Task task) {
        String type = "";

        String details = "";

        if (task instanceof TodoTask) {
            type = "T";
        } else if (task instanceof DeadlineTask) {
            type = "D";
            details = String.format("| %s", ((DeadlineTask) task).getDeadlineInputString());
        } else if (task instanceof EventTask) {
            type = "E";
            details = String.format("| from: %s to: %s", ((EventTask) task).getFromInputString(), ((EventTask) task)
                    .getToInputString());
        }

        return String.format("%s | %s | %s %s", type, task.isDone() ? "1" : "0", task.getTitle(), details);
    }

}
