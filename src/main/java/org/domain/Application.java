package org.domain;

import io.javalin.Javalin;
import org.domain.config.Config;
import org.domain.handlers.GetRankingByIdHandler;
import org.domain.handlers.GetRankingSemanalHandler;
import org.domain.models.EntidadPropietaria;
import org.domain.models.Incidente;
import org.domain.models.Ranking;
import org.domain.models.ItemRanking;
import org.domain.persistence.BDUtils;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.*;

/*
Este servicio permite calcular el ranking semanal previsto en la entrega anterior con el criterio de mayor
grado de impacto de las problemáticas considerando que las que algunas comunidades tienen mayor
cantidad de miembros y por lo tanto les afecta de mayor medida el no funcionamiento de ese servicio.
Para cada entidad se calcula su nivel de impacto considerando la sumatoria de tiempos de resolución
de incidentes + la cantidad de incidentes no resueltos multiplicado por un coeficiente de incidentes no
resueltos (CNF), expresado en la siguiente ecuación
Σ (𝑡 𝑟𝑒𝑠𝑜𝑙𝑢𝑐𝑖ó𝑛 𝑑𝑒 𝑖𝑛𝑐𝑖𝑑𝑒𝑛𝑡𝑒) + Cantidad de incidentes no resueltos * CNF
Con los valores obtenidos se realiza un ordenamiento de las entidades.
*/

public class Application {
    public static void main(String[] args) {
        Javalin app = Javalin
                .create(Config.getConfigs())
                .routes(() -> {
                    path("api/ranking", () -> {
                        path("semanal/{coeficiente}", () -> {
                            get(new GetRankingSemanalHandler()::handle);
                        });
                        path("{id}", () -> {
                            get(new GetRankingByIdHandler()::handle);
                        });
                    });
                })
                .start(8080);
        System.out.println("Check out Swagger UI docs at http://localhost:8080/swagger");

        app.exception(IllegalArgumentException.class, (e, ctx) -> {
            ctx.status(500);
        });
    }
}
