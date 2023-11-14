import java.time.LocalDateTime;

/**
 * 待办事项类
 *
 */

public class PIMTodo extends PIMEntity implements SharedDate {

    //格式化的日期放在这个str里
    String date;

    //日期类方便管理和比较, 输入时可以按格式输入:yy-mm-dd, 然后转换成date类
    LocalDateTime dateTime;

    //todo的内容, 查找的时候可以用todotext.contains(目标串)?
    String toDoText;

    public PIMTodo() {
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTodoText() {
        return this.toDoText;
    }

    void setTodoText(String toDoText) {
        this.toDoText = toDoText;
    }

    public void fromString(String ex) {
    }

    public String toString() {
        return "TODO " + this.Priority + " " + this.date + " " + this.toDoText;
    }
}
