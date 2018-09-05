package com.edu.login.service;

import com.edu.login.cache.CatchDto;
import com.edu.login.constant.EduApiUrl;
import e0000.CommonMsg;
import e0000.CommonMsgCovert;
import e0000.OutDto;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import net.sf.json.JSONObject;


/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-8-24
 * Time: 下午1:41
 * To change this template use File | Settings | File Templates.
 */
public class LoginService {


    private static class LoginServiceInstance{
        private static final LoginService INSTANCE = new LoginService();
    }

    public static LoginService getInstance(){
        return LoginServiceInstance.INSTANCE;
    }

    public void register(RoutingContext routingContext){
        Future<Void> future = Future.future();
        CommonMsg commonMsg = new CommonMsgCovert().decodeFromWire(0, routingContext.getBody());
        commonMsg.setUrl(EduApiUrl.REGISTER_INS.getUrl());
        RestUtl.callRemoteApi((outData) -> registerUsr(outData, routingContext, future), commonMsg, future);
    }

    public void findUsrBuyNickName(RoutingContext routingContext){
        Future<Void> future = Future.future();
        CommonMsg commonMsg = new CommonMsgCovert().decodeFromWire(0, routingContext.getBody());
        commonMsg.setUrl(EduApiUrl.REGISTER_SELECT_NICKNAME.getUrl());
        RestUtl.callRemoteApi((outData) -> findUsrBuyNickName(outData, routingContext, future), commonMsg, future);
    }

    public void goLogin(RoutingContext routingContext){
        Future<Void> future = Future.future();
        CommonMsg commonMsg = new CommonMsgCovert().decodeFromWire(0, routingContext.getBody());
        commonMsg.setUrl(EduApiUrl.REGISTER_SELECT_PSAAWORD.getUrl());
        RestUtl.callRemoteApi((outData) -> goLogin(outData, routingContext, future), commonMsg, future);
    }

    private void registerUsr(AsyncResult<JsonObject> result,RoutingContext routingContext,Future<Void> future){
        if(future.succeeded()){
            routingContext.response().setStatusCode(200)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodePrettily(result.result()));
        }else{
            dealCommonErr(routingContext,future.cause().getMessage());
        }


    }

    private void findUsrBuyNickName(AsyncResult<JsonObject> result,RoutingContext routingContext,Future<Void> future){
        if(future.succeeded()){
            routingContext.response().setStatusCode(200)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodePrettily(result.result()));
        }else{
            dealCommonErr(routingContext,future.cause().getMessage());
        }


    }

    private void goLogin(AsyncResult<JsonObject> result,RoutingContext routingContext,Future<Void> future){
        if(future.succeeded()){
            routingContext.session().put("usrInfo",result.result());
            routingContext.response().setStatusCode(200)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodePrettily(result.result()));
        }else{
            dealCommonErr(routingContext,future.cause().getMessage());
        }
    }

    private void dealCommonErr(RoutingContext routingContext,String errMsg){
        OutDto outDto = new OutDto();
        outDto.setResult("1");
        outDto.setMsg(errMsg);
        JsonObject jsonObject = new JsonObject(JSONObject.fromObject(outDto).toString());
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(jsonObject));
    }


}
