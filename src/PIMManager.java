import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

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

    public PIMManager() {}

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

        System.out.println("--------⭐Successfully saved⭐---------");
        System.out.println("----------⭐Total " + itemList.size() + " records⭐-----------");
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

        System.out.println("--------⭐Successfully loaded⭐--------");
    }

    static void createTodo(){
        //todo 的 date意思是deadline
        String date;
        String text;

        PIMTodo todo = new PIMTodo();

        // 定义日期时间格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 正则表达式模式
        String pattern = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
        boolean isFormatValid;

        do {
            //提示用户输入startTime
            System.out.println("-------------Enter Deadline for todo( format：yyyy-MM-dd HH:mm:ss ) ：");
            date = sc.nextLine();
            isFormatValid = Pattern.matches(pattern, date);

            if (!isFormatValid) {
                System.out.println("------------❗Invalid format❗------------");
            } else {
                isFormatValid = true;
                //break label;  在日期格式正确时跳出循环
            }
        }while (isFormatValid == false);

        try {
            // 解析deadline为LocalDateTime对象
            LocalDateTime deadline = LocalDateTime.parse(date, formatter);

            // 创建事件并进行后续操作
            todo.setDeadline(deadline);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //提示输入描述, 将描述添加到todo对象中
        System.out.println("-------------Enter todo description:");
        text = sc.nextLine();
        todo.setDescription(text);

        //添加到list
        itemList.add(todo);

        //添加完之后输出反馈
        System.out.println("--------⭐Successfully added⭐---------"+ "\n" + todo);
    }

    static void createEvent(){
        String date;
        String alarmTimeInput;
        String text;
        PIMEvent event = new PIMEvent();

        // 正则表达式模式
        String pattern = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
        boolean isFormatValid;

        //正则验证start time格式
        do {
            //提示用户输入startTime
            System.out.println("-------------Enter Start time for event( format：yyyy-MM-dd HH:mm:ss ) ：");
            date = sc.nextLine();
            isFormatValid = Pattern.matches(pattern, date);

            if (!isFormatValid) {
                System.out.println("Invalid format!");
                continue;
            } else {
                isFormatValid = true;
                //break label;  在日期格式正确时跳出循环
            }

        }while (isFormatValid == false);

        //正则验证alarm time格式, 以及是否早于start time
        do {
            // 提示用户输入alarmTime
            System.out.println("-------------Enter Alarm time for event( format：yyyy-MM-dd HH:mm:ss ) ：");
            alarmTimeInput = sc.nextLine();
            isFormatValid = Pattern.matches(pattern, alarmTimeInput);
            if (!isFormatValid) {
                System.out.println("------------❗Invalid format❗------------");
                continue ;
            } else {
                isFormatValid = true;
                // 在日期格式正确时跳出循环
            }
            // 定义日期时间格式化器
            DateTimeFormatter formatter2 =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            try {
                // 解析startTime和alarmTime为LocalDateTime对象
                LocalDateTime startTime = LocalDateTime.parse(date, formatter2);
                LocalDateTime alarmTime = LocalDateTime.parse(alarmTimeInput, formatter2);

                // 验证alarmTime是否晚于startTime
                if (!alarmTime.isBefore(startTime)) {
                    throw new IllegalArgumentException(
                            "-------Invalid alarmTime: --------------" + "\n" +
                            "Reminders must be no later than " + date +
                            "----------------------------------------"
                    );
                }
                // 创建事件并进行后续操作
                event.setStart_time(startTime);
                event.setAlarm_time(alarmTime);

            } catch (Exception e) {
                //其实这个exception就包括了任何exception
                System.out.println(e.getMessage());

                //如果提醒时间晚于开始时间, 要回到do处
                isFormatValid = false;
            }
        }while (isFormatValid == false);

        //输入event描述
        System.out.println("-------------Enter event description:");
        text = sc.nextLine();
        event.setDescription(text);

        //添加到list
        itemList.add(event);

        //输出反馈
        System.out.println("--------⭐Successfully added⭐---------"+ "\n" + event);
    }

    static void createNote(){
        String text;

        //输入note内容
        PIMNote note = new PIMNote();
        System.out.println("-------------Enter note text:");
        text = sc.nextLine();
        note.setNoteText(text);

        //添加到list
        itemList.add(note);

        //输出反馈
        System.out.println("--------⭐Successfully added⭐---------" + "\n" + note);

    }

    static void createContact(){
        PIMContact contact = new PIMContact();
        //输入firstname
        System.out.println("-------------Enter firstname :");
        String firstName = sc.nextLine();
        contact.setFirstName(firstName);

        //输入lastname
        System.out.println("-------------Enter lastname :");
        String lastName = sc.nextLine();
        contact.setLastName(lastName);

        //输入地址
        System.out.println("-------------Enter address :");
        String address = sc.nextLine();
        contact.setAddress(address);

        //输入numbers
        System.out.println("-------------Enter mobile numbers :");
        String m = sc.nextLine();
        contact.setPhoneNum(m);

        //把contact添加到itemlist
        itemList.add(contact);

        //输出反馈
        System.out.println("--------⭐Successfully added⭐---------"+ "\n" + contact);
    }

    static void createData(){
        //打印create菜单
        printCreateMenu();

        switch (sc.nextLine()) {
            case "t":
                createTodo();
                break;

            case "n":
                createNote();
                break;

            case "e"://把appoint改成event
                createEvent();
                break;

            case "c":
                createContact();
                break;

            default:
                System.out.println("!!!!!!!!! the item type is not exist !!!!!!!!");
                break;
        }
    }

    static void printList(){
        System.out.println("There are " + itemList.size() + " items.");
        int i = 0;

        while(true) { //这一段打包成一个printList()
            if (i >= itemList.size()) {
                break;//跳出while循环, 也就是该printlist()执行完毕, 继续回到enter a command
            }
            System.out.println(
                    "--------------⭐Item " + (i + 1) + "⭐---------------" + "\n"+
                            itemList.get(i));
            ++i;
        }
    }

    /**
     * 打印主菜单
     */
    static void printMainMenu(){
        System.out.println("----------------------------------------");
        System.out.println("-          Enter a command             -");
        System.out.println("----------------------------------------");
        System.out.println(
                        "-            l -> list                 -" +"\n"+
                        "-            c -> create               -" +"\n"+
                        "-            s -> save                 -" +"\n"+
                        "-            e -> exit                 -" +"\n"+
                        "----------------------------------------");
    }

    /**
     * 打印create各种事项的菜单
     */
    static void printCreateMenu(){
        System.out.println("----------------------------------------");
        System.out.println("-      Enter an item type(number)      -");
        System.out.println("----------------------------------------");
        System.out.println(
                "-            t -> todo                 -" +"\n"+
                        "-            n -> note                 -" +"\n"+
                        "-            c -> contact              -" +"\n"+
                        "-            e -> event                -" +"\n"+
                        "----------------------------------------");
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

        label74:
        do {
            //打印这个主菜单打包成一个printMainMenu()
            printMainMenu();
            switch (sc.nextLine()) {
                case "l":
                    printList();
                    continue ;
                case "c":
                    createData();
                    continue ;
                case "s":
                    saveData();
                    continue ;
                //这里把 break 改成 break label74, 就不会跳到 108行 的输入命令
                case "e":
                    sc.close();
                    break label74;
                default:
                    System.out.println("------❗the command is not exist❗--------");
            }
        } while(true);//感觉这个条件直接改成while( 1 )死循环也可以啊

    }

    /**
     * 一个静态代码块，它在类加载时执行，并且只会执行一次。静态代码块的主要目的是在类加载时进行一些初始化操作。
     *
     * 根据代码片段提供的信息，可以推断出以下几点：
     *
     * dataFilePath 是一个字符串变量，表示数据文件的路径。
     * dataFile 是一个 File 对象，用于表示数据文件。
     * itemList 是一个 LinkedList 对象，用于存储数据项。
     */
    static {
        dataFile = new File(dataFilePath);
        itemList = new LinkedList();
    }

    //hello
}
