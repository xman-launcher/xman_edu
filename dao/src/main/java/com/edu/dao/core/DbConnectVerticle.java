package com.edu.dao.core;

import com.edu.dao.cache.CatchDto;
import io.vertx.core.AbstractVerticle;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-3-14
 * Time: 下午4:10
 * To change this template use File | Settings | File Templates.
 */
public class DbConnectVerticle extends AbstractVerticle{

    private static Logger logger = Logger.getLogger(DbConnectVerticle.class);
    @Override
    public void start() throws Exception {
        try {
            System.out.println("连接数据库！！！");
            String resource = "mybatis-config.xml";
            InputStream inputStream = null;
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            CatchDto.setSqlSessionFactory(sqlSessionFactory);

        }catch (Exception e) {
            logger.info(Thread.currentThread().getName()+"---错误信息:"+e.getMessage());
            e.printStackTrace();
        }
    }
}
