//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Scanner;

// hello cool go

public class PIMManager {
    static String dataFilePath = "PIMDatabase.pim";
    static File dataFile;
    static LinkedList<PIMEntity> itemList;

    public PIMManager() {
    }

    private static void saveData() {
        if (dataFile.canWrite()) {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile));

                try {
                    oos.writeObject(itemList);
                    oos.flush();
                } catch (Throwable var4) {
                    try {
                        oos.close();
                    } catch (Throwable var3) {
                        var4.addSuppressed(var3);
                    }

                    throw var4;
                }

                oos.close();
            } catch (FileNotFoundException var5) {
                var5.printStackTrace();
            } catch (IOException var6) {
                var6.printStackTrace();
            }
        }

    }

    private static void loadData() {
        if (dataFile.canRead() && dataFile.length() > 0L) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile));

                try {
                    itemList = (LinkedList)ois.readObject();
                } catch (Throwable var4) {
                    try {
                        ois.close();
                    } catch (Throwable var3) {
                        var4.addSuppressed(var3);
                    }

                    throw var4;
                }

                ois.close();
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        } else {
            loadData();
        }

        System.out.println("Welcome to PIM.");
        Scanner sc = new Scanner(System.in);

        String op = new String();
        label74:
        do {
            System.out.println("---Enter a command (supported commands are list create save load quit)---");
            switch (sc.nextLine()) {
                case "list":
                    System.out.println("There are " + itemList.size() + " items.");
                    int i = 0;

                    while(true) {
                        if (i >= itemList.size()) {
                            continue label74;
                        }

                        System.out.println("Item " + (i + 1) + ": " + itemList.get(i));
                        ++i;
                    }
                case "create":
                    System.out.println("Enter an item type ( todo, note, contact or appointment )");
                    String date;
                    String text;
                    String priority;
                    switch (sc.nextLine()) {
                        case "todo":
                            PIMTodo todo = new PIMTodo();
                            System.out.println("Enter date for todo item:");
                            date = sc.nextLine();
                            todo.setDate(date);
                            System.out.println("Enter todo text:");
                            text = sc.nextLine();
                            todo.setTodoText(text);
                            System.out.println("Enter todo priority:");
                            priority = sc.nextLine();
                            todo.setPriority(priority);
                            itemList.add(todo);
                            continue;
                        case "note":
                            PIMNote note = new PIMNote();
                            System.out.println("Enter note text:");
                            text = sc.nextLine();
                            note.setNoteText(text);
                            System.out.println("Enter note priority:");
                            priority = sc.nextLine();
                            note.setPriority(priority);
                            itemList.add(note);
                            continue;
                        case "appointment":
                            PIMAppointment appointment = new PIMAppointment();
                            System.out.println("Enter date for todo item:");
                            date = sc.nextLine();
                            appointment.setDate(date);
                            System.out.println("Enter appointment description:");
                            text = sc.nextLine();
                            appointment.setDescription(text);
                            System.out.println("Enter appointment priority:");
                            priority = sc.nextLine();
                            appointment.setPriority(priority);
                            itemList.add(appointment);
                            continue;
                        case "contact":
                            PIMContact contact = new PIMContact();
                            System.out.println("Enter firstname for contact item:");
                            String firstName = sc.nextLine();
                            contact.setFirstName(firstName);
                            System.out.println("Enter lastname for contact item:");
                            String lastName = sc.nextLine();
                            contact.setLastName(lastName);
                            System.out.println("Enter email for contact item:");
                            String email = sc.nextLine();
                            contact.setEmail(email);
                            System.out.println("Enter contact priority:");
                            priority = sc.nextLine();
                            contact.setPriority(priority);
                            itemList.add(contact);
                            continue;
                        default:
                            System.out.println("the item type is not exist");
                            continue;
                    }
                case "save":
                    saveData();
                    System.out.println("Items have been saved.");
                    break;
                case "load":
                    loadData();
                    break;
                case "quit":
                    sc.close();
                    break;
                default:
                    System.out.println("the command is not exist");
            }
        } while(!op.equals("quit"));

    }

    static {
        dataFile = new File(dataFilePath);
        itemList = new LinkedList();
    }
}
