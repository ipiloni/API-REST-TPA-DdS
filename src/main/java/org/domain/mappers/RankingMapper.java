package org.domain.mappers;

import org.domain.models.ItemRanking;
import org.domain.models.Ranking;
import org.domain.models.dbo.ItemResponse;
import org.domain.models.dbo.RankingResponse;

import java.util.ArrayList;
import java.util.List;

public class RankingMapper {

    public RankingResponse generateResponse(Ranking ranking){
        return new RankingResponse(
                ranking.getSemana().toString(),
                generateItemsResponse(ranking.getItems())
        );
    }

    public List<ItemResponse> generateItemsResponse(List<ItemRanking> items){
        List<ItemResponse> itemResponses = new ArrayList<>();

        items.forEach(itemRanking -> itemResponses.add(new ItemResponse(
                itemRanking.getPosicion(),
                itemRanking.getEntidad().getNombre(),
                itemRanking.getEntidad().getId(),
                itemRanking.getValor()
        )));

        return itemResponses;
    }
}
