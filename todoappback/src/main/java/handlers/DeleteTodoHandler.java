package handlers;

import DBClient.DBClient;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class DeleteTodoHandler implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext routingContext) {
        DBClient.deleteTask(routingContext.request().params().get("id"));
        routingContext.response().end("Deleted");
    }
}