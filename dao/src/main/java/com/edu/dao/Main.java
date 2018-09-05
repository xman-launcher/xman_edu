package com.edu.dao;

import com.edu.dao.cache.CatchDto;
import com.edu.dao.core.DbConnectVerticle;
import com.edu.dao.core.HttpServerVerticle;
import com.edu.dao.dbservice.e1001.E1001ServiceMapper;
import e1001.E100101InDto;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.impl.FileResolver;
import io.vertx.ext.jdbc.JDBCClient;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-3-7
 * Time: 下午4:58
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    private static Logger logger = Logger.getLogger(Main.class);

//    @Override
//    public void start(Future<Void> startFuture) throws Exception {
//        JDBCClient jdbcClient = JDBCClient.createShared(vertx, config(), "My-Whisky-Collection");
//    }

    public static void main(String[] args){

        Vertx vertx = CatchDto.vertx;
        vertx.deployVerticle(new DbConnectVerticle(), res -> {
            if (res.succeeded()) {
                logger.info("数据库服务已启动！！！");
                vertx.deployVerticle(new HttpServerVerticle(8080), res1 -> {
                    if (res1.succeeded()) {
                        logger.info("web服务已启动！！！");
                    } else {
                        logger.info("web服务启动失败：" + res1.cause());
                    }
                });

            } else {
                logger.info("数据库服务启动失败：" + res.cause());
            }
        });






    }

}
