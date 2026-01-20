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

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X": " ", this.title);
    }
}
