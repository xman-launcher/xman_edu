package e0000;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-3-7
 * Time: 下午3:49
 * 接口出参统一类型
 */
public class OutDto implements Serializable{
    private static final long serialVersionUID = 1L;

    private String result; //结果状态
    private String msg; //错误信息
    private String serial_no; //流水号
    private boolean success; //是否调用成功

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
