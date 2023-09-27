package org.domain.handlers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.*;
import lombok.Setter;
import org.domain.mappers.RankingMapper;
import org.domain.models.Ranking;
import org.domain.models.dbo.RankingResponse;
import org.domain.persistence.Rankingdb;
import org.jetbrains.annotations.NotNull;

public class GetRankingSemanalHandler implements Handler {
    @Setter
    private RankingMapper rankingMapper = new RankingMapper();
    @Setter
    private Rankingdb rankingdb = new Rankingdb();

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
    public void handle(@NotNull Context context) {
        Ranking ranking = rankingdb.getSemanal();

        if(ranking == null){
            context.status(404);
        }else{
            context.status(200).json(rankingMapper.generateResponse(ranking));
        }
    }
}
