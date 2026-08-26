import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Anniechat {
    public static void main(String[] args) throws IOException {
        String greet = "Hello! I'm Anniechat.";
        String exit = "Byeee! Cya again soon!";
        System.out.println(greet);
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage("data/anniechat.txt");
        List<Task> tasks = storage.load();
        while (true) {
            String echo = scanner.nextLine();
            if (echo.equals("bye")) {
                System.out.println(exit);
                break;
            }
            else if (echo.equals("list")) {
                int len = tasks.size();
                System.out.println("a glimpse of ur tasks :)");
                for (int i = 0; i < len; i ++){
                        Task t = tasks.get(i);
                        System.out.println(i+1 + "." +"[" + t.getTaskIcon() + "]" + t.statusIcon() + t.getTaskDesc());
                }
            } else if (echo.startsWith("mark ")) {
                int user_number = Integer.parseInt(echo.substring(5));
                int sys_number = user_number -1;
                Task t = tasks.get(sys_number);
                t.markDone();
                storage.save(tasks);
                System.out.println(" Nice! I've marked this task as done: \n");
                System.out.println( t.statusIcon() + t.getTaskDesc());

            }
            else if (echo.startsWith("unmark ")) {
                int user_number = Integer.parseInt(echo.substring(7));
                int sys_number = user_number - 1;
                Task t = tasks.get(sys_number);
                t.markUndone();
                storage.save(tasks);
                System.out.println(" OK, I've marked this task as not done yet: \n");
                System.out.println( t.statusIcon() + t.getTaskDesc());
            } else if (echo.startsWith("todo ")) {
                if (echo.substring(5).isEmpty()){
                    emptyDesc();
                    continue;
                };
                Task t = new ToDo(echo);
                printAddedTask(t, t.getTaskCount());
                tasks.add(t);
                storage.save(tasks);
            }
            else if (echo.startsWith("deadline ")) {
                if (echo.substring(9).isEmpty()) {
                    emptyDesc();
                    continue;
                }
                Task t = new Deadline(echo);
                printAddedTask(t, t.getTaskCount());
                tasks.add(t);
                storage.save(tasks);

            }
            else if (echo.startsWith("event ")) {
                if (echo.substring(6).isEmpty()){
                    emptyDesc();
                    continue;
                };
                Task t = new Event(echo);
                printAddedTask(t, t.getTaskCount());
                tasks.add(t);
                storage.save(tasks);

            } else if (echo.startsWith("delete ")) {
                int user_number = Integer.parseInt(echo.substring(7));
                int sys_number = user_number - 1;
                Task t = tasks.get(sys_number);
                tasks.remove(t);
                Task.removeTask();
                storage.save(tasks);
                System.out.println("Noted. I've removed this task:");
                System.out.println("[" + t.getTaskIcon() + "]" + t.statusIcon() + t.getTaskDesc());
                System.out.println("Now you have " + Task.taskCount() + " tasks in the list.");
            } else {
                System.out.println("Sowwy idk what that means :(");
            }
        }
    }
    public static void printAddedTask(Task t, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + "[" + t.getTaskIcon() + "]" + t.statusIcon() + " " + t.getTaskDesc());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
    public static void emptyDesc(){
        System.out.println("OOPS!!! The description of an event cannot be empty.");
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
