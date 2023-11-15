


import java.io.Serializable;

/**
 * entity类除了设置优先级以外啥都没有!
 * 废物, 为了避免pim文件不兼容, 把每个子类的pri都初始化为normal好了
 * 不如就把全部可能会有的属性全都加在这个entity顶层里算了
 * 因为判断text是否包含在PIR的contains方法需要在entity这里完成而不是在criterion里
 * 但也可以在每个子类里重写containsText()这样应该也可以8
 */

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
