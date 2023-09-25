package org.domain.handlers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.*;
import lombok.Setter;
import org.domain.mappers.RankingMapper;
import org.domain.models.Entidad;
import org.domain.models.ItemRanking;
import org.domain.models.Ranking;
import org.domain.models.dbo.RankingResponse;
import org.domain.persistence.Rankingdb;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;

public class GetRankingHandler implements Handler{
    @Setter
    private RankingMapper rankingMapper = new RankingMapper();
    private Rankingdb rankingdb = new Rankingdb();

    @Override
    @OpenApi(
            summary = "Get ranking",
            operationId = "getRanking",
            path = "/ranking",
            methods = HttpMethod.GET,
            tags = {"Ranking"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = RankingResponse.class)})
            }
    )

    public void handle(@NotNull Context context) throws Exception {
       //no recibe body -> no valida datos del body

        Ranking ranking = rankingdb.get();

        if(ranking != null){
            context.status(200).json(rankingMapper.generateResponse(ranking));
        }else{
            context.status(404);
        }
    }
}
