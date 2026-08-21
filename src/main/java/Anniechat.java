import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Anniechat {
    public static void main(String[] args) {
        String greet = "Hello! I'm Anniechat.";
        String exit = "Byeee! Cya again soon!";
        System.out.println(greet);
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        List<String> storage = new ArrayList<>();
        int count = 0;
        while (true) {
            String echo = scanner.nextLine();
            if (echo.equals("bye")) {
                System.out.println(exit);
            }
            else if (echo.equals("list")) {
                for (String item: storage){
                    System.out.println(item);
                }
            }
            else {
                System.out.println("added: "+echo);
                count ++;
                storage.add(count + "." + echo);
            }
        }
    }
}
