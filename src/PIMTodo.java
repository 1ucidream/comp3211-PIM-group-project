//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class PIMTodo extends PIMEntity implements SharedDate {
    String date;
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
