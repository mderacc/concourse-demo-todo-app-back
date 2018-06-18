package verticle;

import DBClient.DBClient;
import handlers.DeleteTodoHandler;
import handlers.GetAllTodosHandler;
import handlers.PostTodoHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class TodoAppVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        final Router router = Router.router(vertx);
        DBClient.connect();
        router.route("/todos*").handler(BodyHandler.create());
        router.get("/todos/").handler(new GetAllTodosHandler());
        router.delete("/todos/:id").handler(new DeleteTodoHandler());
        router.post("/todos/").handler(new PostTodoHandler());

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }
}
