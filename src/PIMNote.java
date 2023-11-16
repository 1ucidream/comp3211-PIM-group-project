//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.awt.SystemColor.text;

/**
 * 这个note类就是备忘录, 以文本内容为主体new plain texts as PIRs
 * 新增一个establish的时间戳, 用于搜索
 * 但是如果要修改怎么办呢
 * 再加一个update时间戳, 把toString()的输出加上这一项
 */

/**
 * 要不要把str到date类的转换放进note类里, 而不是放在createNote方法
 *
 */
public class PIMNote extends PIMEntity {
    String noteText;

    String establishedTime_str;

    String updatedTime_str;

    LocalDateTime establishedTime;

    LocalDateTime updatedTime;

    public PIMNote() {
        setEstablishedTimeStamp();
    }

    @Override
    public void fromString(String var1) {

    }

    public void setEstablishedTimeStamp(){

        //把note实例化的时候就设置当前时间为establishTime
        establishedTime = LocalDateTime.now();

        //首次update时间置为establishTime
        setUpdatedTime(establishedTime);

        //顺便给str设置一下
        establishedTime_str = establishedTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setUpdatedTime( LocalDateTime localDateTime ){

        //更新的时候将updateTime置为参数对象
        updatedTime = localDateTime;
        updatedTime_str = updatedTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getNoteText() {
        return this.noteText;
    }

    //修改内容就用这个set方法?
    //没错!
    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String toString() {
        return "----------------------------------------"+ "\n" +
               "---type: NOTE " + "\n" +
               "---Established Time: " + this.establishedTime_str + "\n" +
               "---Updated on: " + this.updatedTime_str + "\n" +
               "---Text: " + this.noteText + "\n" +
               "----------------------------------------";
    }

    @Override
    public void update(Scanner scanner) {

        int updateCount = 0;
        //传进来的scanner是可以用的!

        //提示用户, 当前记录为note, 输入新的text
        System.out.println("-------------Enter new text:");
        String input = scanner.nextLine();
        setNoteText(input);

        setUpdatedTime(LocalDateTime.now());

        //输出反馈
        System.out.println("-------------⭐Successfully updated⭐" + "\n" + this);
    }

    @Override
    public void containsText(String inputText) {

    }
}
