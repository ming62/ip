package gus.ui;

import java.time.LocalDate;
import java.util.ArrayList;

import gus.exception.GusException;
import gus.task.Task;

/**
 * Manages a list of tasks and provides operations to manipulate them.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates a TaskList with the given ArrayList of tasks.
     *
     * @param t The list of tasks.
     */
    public TaskList(ArrayList<Task> t) {
        this.tasks = t;
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the number of tasks.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Checks if the indices are valid
     *
     * @throws GusException if any of the indices is invalid
     */
    private void checkIndices(int... indices) throws GusException {
        for (int index : indices) {
            if (index < 0 || index >= tasks.size()) {
                throw new GusException("I'm sorry, but that task number does not exist: " + (index + 1));
            }
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to add.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }


    /**
     * Deletes tasks at the given indices.
     *
     * @param indices The indices of tasks to delete.
     * @return Array of deleted tasks.
     * @throws GusException If any index is invalid.
     */
    public Task[] deleteTask(int... indices) throws GusException {

        checkIndices(indices);

        Task[] deletedTasks = new Task[indices.length];

        for (int i = 0; i < indices.length; i++) {

            assert indices[i] < tasks.size() && indices[i] >= 0 : "Index should not be out of bound";
            deletedTasks[i] = tasks.remove(indices[i]);
        }

        return deletedTasks;
    }

    /**
     * Marks tasks as done and returns them.
     *
     * @param indices The indices of tasks to mark.
     * @return Array of marked tasks.
     * @throws GusException If any index is invalid.
     */
    public Task[] markTask(int... indices) throws GusException {

        checkIndices(indices);

        Task[] markedTasks = new Task[indices.length];

        for (int i = 0; i < indices.length; i++) {
            assert indices[i] < tasks.size() && indices[i] >= 0 : "Index should not be out of bound";
            tasks.get(indices[i]).mark();
            markedTasks[i] = tasks.get(indices[i]);
        }

        return markedTasks;
    }

    /**
     * Marks tasks as undone and returns them.
     *
     * @param indices The indices of tasks to unmark.
     * @return Array of unmarked tasks.
     * @throws GusException If any index is invalid.
     */
    public Task[] unmarkTask(int... indices) throws GusException {
        
        checkIndices(indices);

        Task[] unmarkedTasks = new Task[indices.length];

        for (int i = 0; i < indices.length; i++) {
            assert indices[i] < tasks.size() && indices[i] >= 0 : "Index should not be out of bound";
            tasks.get(indices[i]).unmark();
            unmarkedTasks[i] = tasks.get(indices[i]);
        }

        return unmarkedTasks;
    }

    /**
     * Gets the task list as a string.
     *
     * @return The formatted task list string.
     */
    public String getListString() {
        if (this.isEmpty()) {
            return "You have no tasks at the moment.";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s \n", i + 1, tasks.get(i).toString()));
        }

        return sb.toString();
    }

    /**
     * Gets the tasks that occur on a specific date.
     *
     * @param date The date to search for.
     * @return Array of tasks that occur on the specified date.
     */
    public Task[] getListByDate(LocalDate date) {

        return tasks.stream().filter(task -> task.occursOn(date)).toArray(Task[]::new);
    }

    /**
     * Gets the tasks that have a certain keyword.
     *
     * @param k The keyword to search for.
     * @return Array of tasks that contain the keyword.
     */
    public Task[] getListByKeyword(String k) {

        return tasks.stream().filter(task -> task.getTitle().contains(k)).toArray(Task[]::new);
    }

}
