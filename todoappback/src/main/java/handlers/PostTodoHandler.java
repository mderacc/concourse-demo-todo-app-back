package handlers;

import DBClient.DBClient;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class PostTodoHandler implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext routingContext) {
        String title = routingContext.getBodyAsJson().getString("title");
        int priority = Integer.parseInt(routingContext.getBodyAsJson().getString("priority"));
        DBClient.addTask(title, priority);
        routingContext.response().end("Created");
    }
}