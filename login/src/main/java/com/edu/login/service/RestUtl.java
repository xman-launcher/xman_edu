package com.edu.login.service;

import com.edu.login.cache.CatchDto;
import com.edu.login.constant.EventBusName;
import e0000.CommonMsg;
import e0000.CommonMsgCovert;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;


/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-8-24
 * Time: 下午2:05
 * To change this template use File | Settings | File Templates.
 */
public class RestUtl {


    public static void  callRemoteApi(Handler<AsyncResult<JsonObject>> result, CommonMsg msg,Future<Void> fuc) {
        CatchDto.vertx.eventBus().send(EventBusName.RESTCLIENT.getDesc(),msg,h->{
           if(!h.succeeded()){
               fuc.fail(h.cause());
           }else{
               Message message = h.result();
               fuc.complete();
               result.handle(Future.succeededFuture(new JsonObject(message.body().toString())));
           }
        });
    }
}
