package com.edu.dao.service;

import com.edu.common.util.RSAUtils;
import com.edu.dao.cache.CatchDto;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-3-14
 * Time: 下午5:05
 * To change this template use File | Settings | File Templates.
 */
public class PassService {

    private Logger log = Logger.getLogger(PassService.class);


    private Vertx vertx = CatchDto.vertx;

    private PassService() {}

    private static class E1001ServiceInstance {
        private static final PassService INSTANCE = new PassService();
    }

    public static PassService getInstance() {
        return E1001ServiceInstance.INSTANCE;
    }

    public void getPass(RoutingContext routingContext){
        try {

//            RSAPublicKey defaultPublicKey = RSAUtils.getDefaultPublicKey();
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.put("publicKeyExponent",new String(Hex.encodeHex(defaultPublicKey.getPublicExponent().toByteArray())));
//            jsonObject.put("publicKeyModulus", new String(Hex.encodeHex(defaultPublicKey.getModulus().toByteArray())));
            HashMap<String, Object> map = RSAUtils.getKeys();
            //生成公钥和私钥
            RSAPublicKey publicKey = (RSAPublicKey) map.get("public");
            RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");
            JsonObject jsonObject = new JsonObject();
            jsonObject.put("publicKeyExponent",publicKey.getModulus().toString(16));
            jsonObject.put("publicKeyModulus", publicKey.getPublicExponent().toString(16));
            routingContext.session().put("privateKey",privateKey);
            HttpServerResponse response = routingContext.response();
            response.setChunked(true);
            response.write(jsonObject.toBuffer());
            routingContext.response().end();
        }catch (Exception e) {
            log.info(e.getMessage());
        }
    }


    public void endPass(RoutingContext routingContext){
        String pass = (String)routingContext.getBodyAsJson().getMap().get("pass");
        RSAPrivateKey privateKey = routingContext.session().get("privateKey");
        try {
            String s = RSAUtils.decryptByPrivateKey(pass,privateKey);
            HttpServerResponse response = routingContext.response();
            JsonObject jsonObject = new JsonObject();
            jsonObject.put("res",s);
            response.setChunked(true);
            response.write(jsonObject.toBuffer());
            routingContext.response().end();
        }catch (Exception e) {
            log.info(e.getMessage());
        }
    }
//        bodyAsJson.get


}
