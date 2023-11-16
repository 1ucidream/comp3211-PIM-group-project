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

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
        setDeadline_str();
    }

    public void setDeadline_str(){
        this.deadline_str = deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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
     */
    @Override
    public void update(Scanner scanner) {

    }

    @Override
    public void containsText(String inputText) {

    }
}
