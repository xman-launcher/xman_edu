package e0000;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.EncodeException;
import io.vertx.core.json.Json;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: wangxun
 * Date: 18-1-30
 * Time: 上午9:05
 * To change this template use File | Settings | File Templates.
 */
public class CommonMsgCovert implements MessageCodec<CommonMsg,CommonMsg> {
    private Logger log = Logger.getLogger(CommonMsgCovert.class);

    /**
     * 将消息体编成二进制流
     * @param buffer
     * @param commonMsg
     */
    @Override
    public void encodeToWire(Buffer buffer, CommonMsg commonMsg) {
        try {
            buffer = Json.encodeToBuffer(commonMsg);
        } catch (EncodeException e) {
            log.error("convert commonMsg error", e);
        }
    }

    /**
     * 将消息体编成二进制流
     * @param buffer
     * @param commonMsg
     */
    public Buffer encodeToWirte(Buffer buffer, CommonMsg commonMsg) {
        try {
            buffer = Json.encodeToBuffer(commonMsg);
        } catch (EncodeException e) {
            log.error("convert commonMsg error", e);
        }
        return buffer;
    }

    /**
     * 二进制转消息体
     * @param pos
     * @param buffer
     * @return
     */
    @Override
    public CommonMsg decodeFromWire(int pos, Buffer buffer) {
        CommonMsg commonMsg = null;
        try{
            commonMsg = Json.decodeValue(buffer, CommonMsg.class);
        }catch (DecodeException e){
            log.error("commonMsg httpMsg error", e);
        }
        return commonMsg;
    }

    @Override
    public CommonMsg transform(CommonMsg commonMsg) {
        return commonMsg;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String name() {
        return "CommonMsg";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public byte systemCodecID() {
        return -1;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
