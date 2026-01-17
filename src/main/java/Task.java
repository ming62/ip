public class Task {
    private String title;
    private boolean isDone;

    public Task(String t) {
        this.title = t;
        isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X": " ", this.title);
    }
}
