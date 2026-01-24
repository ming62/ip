import java.util.ArrayList;
import java.time.LocalDate;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> t) {
        this.tasks = t;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task getTask(int index) throws GusException {
        if (index < 0 || index >= tasks.size()) {
            throw new GusException("Task number is invalid!");
        }
        return tasks.get(index);
    }

    public Task deleteTask(int index) throws GusException {
        if (index < 0 || index >= tasks.size()) {
            throw new GusException("Task number is invalid!");
        }
        return tasks.remove(index);
    }

    public void markTask(int index) throws GusException {
        getTask(index).mark();
    }

    public void unmarkTask(int index) throws GusException {
        getTask(index).unmark();
    }

    public String getListSring() {
        if (this.isEmpty()) {
            return "         The list is now empty";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("         %d. %s \n", i + 1, tasks.get(i).toString()));
        }

        return sb.toString();
    }

    public String getListByDate(LocalDate date) {
        int count = 0;
        StringBuilder sb = new StringBuilder();

        for (Task t: tasks) {
            if (t.occursOn(date)) {
                sb.append(String.format("         %d. %s \n", count + 1, t.toString()));
                count++;
            }
        }

        if (count == 0) {
            return "         No tasks found on this date";
        }
        return sb.toString();
    }
    
}
