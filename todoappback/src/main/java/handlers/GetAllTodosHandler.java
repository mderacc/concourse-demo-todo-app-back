package handlers;

import DBClient.DBClient;
import io.vertx.core.Handler;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.sql.SQLException;

public class GetAllTodosHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext routingContext) {
        try {
            routingContext.response()
                .putHeader("content-type", "application/json")
                .end(Json.encode(DBClient.getAll()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}