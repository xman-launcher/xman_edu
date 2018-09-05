package com.edu.login.core;

import com.edu.login.cache.CatchDto;
import com.edu.login.constant.EventBusName;
import e0000.CommonMsg;
import e0000.CommonMsgCovert;
import e0000.OutDto;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.Json;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-8-23
 * Time: 下午2:53
 * To change this template use File | Settings | File Templates.
 */
public class HttpClientVerticle extends AbstractVerticle {
    private Logger logger = Logger.getLogger(HttpClientVerticle.class);
    @Override
    public void start() throws Exception {
        // http客户端配置
        WebClientOptions webClientOptions = new WebClientOptions();
        webClientOptions
//                .setIdleTimeout(5)
                .setKeepAlive(true)
                .setMaxPoolSize(512)
                .setPipelining(true)
                .setTcpNoDelay(true)
                .setUsePooledBuffers(true);
        // 创建http客户端
        WebClient client = WebClient.create(vertx, webClientOptions);
        // 获取事件总线
        EventBus eventBus = vertx.eventBus();
        // 注册解码器
        eventBus.registerDefaultCodec(CommonMsg.class, new CommonMsgCovert());
        eventBus.localConsumer(EventBusName.RESTCLIENT.getDesc(), message -> {
            CommonMsg commonMsg = (CommonMsg)message.body();
            client.postAbs(commonMsg.getUrl())
                  .putHeader(HttpHeaderNames.ACCEPT.toString(), HttpHeaderValues.APPLICATION_JSON.toString())
                  .sendJson(commonMsg,h->{
                      if(h.succeeded()){
                          message.reply(h.result().body());
                      }else{
                          OutDto outDto = new OutDto();
                          outDto.setMsg("等待应答失败：" + h.cause().getMessage());
                          outDto.setResult("1");
                          commonMsg.setDto(outDto);
                          message.reply(commonMsg);
                          logger.error("等待应答失败：", h.cause());
                      }
                  });
        });
    }
}
