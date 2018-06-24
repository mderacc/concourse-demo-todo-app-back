package verticle;

import DBClient.DBClient;
import handlers.DeleteTodoHandler;
import handlers.GetAllTodosHandler;
import handlers.PostTodoHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

import java.util.HashSet;
import java.util.Set;

public class TodoAppVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        final Router router = Router.router(vertx);
        DBClient.connect();
        router.route("/todos*").handler(BodyHandler.create());
        router.route().handler(CorsHandler.create("*")
                .allowedHeaders(getAllowedHeaders())
                .allowedMethods(getAllowedMethods()));

        router.get("/todos/").handler(new GetAllTodosHandler());
        router.delete("/todos/:id").handler(new DeleteTodoHandler());
        router.post("/todos/").handler(new PostTodoHandler());

        vertx.createHttpServer().requestHandler(router::accept).listen(8082);
    }

    private Set<String> getAllowedHeaders() {
        Set<String> allowedHeaders = new HashSet<>();
        allowedHeaders.add("Access-Control-Allow-Origin");
        allowedHeaders.add("origin");
        allowedHeaders.add("Content-Type");
        return allowedHeaders;
    }

    private Set<HttpMethod> getAllowedMethods() {
        Set<HttpMethod> allowedMethods = new HashSet<>();
        allowedMethods.add(HttpMethod.GET);
        allowedMethods.add(HttpMethod.POST);
        allowedMethods.add(HttpMethod.DELETE);
        return allowedMethods;
    }
}
