package com.edu.login.cache;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-2-1
 * Time: 下午1:35
 * To change this template use File | Settings | File Templates.
 */
public class CatchDto {
    private static Logger logger = Logger.getLogger(CatchDto.class);
    public static Vertx vertx = null; //Vertx实例
    public static final String API_PATH =  "http://127.0.0.1:8080";
    static{
        if(null == vertx){
            vertx = Vertx.vertx(new VertxOptions().setEventLoopPoolSize(32).setWorkerPoolSize(40)); //创建vert实例
        }
    }


}
