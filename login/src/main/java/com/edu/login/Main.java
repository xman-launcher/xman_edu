package com.edu.login;

import com.edu.login.cache.CatchDto;
import com.edu.login.core.HttpClientVerticle;
import com.edu.login.core.HttpServerVerticle;
import io.vertx.core.Vertx;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-1-19
 * Time: 下午1:59
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    private static Logger logger = Logger.getLogger(Main.class);

//    @Override
//    public void start(Future<Void> startFuture) throws Exception {
//        JDBCClient jdbcClient = JDBCClient.createShared(vertx, config(), "My-Whisky-Collection");
//    }

    public static void main(String[] args) {

        Vertx vertx = CatchDto.vertx;
        vertx.deployVerticle(HttpClientVerticle.class.getName(),res->{
            if(res.succeeded()){
                logger.info("client调用启动成功！");
                vertx.deployVerticle(new HttpServerVerticle(8002), res1 -> {
                    if (res1.succeeded()) {
                        logger.info("web服务已启动！！！");
                    } else {
                        logger.info("web服务启动失败：" + res1.cause());
                    }
                });
            }else{
                logger.error("client调用启动失败");
            }
        });

    }

    }
