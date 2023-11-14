
/**
 * cri类写成一个接口, 让textCri和dateCri去实现
 */
public interface Criterion {


    public boolean matches(PIMEntity targetItem);
}
