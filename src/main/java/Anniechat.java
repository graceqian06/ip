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
            else {
                System.out.println("added: "+echo);
                storage.add(echo);
                completed.add(false);
            }
        }
    }
}


/*
 else if (echo.startsWith("unmark ")) {
                System.out.println(" OK, I've marked this task as not done yet: \n");
                int user_number = Integer.parseInt(echo.substring(7));
                int sys_number = user_number - 1;
                completed.set(sys_number, false);
                System.out.println("[ ]" + storage.get(sys_number));
            }
 */