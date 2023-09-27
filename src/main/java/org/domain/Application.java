package org.domain;

import io.javalin.Javalin;
import org.domain.config.Config;
import org.domain.handlers.PostReportesHandler;

public class Application {
    public static void main(String[] args) {
        Javalin app = Javalin
                .create(Config.getConfigs())
                .post("/api/reporte", new PostReportesHandler())
                .start(8080);
        System.out.println("Check out Swagger UI docs at http://localhost:8080/swagger");

        app.exception(IllegalArgumentException.class, (e, ctx) -> {
            //tratar excepcion
        });
    }
}
