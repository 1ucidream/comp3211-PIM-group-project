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
/**
 * 感觉switch部分太庞大了应该改成方法调用比较好
 * 需不需要为每个子类都加一个专门的list?
 *
 */

public class PIMManager {

    //仅仅把dat改成了pim后缀
    static String dataFilePath = "PIMDatabase.pim";
    static File dataFile;
    static LinkedList<PIMEntity> itemList;

    static Scanner sc = new Scanner(System.in);

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

    static void createData(){

        System.out.println("Enter an item type ( todo, note, contact or appointment )");
        String date;
        String text;
        String priority;
        switch (sc.nextLine()) {
            case "todo":
                /* */
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
                break;

            case "note":
                PIMNote note = new PIMNote();
                System.out.println("Enter note text:");
                text = sc.nextLine();
                note.setNoteText(text);
                System.out.println("Enter note priority:");
                priority = sc.nextLine();
                note.setPriority(priority);
                itemList.add(note);
                break;

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
                break;

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
                break;

            default:
                System.out.println("the item type is not exist");
                break;

        }
    }

    public static void main(String[] args) throws IOException {
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        } else {
            loadData();
        }

        //感觉这个开始语句有点不显眼, 看能不能整点花样, 加点那种程序员图什么的
        System.out.println("----------------------------------------");
        System.out.println("*          Welcome to PIM:)            *");
        System.out.println("----------------------------------------");

        //Scanner sc = new Scanner(System.in);
        //String op = new String();//这个op好像没啥用啊

        label74:
        do {
            System.out.println("----------------------------------------");
            System.out.println("-          Enter a command             -");
            System.out.println("----------------------------------------");

            System.out.println(
                    "-            1.list                    -" +"\n"+
                    "-            2.create                  -" +"\n"+
                    "-            3.save                    -" +"\n"+
                    "-            4.exit                    -" +"\n"+
                    "----------------------------------------");

            switch (sc.nextLine()) {
                case "list":
                    System.out.println("There are " + itemList.size() + " items.");
                    int i = 0;

                    while(true) {
                        if (i >= itemList.size()) {
                            continue label74;//这个label不能删, 因为删了之后是继续了这个while(true)
                        }

                        System.out.println("Item " + (i + 1) + ": " + itemList.get(i));
                        ++i;
                    }
                case "create":
                    createData();
                    continue ;
                    /*
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
                            continue;//这个continue相当于continue label74;

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
                    }*/
                case "save":
                    saveData();
                    System.out.println("Items have been saved.");
                    break;
                /*case "load":
                    loadData();
                    break;*/

                //这里把 break 改成 break label74, 就不会跳到 108行 的输入命令
                case "exit":
                    sc.close();
                    break label74;
                default:
                    System.out.println("the command is not exist");
            }
        } while(true);//感觉这个条件直接改成while( 1 )死循环也可以啊

    }

    static {
        dataFile = new File(dataFilePath);
        itemList = new LinkedList();
    }
}
