package org.domain.models.dbo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class RankingResponse {
    private String semana;
    private List<ItemResponse> items;
}
