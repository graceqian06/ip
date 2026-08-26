public class Deadline extends Task{
    String taskDesc;
    public Deadline(String desc) {
        super(desc);
        String descDeadline = desc.substring(9);
        String Desc = descDeadline.split("/")[0];
        String deadline = descDeadline.split("/")[1];
        String date = deadline.split(" ")[1];
        taskDesc =  Desc + "(by: " + date + ")";
    }
    @Override
    public String getTaskDesc() {
        return taskDesc;
    }
    @Override
    public String getTaskIcon() {
        return "[D]";
    }
}
