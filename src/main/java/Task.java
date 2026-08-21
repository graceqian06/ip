public abstract class Task {
    protected String desc;
    protected boolean isDone;
    static int taskCount = 0;
    public Task(String desc){
        this.desc = desc;
        isDone = false;
        taskCount ++;
        System.out.println( "Got it. I've added this task: \n");
    }

    public static int taskCount() {
        return taskCount;
    }
    public static void removeTask(){
        taskCount--;
    }


    public String statusIcon() {
        return isDone? "[x]" :"[ ]";
    }

    public void markDone() {
        isDone = true;
        System.out.println(" Nice! I've marked this task as done: \n");
        System.out.println( this.statusIcon() + this.getTaskDesc());
    }
    public void markUndone() {
        isDone = false;
        System.out.println(" OK, I've marked this task as not done yet: \n");
        System.out.println( this.statusIcon() + this.getTaskDesc());
    }
    public String getTaskDesc(){
        return desc;
    }
    public boolean checkStatus(){
        return isDone;
    }
    public int getTaskCount(){
        return taskCount;
    }
    abstract String taskIcon();
}
