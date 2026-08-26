public class Event extends Task {
    String taskDesc;
        public Event(String desc) {
            super(desc);
            String longDesc = desc.substring(6);
            String taskOnly = longDesc.split("/from")[0];
            String day = desc.split("/from")[1];
            String start = day.split("/to")[0];
            String end = day.split("/to")[1];
            taskDesc =  taskOnly + "(from: " + start
                    + "to: " + end +")";
        }

        @Override
        public String getTaskDesc() {
            return taskDesc;
        }
        @Override
        public String getTaskIcon() {
            return "[E]";
        }
    }

