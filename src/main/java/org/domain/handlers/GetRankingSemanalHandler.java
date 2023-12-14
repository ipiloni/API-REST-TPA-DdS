package org.domain.handlers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.*;
import lombok.Setter;
import org.domain.mappers.RankingMapper;
import org.domain.models.ItemRanking;
import org.domain.models.Ranking;
import org.domain.models.dbo.RankingResponse;
import org.domain.persistence.repositories.RepositorioDeRankings;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetRankingSemanalHandler implements Handler {
    @Setter
    private RankingMapper rankingMapper = new RankingMapper();

    @Override
    @OpenApi(
            summary = "Obtiene el ranking semanal. Si no existe, lo genera con el coeficiente pasado por parametro.",
            operationId = "getRankingSemanal",
            path = "/ranking/semanal",
            methods = HttpMethod.GET,
            tags = {"Ranking"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = RankingResponse.class)}),
                    @OpenApiResponse(status = "500", description = "Hubo un problema en el Servidor.")
            }
    )
    public void handle(@NotNull Context context) {
        Ranking ranking = RepositorioDeRankings.obtenerDeSemana(Ranking.obtenerSemana());

        if(ranking == null){
            double coeficiente = Double.parseDouble(context.pathParam("coeficiente"));
            System.out.println("El Ranking de la semana no existe, generando Ranking con coeficiente " + coeficiente);
            ranking = new Ranking();
            ranking.setSemana(Ranking.obtenerSemana());
            int status = ranking.generarRanking(coeficiente);
            List<ItemRanking> items = RepositorioDeRankings.obtenerItemsDeRanking(ranking.getIdRanking());
            context.status(status).json(rankingMapper.generateResponse(ranking, items));
        } else {
            System.out.println("El Ranking de la semana se ha obtenido correctamente.");
            List<ItemRanking> items = RepositorioDeRankings.obtenerItemsDeRanking(ranking.getIdRanking());
            context.status(200).json(rankingMapper.generateResponse(ranking, items));
        }
    }
}
