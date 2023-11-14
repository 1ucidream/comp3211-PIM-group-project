import java.time.LocalDateTime;

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


    public PIMEvent() {
    }

    public String getDate() {
        return this.start_time_Str;
    }

    public void setDate(String date) {
        this.start_time_Str = date;
    }

    public String getDescription() {
        return this.description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public void fromString(String ex) {
    }

    public String toString() {
        return "Event " + this.Priority + " " + this.start_time_Str + " " + this.description;
    }
}