public class Deadline extends Task{
    //example: deadline return book /by Sunday
    String taskDesc;
    String date;
    String Desc;
    public Deadline(String desc) {
        super(desc);
        String descDeadline = desc.substring(9);
        Desc = descDeadline.split("/")[0];
        String deadline = descDeadline.split("/")[1];
        date = deadline.split(" ")[1];
        taskDesc =  Desc + "(by: " + date + ")";
    }
    @Override
    public String getTaskDesc() {
        return taskDesc;
    }
    @Override
    public String getTaskIcon() {
        return "D";
    }

    @Override
    public String toSaveFormat(){
        return this.getTaskIcon() + " | " + (isDone? "1" : "0") + " | " + this.Desc + " | " + this.date;
    }
}
