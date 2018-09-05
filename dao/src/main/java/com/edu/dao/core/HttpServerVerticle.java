package com.edu.dao.core;

import com.edu.dao.cache.CatchDto;
import com.edu.dao.service.E1001Service;
import com.edu.dao.service.PassService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.*;
import io.vertx.ext.web.sstore.LocalSessionStore;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-3-14
 * Time: 下午5:17
 * To change this template use File | Settings | File Templates.
 */
public class HttpServerVerticle extends AbstractVerticle {

    // 端口号
    private int port;

    Vertx vertx = CatchDto.vertx;

    public HttpServerVerticle(int port) {
        this.port = port;
    }

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);

        // Bind "/" to our hello message.
        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response
                    .putHeader("content-type", "text/html")
                    .end("<h1>Hello from my first Vert.x 3 application</h1>");
        });

        //处理静态资源
        router.route("/edu/public/resources/*").handler(CorsHandler.create("*").allowedMethod(HttpMethod.GET)); //支持跨域
        router.route("/edu/public/resources/*").handler(StaticHandler.create().setAllowRootFileSystemAccess(true).setWebRoot("web"));

        //cookie处理
        router.route().handler(CookieHandler.create());
        //session处理
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx))
                .setSessionCookieName("edu.xun"));

        //支持form请求
        router.route().handler(BodyHandler.create().setMergeFormAttributes(false));
        router.route().handler(CorsHandler.create("*").allowedMethod(HttpMethod.OPTIONS).allowedHeader("Access-Control-Allow-Origin:*").allowedHeader("Content-Type"));
        router.post("/api/edu/e100101").handler(E1001Service.getInstance()::insert);
        router.post("/api/edu/e100102").handler(E1001Service.getInstance()::selectByUsrName);
        router.post("/api/edu/e100103").handler(E1001Service.getInstance()::selectByUsrNameAndPass);
//        router.post("/api/edu/pass").handler(PassService.getInstance()::getPass);
//        router.post("/api/edu/endPass").handler(PassService.getInstance()::endPass);



        // 建立连接
        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(port);
    }


}
