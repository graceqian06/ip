package anniechat.task;

/** Represents a task with a description and completion status. */
public abstract class Task {
    /** Original command or description used to create this task. */
    protected String desc;

    /** Whether this task has been marked as completed. */
    protected boolean isDone;
    static int taskCount = 0;

    /**
     * Creates an incomplete task with the given description.
     *
     * @param desc original command or description for this task.
     */
    public Task(String desc) {
        this.desc = desc;
        isDone = false;
        taskCount++;
    }

    /**
     * Returns the total number of task objects created in this run.
     *
     * @return total number of task objects created.
     */
    public static int taskCount() {
        return taskCount;
    }

    /** Decreases the total task count after a task is deleted. */
    public static void removeTask() {
        taskCount--;
    }

    /** Marks this task as completed. */
    public void markDone() {
        isDone = true;
    }

    /** Marks this task as incomplete. */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Returns whether this task is completed.
     *
     * @return true if this task is completed.
     */
    public boolean checkStatus() {
        return isDone;
    }

    /**
     * Returns the total number of task objects created in this run.
     *
     * @return total number of task objects created.
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Returns the display description of this task.
     *
     * @return display description of this task.
     */
    public String getTaskDesc() {
        return desc;
    }

    /**
     * Returns the icon representing this task's completion status.
     *
     * @return {@code [x]} if completed, otherwise {@code [ ]}.
     */
    public String statusIcon() {
        return isDone ? "[x]" : "[ ]";
    }

    /**
     * Returns the icon representing this task type.
     *
     * @return one-letter icon for this task type.
     */
    public abstract String getTaskIcon();

    /**
     * Returns this task in the format used by storage.
     *
     * @return serialized representation of this task.
     */
    public abstract String toSaveFormat();
}
