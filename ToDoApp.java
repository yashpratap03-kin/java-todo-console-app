import java.io.*;
import java.util.*;

class ToDoApp {
    static ArrayList<String> tasks = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static File file = new File("tasks.txt");

    public static void main(String[] args) {
        loadTasks();
        int choice;
        
        do {
            System.out.println("\n===== TO-DO LIST MENU =====");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Complete");
            System.out.println("4. Delete Task");
            System.out.println("5. Save & Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            
            switch(choice) {
                case 1 -> addTask();
                case 2 -> viewTasks();
                case 3 -> markComplete();
                case 4 -> deleteTask();
                case 5 -> saveTasks();
                default -> System.out.println("Invalid Choice!");
            }
        } while (choice != 5);
    }

    // Add Task
    static void addTask() {
        System.out.print("Enter task: ");
        tasks.add(sc.nextLine());
        System.out.println("Task Added!");
    }

    // Display Tasks
    static void viewTasks() {
        if(tasks.isEmpty()) {
            System.out.println("No tasks available!");
            return;
        }
        System.out.println("\nYour Tasks:");
        for(int i=0; i<tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i));
        }
    }

    // Mark Complete
    static void markComplete() {
        viewTasks();
        System.out.print("Enter task number to mark complete: ");
        int n = sc.nextInt() - 1;
        if(n>=0 && n<tasks.size()) {
            tasks.set(n, tasks.get(n) + " ✔ COMPLETED");
            System.out.println("Task Marked Complete!");
        } else {
            System.out.println("Invalid number!");
        }
    }

    // Delete Task
    static void deleteTask() {
        viewTasks();
        System.out.print("Enter task number to delete: ");
        int n = sc.nextInt() - 1;
        if(n>=0 && n<tasks.size()) {
            tasks.remove(n);
            System.out.println("Task Deleted!");
        } else {
            System.out.println("Invalid number!");
        }
    }

    // Save tasks to file
    static void saveTasks() {
        try (PrintWriter pw = new PrintWriter(file)) {
            for(String task : tasks) pw.println(task);
            System.out.println("Tasks Saved! Exiting...");
        } catch (Exception e) {
            System.out.println("Error saving tasks!");
        }
    }

    // Load tasks when app starts
    static void loadTasks() {
        try {
            if(file.exists()) {
                Scanner fr = new Scanner(file);
                while(fr.hasNextLine()) tasks.add(fr.nextLine());
                fr.close();
            }
        } catch (Exception e) {
            System.out.println("Error loading tasks!");
        }
    }
}
