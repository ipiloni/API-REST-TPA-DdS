package org.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter @Setter
public class Ranking {
    private List<Entidad> entidades;
    private List<ItemRanking> ranking;
    private Double coeficiente;

    public Ranking() {
        this.entidades = new ArrayList<>();
        this.ranking = new ArrayList<>();
    }

    public List<ItemRanking> generarRanking() {
        ranking = this.entidades.stream().map(entidad -> entidad.generarItemEnRanking(coeficiente)).collect(Collectors.toList());
        ordenarRanking(ranking);
        return ranking;
    }

    public void ordenarRanking(List<ItemRanking> items) {
        Collections.sort(items);
    }
}
