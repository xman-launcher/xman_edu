package com.edu.dao.cache;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.apache.ibatis.session.SqlSessionFactory;
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
    public static SqlSessionFactory sqlSessionFactory = null;
    static{
        if(null == vertx){
            vertx = Vertx.vertx(new VertxOptions().setEventLoopPoolSize(32).setWorkerPoolSize(40)); //创建vert实例
        }
    }

    public static void setSqlSessionFactory(SqlSessionFactory sqlSession){
        sqlSessionFactory = sqlSession;
    }

}
