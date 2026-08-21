public class Deadline extends Task{
    String descndeadline = desc.substring(9);
    String Desc = descndeadline.split("/")[0];
    String deadline = descndeadline.split("/")[1];
    String date = deadline.split(" ")[1];
    String taskDesc =  Desc + "(by: " + date + ")";
    public Deadline(String desc) {
        super(desc);

        System.out.println(" [D] [ ] " + taskDesc + "\n Now you have " + this.getTaskCount() + " tasks in the list.");
    }

    @Override
    public String getTaskDesc() {
        return taskDesc;
    }
    @Override
    public String taskIcon() {
        return "[D]";
    }
}
