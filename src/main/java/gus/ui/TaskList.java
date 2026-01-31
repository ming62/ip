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
     * Adds a task to the list.
     *
     * @param t The task to add.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Gets a task at that index.
     *
     * @param index The task index.
     * @return The task at that index.
     * @throws GusException If the index is invalid.
     */
    public Task getTask(int index) throws GusException {
        if (index < 0 || index >= tasks.size()) {
            throw new GusException("I'm sorry, but that task number does not exist.");
        }
        return tasks.get(index);
    }

    /**
     * Deletes a task at that index.
     *
     * @param index The task index.
     * @return The deleted task.
     * @throws GusException If the index is invalid.
     */
    public Task deleteTask(int index) throws GusException {
        if (index < 0 || index >= tasks.size()) {
            throw new GusException("I'm sorry, but that task number does not exist.");
        }
        return tasks.remove(index);
    }

    /**
     * Marks a task as done.
     *
     * @param index The task index.
     * @throws GusException If the index is invalid.
     */
    public void markTask(int index) throws GusException {
        getTask(index).mark();
    }

    /**
     * Marks a task as not done.
     *
     * @param index The task index.
     * @throws GusException If the index is invalid.
     */
    public void unmarkTask(int index) throws GusException {
        getTask(index).unmark();
    }

    /**
     * Gets the task list as a string.
     *
     * @return The formatted task list string.
     */
    public String getListString() {
        if (this.isEmpty()) {
            return "         You have no tasks at the moment.";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("         %d. %s \n", i + 1, tasks.get(i).toString()));
        }

        return sb.toString();
    }

    /**
     * Gets the tasks that occur on a specific date.
     *
     * @param date The date to search for.
     * @return The formatted string of the list of tasks on that date.
     */
    public String getListByDate(LocalDate date) {
        int count = 0;
        StringBuilder sb = new StringBuilder();

        for (Task t : tasks) {
            if (t.occursOn(date)) {
                sb.append(String.format("         %d. %s \n", count + 1, t.toString()));
                count++;
            }
        }

        if (count == 0) {
            return "         I found no tasks scheduled for this date.";
        }
        return sb.toString();
    }

    /**
     * Gets the tasks that has a certain keyword
     *
     * @param k The keyword to search for.
     * @return The formatted string of the list of tasks that has this keyword.
     */
    public String getListByKeyword(String k) {
        int count = 0;
        StringBuilder sb = new StringBuilder();

        for (Task t : tasks) {
            if (t.getTitle().contains(k)) {
                sb.append(String.format("         %d. %s \n", count + 1, t.toString()));
                count++;
            }
        }

        if (count == 0) {
            return "         I found no matching tasks for: " + k + ".";
        }
        return sb.toString();
    }

}
