import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * 待办事项类
 *
 */

public class PIMTodo extends PIMEntity {

    //格式化的日期放在这个str里
    String deadline_str;

    //日期类方便管理和比较, 输入时可以按格式输入:yy-mm-dd, 然后转换成date类
    LocalDateTime deadline;

    //todo的内容, 查找的时候可以用todotext.contains(目标串)?
    String description;

    public PIMTodo() {
    }

    public String getDate() {
        return this.deadline_str;
    }

    public LocalDateTime getDeadline(){ return this.deadline; }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
        setDeadline_str();
    }

    public void setDeadline(){
        // 定义日期时间格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            // 解析deadline为LocalDateTime对象
            LocalDateTime deadline = LocalDateTime.parse(this.deadline_str, formatter);

            // 创建事件并进行后续操作
            setDeadline(deadline);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 这个方法是由输入的str来设置deadline成员;
     * setDeadline( date )方法是由输入的date对象来设置str成员
     */

    public void setDeadline_str(){
        this.deadline_str = deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setDeadline_str(String s){
        this.deadline_str = s;

        setDeadline();
    }

    public String getDescription() {
        return this.description;
    }

    void setDescription(String toDoText) {
        this.description = toDoText;
    }

    public void fromString(String ex) {
    }

    public String toString() {

        return  "----------------------------------------"+ "\n" +
                "---type: TODO " + "\n" +
                "---Deadline: " + this.deadline_str + "\n" +
                "---Description: " + this.description + "\n" +
                "----------------------------------------";
    }

    /**
     * 对todo的更新, 应该让用户选择要更新deadline or description
     * 输入1-dead, 2-descri, 3-两个都更新switch-case(两个都更新为什么不直接建一个新的...PIMManager.createTodo()...再把原来的指向这个新的)
     * 1-dead: 要判断时间格式及其合法性
     *
     * @return
     */
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
            //提示用户, 当前记录为todo, 输入新的descrip
            //System.out.println("-------------⭐You are updating a Todo");
            System.out.println("-------------Enter : 1-> To update Description");
            System.out.println("                     2-> To update Deadline");
            System.out.println("                     3-> To update All");
            System.out.println("                     e-> To to end the update");
            //System.out.println("                     b-> back to Main Menu ");
            String input = scanner.nextLine();

            switch ( input ){
                case "1":
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
                        System.out.println("-------------Enter Deadline for Todo( format：yyyy-MM-dd HH:mm:ss ) ：");
                        new_deadline = scanner.nextLine();
                        isFormatValid =  PIMManager.isTimeValid(new_deadline);

                        //如果输入非法, 则继续下一次while, 不跳出
                        if (!isFormatValid) {
                            System.out.println("------------❗Invalid format❗------------");
                        }
                    }while (isFormatValid == false);

                    // 创建事件并进行后续操作
                    setDeadline_str(new_deadline);

                    //输出反馈
                    System.out.println("-------------⭐Successfully updated⭐" + "\n" + this);

                    isupdated =true;
                    break ;

                case"3":
                    //重复1,2
                    System.out.println("-------------Enter new Description:");
                    description = scanner.nextLine();
                    setDescription(description);

                    do {
                        //提示用户输入deadTime
                        System.out.println("-------------Enter Deadline for Todo( format：yyyy-MM-dd HH:mm:ss ) ：");
                        new_deadline = scanner.nextLine();
                        isFormatValid =  PIMManager.isTimeValid(new_deadline);

                        //如果输入非法, 则继续下一次while, 不跳出
                        if (!isFormatValid) {
                            System.out.println("------------❗Invalid format❗------------");
                        }
                    }while (isFormatValid == false);

                    // 创建事件并进行后续操作
                    setDeadline_str(new_deadline);

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
