//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class PIMAppointment extends PIMEntity {
    String date;
    String description;

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
}
