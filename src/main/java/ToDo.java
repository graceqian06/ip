public class ToDo extends Task {
    String taskDesc = desc.substring(5);

    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String getTaskDesc() {
        return taskDesc;
    }

    @Override
    public String getTaskIcon(){
        return "[T]";
    }

}
