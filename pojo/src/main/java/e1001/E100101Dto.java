package e1001;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-3-7
 * Time: 下午3:26
 * 注册用户信息.
 */
public class E100101Dto {
    private String seiNo; //流水号
    private String state; //是否注册成功

    public String getSeiNo() {
        return seiNo;
    }

    public void setSeiNo(String seiNo) {
        this.seiNo = seiNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
