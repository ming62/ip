import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private Path filePath;

    public Storage(String s) {
        this.filePath = Paths.get(s);
    }

    public ArrayList<Task> load() throws GusException {

        ArrayList<Task> tasks = new ArrayList<>();

        Path directory = filePath.getParent();
        if (directory != null && !Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                throw new GusException("We have an error while creating directory");
            }
        }

        if (!Files.exists(filePath)) {
            return tasks;
        }

        try{
            File taskFile = filePath.toFile();
            Scanner scanner = new Scanner(taskFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseLine(line);
                if (task != null) {tasks.add(task);};
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            throw new GusException("We did not find the file to be loaded");
        }

        return tasks;
    }

    public Task parseLine(String s) throws GusException{
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
            }

            if (task != null) {
                if (isDone) {
                    task.mark();
                }
            }

            return task;
        } catch (Exception e) {
            throw new GusException("Data file is corrupted");
        }
    }

    public void save(ArrayList<Task> tasks) throws GusException {

        Path directory = filePath.getParent();
        if (directory != null && !Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                throw new GusException("We have an error while creating directory");
            }
        }

        try {
            FileWriter writer = new FileWriter(filePath.toFile());
            for (Task task : tasks) {
                writer.write(formatLine(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new GusException("We have an error while saving tasks");
        }

    }

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
            details = String.format("| from: %s to: %s", ((EventTask) task).getFromInputString(), ((EventTask) task).getToInputString());
        } 

        return String.format("%s | %s | %s %s", type, task.getIsDone() ? "1" : "0", task.getTitle(), details);
    }


}
