public class ToDo extends Task {
    String taskDesc;

    public ToDo(String desc) {
        super(desc);
        taskDesc = desc.substring(5);

    }

    @Override
    public String getTaskDesc() {
        return taskDesc;
    }

    @Override
    public String getTaskIcon(){
        return "T";
    }
    @Override
    public String toSaveFormat(){
        return this.getTaskIcon() + " | " + (isDone? "1" : "0") + " | " + this.taskDesc;
    }
}
