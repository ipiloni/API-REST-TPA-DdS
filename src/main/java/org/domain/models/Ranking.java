package org.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class Ranking {
    private List<Entidad> entidades;
    private Double coeficiente;

    public List<ItemRanking> generarRanking() {
        List<ItemRanking> ranking;
        ranking = this.entidades.stream().map(entidad -> entidad.generarItemEnRanking(coeficiente)).collect(Collectors.toList());
        ordenarRanking(ranking);
        return ranking;
    }

    public void ordenarRanking(List<ItemRanking> items) {
        Collections.sort(items);

    }
}
