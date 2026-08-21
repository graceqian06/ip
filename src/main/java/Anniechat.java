import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.IntStream.range;

public class Anniechat {
    public static void main(String[] args) {
        String greet = "Hello! I'm Anniechat.";
        String exit = "Byeee! Cya again soon!";
        System.out.println(greet);
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        List<Task> storage = new ArrayList<>();
        List<Boolean> completed = new ArrayList<>();
        while (true) {
            String echo = scanner.nextLine();
            if (echo.equals("bye")) {
                System.out.println(exit);
                break;
            }
            else if (echo.equals("list")) {
                int len = storage.size();
                System.out.println("a glimpse of ur tasks :)");
                for (int i = 0; i < len; i ++){
                        Task t = storage.get(i);
                        System.out.println(i+1 + "." + t.taskIcon() + t.statusIcon() + t.getTaskDesc());
                }
            } else if (echo.startsWith("mark ")) {
                int user_number = Integer.parseInt(echo.substring(5));
                int sys_number = user_number -1;
                Task t = storage.get(sys_number);
                t.markDone();

            }
            else if (echo.startsWith("unmark ")) {
                int user_number = Integer.parseInt(echo.substring(7));
                int sys_number = user_number - 1;
                Task t = storage.get(sys_number);
                t.markUndone();
            } else if (echo.startsWith("todo ")) {
                if (echo.substring(5).isEmpty()){
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    continue;
                };
                Task t = new ToDo(echo);
                storage.add(t);
            }
            else if (echo.startsWith("deadline ")) {
                if (echo.substring(9).isEmpty()){
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                    continue;
                };
                Task t = new Deadline(echo);
                storage.add(t);
            }
            else if (echo.startsWith("event ")) {
                if (echo.substring(6).isEmpty()){
                    System.out.println("OOPS!!! The description of an event cannot be empty.");
                    continue;
                };
                Task t = new Event(echo);
                storage.add(t);
            } else if (echo.startsWith("delete ")) {
                int user_number = Integer.parseInt(echo.substring(7));
                int sys_number = user_number - 1;
                Task t = storage.get(sys_number);
                storage.remove(t);
                Task.removeTask();
                System.out.println("Noted. I've removed this task:");
                System.out.println(t.taskIcon() + t.statusIcon() + t.getTaskDesc());
                System.out.println("Now you have " + Task.taskCount() + " tasks in the list.");
            } else {
                System.out.println("Sowwy idk what that means :(");
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
        List<String> storage = new ArrayList<>();
        List<Boolean> completed = new ArrayList<>();
        while (true) {
            String echo = scanner.nextLine();
            if (echo.equals("bye")) {
                System.out.println(exit);
                break;
            }
            else if (echo.equals("list")) {
                int len = storage.size();
                System.out.println("a glimpse of ur tasks :)");
                for (int i = 0; i < len; i ++){
                    if (completed.get(i).equals(false)) {
                        System.out.println(i+1 + ".[ ]" + storage.get(i));
                    }
                    else {
                        System.out.println(i+1 + ".[x]" + storage.get(i));
                    }
                }
            } else if (echo.startsWith("mark ")) {
                System.out.println(" Nice! I've marked this task as done: \n");
                int user_number = Integer.parseInt(echo.substring(5));
                int sys_number = user_number -1;
                completed.set(sys_number, true);
                System.out.println("[x]" + storage.get(sys_number));

            }
            else if (echo.startsWith("unmark ")) {
                System.out.println(" OK, I've marked this task as not done yet: \n");
                int user_number = Integer.parseInt(echo.substring(7));
                int sys_number = user_number - 1;
                completed.set(sys_number, false);
                System.out.println("[ ]" + storage.get(sys_number));
            }
            else {
                System.out.println("added: "+echo);
                storage.add(echo);
                completed.add(false);
            }
        }
    }
}

 */