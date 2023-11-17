import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * 其实appoint差不多就是题目要求的events
 * 要有alarm, startime, description
 */

public class PIMEvent extends PIMEntity {

    String description;

    String start_time_Str;
    LocalDateTime start_time;

    //没想好alarm是什么功能, 暂时设置为date类好了
    //后面可以用str输出反正
    LocalDateTime alarm_time;
    private String alarm_time_str;

    /**
 * alarm的逻辑:
 * 1.键盘根据给的格式输入开始时间和提醒时间
 * 2.格式转换器转换为date对象
 * 3.验证提醒时间是否合法, 否则抛出异常
 * 4.提醒方式a)命令行打印消息; b)弹窗提醒
 */


    public PIMEvent() {
    }

    public String getDate() {
        return this.start_time_Str;
    }

    public void setStart_time(LocalDateTime start_time){
        this.start_time = start_time;
        setStart_time_Str();
    }

    public void setStart_time(){
        // 定义日期时间格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            // 解析deadline为LocalDateTime对象
            LocalDateTime deadline = LocalDateTime.parse(this.start_time_Str, formatter);

            // 创建事件并进行后续操作
            setStart_time(deadline);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setAlarm_time(LocalDateTime alarm_time){
        this.alarm_time = alarm_time;
    }

    public void setAlarm_time(){
        // 定义日期时间格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            // 解析deadline为LocalDateTime对象
            LocalDateTime deadline = LocalDateTime.parse(this.start_time_Str, formatter);

            // 创建事件并进行后续操作
            setAlarm_time(deadline);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setStart_time_Str() {
        this.start_time_Str = start_time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setStart_time_Str(String s){
        this.start_time_Str = s;

        setDeadline();
    }

    public void setAlarm_time_Str(String s){
        this.alarm_time_str = s;

        setDeadline();
    }


    private void setDeadline() {
    }

    public String getDescription() {
        return this.description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public void fromString(String ex) {
    }

    //start = start_time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    //把event的输出改了
    public String toString() {
        return  "----------------------------------------"+ "\n" +
                "---type: EVENT " + "\n" +
                "---Start Time: " + this.start_time_Str + "\n" +
                "---Description: " + this.description + "\n" +
                "----------------------------------------";
    }

    @Override
    public boolean update(Scanner scanner) {

        /**
         * /加这个while意思是, 若输入了1,2,3以外的命令, 将不会结束当前函数, 而是继续输入命令, 直到出现1,2,3为止
         * 出现了1,2,3,4,5,做完更新后不会跳出while, 会继续询问要更新哪个字段, 直到输入e, 跳出while
         * 跳出while之后判断isupdated是否为真,
         */

        /**
         * isUPdated 意思是当这个示例真的被更新过而不是直接按e结束并退出
         * 如果 = true, 则跳出循环后令PIM.updatecounter++
         * 若 = f, counter不变
         * 防止重复
         */
        boolean isupdated = false;

        pbp:
        while(true){
            //提示用户, 当前记录为event, 输入新的descrip
            //System.out.println("-------------⭐You are updating a Todo");
            System.out.println("-------------Enter : 1-> To update Description");
            System.out.println("                     2-> To update StartTime");
            System.out.println("                     3-> To update AlarmTime");
            System.out.println("                     4-> To update All");
            System.out.println("                     e-> To to end the update");
            //System.out.println("                     b-> back to Main Menu ");
            String input = scanner.nextLine();

            switch ( input ){
                case "1":
                    //输入description
                    System.out.println("-------------Enter new Description:");
                    String description = scanner.nextLine();
                    setDescription(description);

                    //输出反馈
                    System.out.println("-------------⭐Successfully updated⭐" + "\n" + this);
                    isupdated = true;
                    break ;
                case "2":
                    String new_deadline;
                    boolean isFormatValid;

                    do {
                        //提示用户输入deadTime
                        System.out.println("-------------Enter NEW　StartTime( format：yyyy-MM-dd HH:mm:ss ) ：");
                        new_deadline = scanner.nextLine();
                        isFormatValid =  PIMManager.isTimeValid(new_deadline);

                        //如果输入非法, 则继续下一次while, 不跳出
                        if (!isFormatValid) {
                            System.out.println("------------❗Invalid format❗------------");
                        }
                    }while (isFormatValid == false);

                    // 创建事件并进行后续操作
                    setStart_time_Str(new_deadline);

                    //输出反馈
                    System.out.println("-------------⭐Successfully updated⭐" + "\n" + this);

                    isupdated =true;
                    break ;

                case"3"://set alarm time

                    do {
                        //提示用户输入deadTime
                        System.out.println("-------------Enter NEW　AlarmTime( format：yyyy-MM-dd HH:mm:ss ) ：");
                        new_deadline = scanner.nextLine();
                        isFormatValid =  PIMManager.isTimeValid(new_deadline);

                        //如果输入非法, 则继续下一次while, 不跳出
                        if (!isFormatValid) {
                            System.out.println("------------❗Invalid format❗------------");
                        }
                    }while (isFormatValid == false);

                    // 创建事件并进行后续操作
                    setAlarm_time_Str(new_deadline);

                    //输出反馈
                    System.out.println("-------------⭐Successfully updated⭐" + "\n" + this);

                    //update成功的item计数器
                    isupdated =true;
                    break ;

                case "4":
                    //输入description
                    System.out.println("-------------Enter new Description:");
                    description = scanner.nextLine();
                    setDescription(description);

                    do {
                        //提示用户输入deadTime
                        System.out.println("-------------Enter NEW　AlarmTime( format：yyyy-MM-dd HH:mm:ss ) ：");
                        new_deadline = scanner.nextLine();
                        isFormatValid =  PIMManager.isTimeValid(new_deadline);

                        //如果输入非法, 则继续下一次while, 不跳出
                        if (!isFormatValid) {
                            System.out.println("------------❗Invalid format❗------------");
                        }
                    }while (isFormatValid == false);

                    // 创建事件并进行后续操作
                    setAlarm_time_Str(new_deadline);

                    do {
                        //提示用户输入deadTime
                        System.out.println("-------------Enter NEW　StartTime( format：yyyy-MM-dd HH:mm:ss ) ：");
                        new_deadline = scanner.nextLine();
                        isFormatValid =  PIMManager.isTimeValid(new_deadline);

                        //如果输入非法, 则继续下一次while, 不跳出
                        if (!isFormatValid) {
                            System.out.println("------------❗Invalid format❗------------");
                        }
                    }while (isFormatValid == false);

                    // 创建事件并进行后续操作
                    setStart_time_Str(new_deadline);

                    //输出反馈
                    System.out.println("-------------⭐Successfully updated⭐" + "\n" + this);

                    //update成功的item计数器
                    isupdated =true;
                    break ;
                case "e":
                    break pbp;
                default:
                    System.out.println("-------------❗the command is not exist❗");
            }
        }

        /*if(isupdated){
            PIMManager.updateCounter++;
        }*/
        return isupdated;
    }

    @Override
    public void containsText(String inputText) {

    }
}