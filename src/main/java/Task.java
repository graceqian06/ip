public class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc){
        this.desc = desc;
        isDone = false;
    }
    public String statusIcon() {
        return isDone? "[x]" :"[ ]";
    }

    public void markDone() {
        isDone = true;
    }
    public void markUndone() {
        isDone = false;
    }
    public String getTaskDesc(){
        return desc;
    }
    public boolean checkStatus(){
        return isDone;
    }

}
