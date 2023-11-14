//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class PIMContact extends PIMEntity {
    String firstName;
    String lastName;
    String email;

    public PIMContact() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    public void fromString(String ex) {
    }

    public String toString() {
        return "CONTACT " + this.Priority + " " + this.firstName + " " + this.lastName + " " + this.email;
    }
}
