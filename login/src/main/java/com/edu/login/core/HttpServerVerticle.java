package com.edu.login.core;

import com.edu.login.cache.CatchDto;
import com.edu.login.service.LoginService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.*;
import io.vertx.ext.web.sstore.LocalSessionStore;

import java.util.HashSet;
import java.util.Set;

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
    public void start(Future<Void> fut) throws Exception {
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
        //展示页面
        router.post("/edu/login/register").handler(LoginService.getInstance()::register);
        router.post("/edu/login/findUsrToNick").handler(LoginService.getInstance()::findUsrBuyNickName);
        router.post("/edu/login/goLogin").handler(LoginService.getInstance()::goLogin);

        // 建立连接
        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(port);
    }


}
