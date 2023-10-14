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

public class GetRankingByIdHandler implements Handler{
    @Setter
    private RankingMapper rankingMapper = new RankingMapper();
    @Setter
    private final Rankingdb rankingdb = new Rankingdb();

    @Override
    @OpenApi(
            summary = "Get ranking by id",
            operationId = "getRankingById",
            path = "/ranking/{id}",
            methods = HttpMethod.GET,
            tags = {"Ranking"},
            pathParams = {@OpenApiParam(name = "id", type = Integer.class, description = "Ranking id", required = true)},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = RankingResponse.class)}),
                    @OpenApiResponse(status = "404", description = "Ranking not found")
            }
    )
    public void handle(@NotNull Context context) throws Exception {
        Integer id = Integer.parseInt(context.pathParam("id"));

        if(id == null){
            context.status(404);
        }else{
            /*Ranking ranking = rankingdb.get(id);

            if(ranking == null){
                context.status(404);
            }else{
                context.status(200).json(rankingMapper.generateResponse(ranking));
            }*/
        }
    }
}
