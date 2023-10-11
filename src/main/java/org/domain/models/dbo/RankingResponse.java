package org.domain.models.dbo;

import java.util.List;

public class RankingResponse {
    private String semana;
    //private List<ItemResponse> items;

    public RankingResponse(String semana/*, List<ItemResponse> items*/) {
        this.semana = semana;
       // this.items = items;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

   /* public List<ItemResponse> getItems() {
        return items;
    }

    public void setItems(List<ItemResponse> items) {
        this.items = items;
    }*/
}
