import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task{
    //example: deadline return book /by Sunday
    private final LocalDate deadline;
    private String taskOnly;
    private String taskDesc;

    public Deadline(String desc) {
        super(desc);
        String descDeadline = desc.split(" ", 2)[1];
        String[] parts  = descDeadline.split("/by ",2);
        taskOnly = parts[0];
        deadline = LocalDate.parse(parts[1]);
        taskDesc = taskOnly + " (by: "
                + deadline.format(OUTPUT_FORMAT) + ")";    }
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
        return this.getTaskIcon() + " | " + (isDone? "1" : "0") + " | " + this.taskOnly + " | " + this.deadline;
    }
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);

}
