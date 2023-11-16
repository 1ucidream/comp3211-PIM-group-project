//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * 其实appoint差不多就是题目要求的events
 * 要有alarm, startime, description
 */

public class PIMAppointment extends PIMEntity {

    String description;
    String date;
    LocalDateTime start_time;

    //没想好alarm是什么功能, 暂时设置为date类好了
    //后面可以用str输出反正
    LocalDateTime alarm_time;


    public PIMAppointment() {
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
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
        return "APPOINTMENT " + this.Priority + " " + this.date + " " + this.description;
    }

    @Override
    public void update(Scanner scanner) {

    }

    @Override
    public void containsText(String inputText) {

    }
}
