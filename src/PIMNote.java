//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 这个note类就是备忘录, 以文本内容为主体new plain texts as PIRs
 * 新增一个创建的时间戳, 用于搜索
 * 但是如果要修改怎么办呢
 */

/**
 * 要不要把str到date类的转换放进note类里, 而不是放在createNote方法
 *
 */
public class PIMNote extends PIMEntity {
    String noteText;

    String timeStamp_str;

    LocalDateTime timeStamp;

    public PIMNote() {
        setTimeStamp();
    }

    @Override
    public void fromString(String var1) {

    }

    public void setTimeStamp(){
        timeStamp = LocalDateTime.now();
        timeStamp_str = timeStamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    public String getNoteText() {
        return this.noteText;
    }

    //修改内容就用这个set方法?
    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String toString() {
        return "----------------------------------------"+ "\n" +
               "---type: NOTE " + "\n" +
               "---Established Time: " + this.timeStamp_str + "\n" +
               "---Text: " + this.noteText + "\n" +
               "----------------------------------------";
    }
}
