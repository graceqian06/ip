package anniechat;

import java.io.IOException;
import java.util.List;

import anniechat.parser.Parser;
import anniechat.storage.Storage;
import anniechat.task.Task;
import anniechat.ui.Ui;

public class Anniechat {
    public static void main(String[] args) throws IOException {
        Ui ui = new Ui();
        ui.showWelcome();
        Storage storage = new Storage("data/anniechat.txt");
        List<Task> tasks = storage.load();
        while (true) {
            String echo = ui.readCommand();
            Parser parser = new Parser(echo);

            String commandWord = parser.getCommandWord();

            if (commandWord.equals("bye")) {
                ui.showExit();
                break;
            } else if (commandWord.equals("list")) {
                ui.showTaskList(tasks);
            } else if (commandWord.equals("mark")) {
                int taskNumber = parser.getTaskNumber();
                Task t = tasks.get(taskNumber);
                t.markDone();
                storage.save(tasks);
                ui.showMarkedTask(t);

            } else if (commandWord.equals("unmark")) {
                int taskNumber = parser.getTaskNumber();
                Task t = tasks.get(taskNumber);
                t.markUndone();
                storage.save(tasks);
                ui.showUnmarkedTask(t);
            } else if (commandWord.equals("todo")) {
                if (parser.getTaskDescription().isEmpty()) {
                    ui.showEmptyDescription();
                    continue;
                }
                Task t = parser.createTask();
                ui.showAddedTask(t, t.getTaskCount());
                tasks.add(t);
                storage.save(tasks);
            } else if (commandWord.equals("deadline")) {
                if (parser.getTaskDescription().isEmpty()) {
                    ui.showEmptyDescription();
                    continue;
                }
                Task t = parser.createTask();
                ui.showAddedTask(t, t.getTaskCount());
                tasks.add(t);
                storage.save(tasks);

            } else if (commandWord.equals("event")) {
                if (parser.getTaskDescription().isEmpty()) {
                    ui.showEmptyDescription();
                    continue;
                }
                Task t = parser.createTask();
                ui.showAddedTask(t, t.getTaskCount());
                tasks.add(t);
                storage.save(tasks);

            } else if (commandWord.equals("delete")) {
                int taskNumber = parser.getTaskNumber();
                Task t = tasks.get(taskNumber);
                tasks.remove(t);
                Task.removeTask();
                storage.save(tasks);
                ui.showDeletedTask(t, Task.taskCount());
            } else {
                ui.showUnknownCommand();
            }
        }
    }
}





/*
public class Anniechat {
    public static void main(String[] args) {
        String greet = "Hello! I'm Anniechat.";
        String exit = "Byeee! Cya again soon!";
        System.out.println(greet);
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        List<String> tasks  = new ArrayList<>();
        List<Boolean> completed = new ArrayList<>();
        while (true) {
            String echo = scanner.nextLine();
            if (echo.equals("bye")) {
                System.out.println(exit);
                break;
            }
            else if (echo.equals("list")) {
                int len = tasks .size();
                System.out.println("a glimpse of ur tasks :)");
                for (int i = 0; i < len; i ++){
                    if (completed.get(i).equals(false)) {
                        System.out.println(i+1 + ".[ ]" + tasks .get(i));
                    }
                    else {
                        System.out.println(i+1 + ".[x]" + tasks .get(i));
                    }
                }
            } else if (echo.startsWith("mark ")) {
                System.out.println(" Nice! I've marked this task as done: \n");
                int user_number = Integer.parseInt(echo.substring(5));
                int sys_number = user_number -1;
                completed.set(sys_number, true);
                System.out.println("[x]" + tasks .get(sys_number));

            }
            else if (echo.startsWith("unmark ")) {
                System.out.println(" OK, I've marked this task as not done yet: \n");
                int user_number = Integer.parseInt(echo.substring(7));
                int sys_number = user_number - 1;
                completed.set(sys_number, false);
                System.out.println("[ ]" + tasks .get(sys_number));
            }
            else {
                System.out.println("added: "+echo);
                tasks .add(echo);
                completed.add(false);
            }
        }
    }
}

 */
