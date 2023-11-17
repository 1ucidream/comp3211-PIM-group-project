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
     *
     * @return
     */
    @Override
    public boolean update(Scanner sc) {

        /**
         * /加这个while意思是, 若输入了1,2,3以外的命令, 将不会结束当前函数, 而是继续输入命令, 直到出现1,2,3为止
         * 出现了1,2,3,4,5,做完更新后不会跳出while, 会继续询问要更新哪个字段, 直到输入e, 跳出while
         * 跳出while之后判断isupdated是否为真,
         */

        /**
         * isUPdated 意思是当这个示例真的被更新过而不是直接按e结束并退出
         * 如果 = true, 则跳出循环后令PIM.updatecounter++
         * 若 = f, counter不变
         * 防止重复
         */
        boolean isupdated = false;

        pbp:
        while(true){

            System.out.println("-------------Enter : 1-> To update Firstname");
            System.out.println("                     2-> To update Lastname");
            System.out.println("                     3-> To update Address");
            System.out.println("                     4-> To update Mobile Number");
            System.out.println("                     5-> To update All");
            System.out.println("                     e-> To to end the update");
            //System.out.println("                     b-> back to Main Menu ");
            String input = sc.nextLine();




            switch ( input ){
                case "1":
                    //输入firstname
                    System.out.println("-------------Enter NEW firstname :");
                    String firstName = sc.nextLine();
                    setFirstName(firstName);
                    //输出反馈
                    System.out.println("-------------⭐Successfully updated⭐" + "\n" + this);

                    isupdated =true;
                    break ;
                    //break pbp;

                case "2":
                    //输入lastname
                    System.out.println("-------------Enter NEW lastname :");
                    String lastName = sc.nextLine();
                    setLastName(lastName);

                    //输出反馈
                    System.out.println("-------------⭐Successfully updated⭐" + "\n" + this);
                    isupdated =true;
                   // break pbp;
                    break ;

                case"3":
                    //输入地址
                    System.out.println("-------------Enter NEW address :");
                    String address = sc.nextLine();
                    setAddress(address);

                    //输出反馈
                    System.out.println("-------------⭐Successfully updated⭐" + "\n" + this);

                    //update成功的item计数器
                    isupdated =true;
                    //break pbp;
                    break ;

                case "4":
                    //输入numbers
                    System.out.println("-------------Enter NEW mobile numbers :");
                    String m = sc.nextLine();
                    setPhoneNum(m);

                    //输出反馈
                    System.out.println("-------------⭐Successfully updated⭐" + "\n" + this);

                    //update成功的item计数器
                    isupdated =true;
                    //break pbp;
                    break ;

                case "5":
                    //输入firstname
                    System.out.println("-------------Enter NEW firstname :");
                    firstName = sc.nextLine();
                    setFirstName(firstName);

                    //输入lastname
                    System.out.println("-------------Enter NEW lastname :");
                    lastName = sc.nextLine();
                    setLastName(lastName);

                    //输入地址
                    System.out.println("-------------Enter NEW address :");
                    address = sc.nextLine();
                    setAddress(address);

                    //输入numbers
                    System.out.println("-------------Enter NEW mobile numbers :");
                    m = sc.nextLine();
                    setPhoneNum(m);
                    isupdated = true;

                    break ;

                case "e":
                    break pbp;

                default:
                    System.out.println("-------------❗the command is not exist❗");
            }


        }

        /*if(isupdated){
            PIMManager.updateCounter++;
        }*/

        return isupdated;
    }

    @Override
    public void containsText(String inputText) {

    }

}
