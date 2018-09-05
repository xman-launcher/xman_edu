package e1001;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-3-7
 * Time: 下午3:26
 * 注册用户信息.
 */
public class E100101InDto {
    private String eduUsrName; //用户真实姓名
    private String eduPhone; //用户电话
    private String eduAddress; //用户地址
    private int eduSex; //用户性别
    private String eduName; //注册昵称
    private String eduPassWord; //密码

    public String getEduUsrName() {
        return eduUsrName;
    }

    public void setEduUsrName(String eduUsrName) {
        this.eduUsrName = eduUsrName;
    }

    public String getEduPhone() {
        return eduPhone;
    }

    public void setEduPhone(String eduPhone) {
        this.eduPhone = eduPhone;
    }

    public String getEduAddress() {
        return eduAddress;
    }

    public void setEduAddress(String eduAddress) {
        this.eduAddress = eduAddress;
    }

    public int getEduSex() {
        return eduSex;
    }

    public void setEduSex(int eduSex) {
        this.eduSex = eduSex;
    }

    public String getEduName() {
        return eduName;
    }

    public void setEduName(String eduName) {
        this.eduName = eduName;
    }

    public String getEduPassWord() {
        return eduPassWord;
    }

    public void setEduPassWord(String eduPassWord) {
        this.eduPassWord = eduPassWord;
    }
}
