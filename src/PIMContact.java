//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Scanner;

public class PIMContact extends PIMEntity {
    String firstName;
    String lastName;
    //String email;

    String address;

    String phoneNum;

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

    public  void setAddress(String address) {
        this.address = address;
    }
    /*public String getEmail() {
        return this.email;
    }

    void setEmail(String email) {
        this.email = email;
    }
*/
    public void setPhoneNum(String s){
        this.phoneNum = s;
    }
    public void fromString(String ex) {
    }

    /*
    public String toString() {
        return "type: CONTACT " + "\n" +
                "Prio" + this.Priority + " " + this.firstName + " " + this.lastName + " " + this.address;
    }
*/
    //把tostring改了, 每条记录的每个信息点换行输出, contact应该不需要priority吧
    public String toString() {
        return  "----------------------------------------"+ "\n" +
                "---type: CONTACT " + "\n" +
                "---Name: " + this.firstName + " " + this.lastName +"\n" +
                "---Address: " + this.address + "\n" +
                "---PhoneNums:"+ this.phoneNum + "\n" +
                "----------------------------------------";
    }

    /**
     * update之前要打印一下自己, 让用户看见, 再问要更新哪个字段
     * 更新完再打印一次
     */
    @Override
    public void update(Scanner scanner) {

    }

    @Override
    public void containsText(String inputText) {

    }

}
