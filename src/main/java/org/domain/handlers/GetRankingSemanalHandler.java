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
            summary = "Get weekly ranking",
            operationId = "getRankingSemanal",
            path = "/ranking/semanal",
            methods = HttpMethod.GET,
            tags = {"Ranking"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = RankingResponse.class)}),
                    @OpenApiResponse(status = "404", description = "Ranking not found")
            }
    )
    public void handle(@NotNull Context context) throws Exception {
        Ranking ranking = RepositorioDeRankings.obtenerDeSemana(Ranking.obtenerSemana());

        if(ranking == null){
            context.status(404);
        }else{
            List<ItemRanking> items = RepositorioDeRankings.obtenerItemsDeRanking(ranking.getIdRanking());
            context.status(200).json(rankingMapper.generateResponse(ranking, items));
        }
    }
}
