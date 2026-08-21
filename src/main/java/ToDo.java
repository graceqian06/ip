public class ToDo extends Task {
    String taskDesc = desc.substring(5);

    public ToDo(String desc) {
        super(desc);
        System.out.println(" [T] [ ] " + taskDesc + "\n Now you have " + this.getTaskCount() + " tasks in the list.");
    }

    @Override
    public String getTaskDesc() {
        return taskDesc;
    }
    @Override
    public String taskIcon() {
        return "[T]";
    }
}
