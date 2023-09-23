package org.domain;

import io.javalin.Javalin;
import org.domain.config.Config;
import org.domain.handlers.PostReportesHandler;

/*
mayor grado de impacto de las problemáticas considerando que las que algunas comunidades
tienen mayor cantidad de miembros y por lo tanto les afecta de mayor medida el no funcionamiento
de ese servicio. El detalle de la generación de ranking con este criterio será considerado en la
siguiente entrega.
Este servicio permite calcular el ranking semanal previsto en la entrega anterior con el criterio de mayor
grado de impacto de las problemáticas considerando que las que algunas comunidades tienen mayor
cantidad de miembros y por lo tanto les afecta de mayor medida el no funcionamiento de ese servicio.
Para cada entidad se calcula su nivel de impacto considerando la sumatoria de tiempos de resolución
de incidentes + la cantidad de incidentes no resueltos multiplicado por un coeficiente de incidentes no
resueltos (CNF), expresado en la siguiente ecuación
Σ (𝑡 𝑟𝑒𝑠𝑜𝑙𝑢𝑐𝑖ó𝑛 𝑑𝑒 𝑖𝑛𝑐𝑖𝑑𝑒𝑛𝑡𝑒) + Cantidad de incidentes no resueltos * CNF
Con los valores obtenidos se realiza un ordenamiento de las entidades.

SUPONEMOS: LA API RECIBE LAS ENTIDADES, LOS INCIDENTES Y UN COEFICIENTE
*/

public class Application {
    public static void main(String[] args) {
        Javalin app = Javalin
                .create(Config.getConfigs())
                .get("/", ctx -> ctx.result("API REST que realiza el 3er informe"))
                .start(4567);
        System.out.println("Check out Swagger UI docs at http://localhost:4567/swagger");

        app.post("/api/reporte", new PostReportesHandler());

        app.exception(IllegalArgumentException.class, (e, ctx) -> {
            //tratar excepcion
        });
    }
}
