package anniechat.ui;

import anniechat.task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    /** Creates a user-interface object that reads from standard input. */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /** Displays the welcome message. */
    public void showWelcome() {
        System.out.println("Hello! I'm Anniechat.\nWhat can I do for you?\n");
    }

    /** Reads one command from the user. */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /** Displays the exit message. */
    public void showExit() {
        System.out.println("Byeee! Cya again soon!");
    }

    /** Displays all tasks in the task list. */
    public void showTaskList(List<Task> tasks) {
        int len = tasks.size();
        System.out.println("a glimpse of ur tasks :)");
        for (int i = 0; i < len; i++) {
            Task t = tasks.get(i);
            System.out.println(i + 1 + "." + "[" + t.getTaskIcon() + "]"
                    + t.statusIcon() + t.getTaskDesc());
        }
    }

    /** Displays a task that was marked as done. */
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.println(task.statusIcon() + task.getTaskDesc());
    }

    /** Displays a task that was marked as not done. */
    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n");
        System.out.println(task.statusIcon() + task.getTaskDesc());
    }

    /** Displays a newly added task and the number of tasks in the list. */
    public void showAddedTask(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + "[" + task.getTaskIcon() + "]"
                + task.statusIcon() + " " + task.getTaskDesc());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /** Displays a task that was deleted and the remaining task count. */
    public void showDeletedTask(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("[" + task.getTaskIcon() + "]"
                + task.statusIcon() + task.getTaskDesc());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /** Displays an error for a task command with no description. */
    public void showEmptyDescription() {
        System.out.println("OOPS!!! The description of a task cannot be empty.");
    }

    /** Displays an error for an unrecognised command. */
    public void showUnknownCommand() {
        System.out.println("Sowwy idk what that means :(");
    }
}
