package handlers;

import DBClient.DBClient;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class PostTodoHandler implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext routingContext) {
        String title = routingContext.getBodyAsJson().getString("title");
        int priority = routingContext.getBodyAsJson().getInteger("priority");
        DBClient.addTask(title, priority);
        routingContext.response().end("Created");
    }
}