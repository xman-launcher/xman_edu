package com.edu.dao.service;

import com.edu.common.constant.LogLevel;
import com.edu.common.tool.CommonLog;
import com.edu.dao.cache.CatchDto;
import com.edu.dao.dbservice.e1001.E1001ServiceMapper;
import e0000.CommonMsg;
import e0000.CommonMsgCovert;
import e0000.OutDto;
import e1001.*;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-3-14
 * Time: 下午5:05
 * To change this template use File | Settings | File Templates.
 */
public class E1001Service {

    private Logger log = Logger.getLogger(E1001Service.class);

    private SqlSessionFactory sqlSessionFactory = CatchDto.sqlSessionFactory;

    private Vertx vertx = CatchDto.vertx;

    private E1001Service() {}

    private static class E1001ServiceInstance {
        private static final E1001Service INSTANCE = new E1001Service();
    }

    public static E1001Service getInstance() {
        return E1001ServiceInstance.INSTANCE;
    }

    public void insert(RoutingContext routingContext){
        vertx.executeBlocking(fut->{
        CommonMsg commonMsg = new CommonMsgCovert().decodeFromWire(0, routingContext.getBody());

        SqlSession sqlSession = sqlSessionFactory.openSession();
        E1001ServiceMapper mapper = sqlSession.getMapper(E1001ServiceMapper.class);
        E100101InDto dto = (E100101InDto) JSONObject.toBean(commonMsg.toJson(),E100101InDto.class);
        mapper.insert(dto);
        sqlSession.commit();
        sqlSession.close();
        JsonObject jsonObject = new JsonObject().put("result","0");
        routingContext.response().setStatusCode(201)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(jsonObject));
        } ,res->{

                System.out.println(res.result());
        });
    }

    public void selectByUsrName(RoutingContext routingContext){
        vertx.executeBlocking(fut->{
            CommonMsg commonMsg = new CommonMsgCovert().decodeFromWire(0, routingContext.getBody());
            SqlSession sqlSession = sqlSessionFactory.openSession();
            E1001ServiceMapper mapper = sqlSession.getMapper(E1001ServiceMapper.class);
            E100102InDto e100102InDto = (E100102InDto) JSONObject.toBean(commonMsg.toJson(),E100102InDto.class);
            List<E100102Dto> e100102Dtos = mapper.selectByUsrName(e100102InDto);
            E100102OutDto out = new E100102OutDto();
            out.setList(e100102Dtos);
            JsonObject jsonObject = new JsonObject(JSONObject.fromObject(out).toString());
            sqlSession.commit();
            sqlSession.close();
            routingContext.response()
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodePrettily(jsonObject));
        },res->{

            System.out.println(res.result());
        });
    }

    public void selectByUsrNameAndPass(RoutingContext routingContext){
        vertx.executeBlocking(fut->{
            CommonMsg commonMsg = new CommonMsgCovert().decodeFromWire(0, routingContext.getBody());
            try(SqlSession sqlSession = sqlSessionFactory.openSession()){
                E1001ServiceMapper mapper = sqlSession.getMapper(E1001ServiceMapper.class);
                E100103InDto e100103InDto = (E100103InDto) JSONObject.toBean(commonMsg.toJson(),E100103InDto.class);
                List<E100103Dto> e100103Dtos = mapper.selectByUsrNameAndPass(e100103InDto);
                E100103OutDto out = new E100103OutDto();
                out.setResult("0");
                out.setList(e100103Dtos);
                JsonObject jsonObject = new JsonObject(JSONObject.fromObject(out).toString());
                routingContext.response()
                        .putHeader("content-type", "application/json; charset=utf-8")
                        .end(Json.encodePrettily(jsonObject));
                sqlSession.commit();
            } catch (Exception e){
                CommonLog.log(LogLevel.ERR,this.getClass(),e.getMessage());
                dealCommonErr(routingContext,e.getMessage());
                e.printStackTrace();
            }
        },res->{

            System.out.println(res.result());
        });
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
