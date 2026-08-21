public class Event extends Task {
        String long_desc = desc.substring(6);
        String task_only = long_desc.split("/from")[0];
        String day = desc.split("/from")[1];
        String start = day.split("/to")[0];
        String end = day.split("/to")[1];
        String taskDesc =  task_only + "(from: " + start
                + "to: " + end +")";
        public Event(String desc) {
            super(desc);
            System.out.println(" [E] [ ] " + taskDesc + "\n Now you have " + this.getTaskCount() + " tasks in the list.");
        }

        @Override
        public String getTaskDesc() {
            return taskDesc;
        }
        @Override
        public String taskIcon() {
            return "[E]";
        }
    }

