package e0000;

import net.sf.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: wangxun
 * Date: 18-1-29
 * Time: 下午1:35
 * 事件总线默认消息体，接口参数共通消息，适用于远程调用
 */
public class CommonMsg {
    private String url; // 接口路径
    private String functionId; // 接口号
    // 入出参
    private Object dto;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public Object getDto() {
        return dto;
    }

    public void setDto(Object dto) {
        this.dto = dto;
    }

    public JSONObject toJson(){
        if(null == dto) {
            return new JSONObject();
        }
        return JSONObject.fromObject(dto);
    }
}
