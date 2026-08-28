package anniechat.task;

public abstract class Task {
    protected String desc;
    protected boolean isDone;
    static int taskCount = 0;

    public Task(String desc){
        this.desc = desc;
        isDone = false;
        taskCount ++;
    }

    public static int taskCount() {
        return taskCount;
    }

    public static void removeTask(){
        taskCount--;
    }

    public void markDone() {
        isDone = true;
    }
    public void markUndone() {
        isDone = false;
    }

    public boolean checkStatus(){
        return isDone;
    }

    public int getTaskCount(){
        return taskCount;
    }

    public String getTaskDesc(){
        return desc;
    }
    public String statusIcon() {
        return isDone? "[x]" :"[ ]";
    }
    public abstract String getTaskIcon();

    public abstract String toSaveFormat();
}
