//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

/**
 * 这个note类就是备忘录, 以文本内容为主体
 * 新增一个创建的时间戳, 用于搜索
 * 但是如果要修改怎么办呢
 *
 */

public class PIMNote extends PIMEntity {
    String noteText;

    public PIMNote() {
    }

    public String getNoteText() {
        return this.noteText;
    }

    //修改内容就用这个set方法?
    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public void fromString(String ex) {
    }

    public String toString() {
        return "NOTE " + this.Priority + " " + this.noteText;
    }
}
