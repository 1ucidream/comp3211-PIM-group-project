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

    public void setAlarm_time(LocalDateTime alarm_time){
        this.alarm_time = alarm_time;
    }

    public void setStart_time_Str() {
        this.start_time_Str = start_time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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
    public void update(Scanner scanner) {

    }

    @Override
    public void containsText(String inputText) {

    }


}