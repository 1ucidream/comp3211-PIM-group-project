//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.Serializable;

public abstract class PIMEntity implements Serializable {
    String Priority;

    PIMEntity() {
        this.Priority = "normal";
    }

    PIMEntity(String priority) {
        this.Priority = priority;
    }

    public String getPriority() {
        return this.Priority;
    }

    public void setPriority(String p) {
        this.Priority = p;
    }

    public abstract void fromString(String var1);

    public abstract String toString();
}
