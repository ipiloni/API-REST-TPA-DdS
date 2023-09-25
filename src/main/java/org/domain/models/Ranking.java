package org.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Getter @Setter
public class Ranking {
    private Integer id;
    private LocalDate semana;
    private List<ItemRanking> items;
    private Double coeficiente = 0.5;

    public Ranking(Integer id, LocalDate semana, List<ItemRanking> items) {
        this.id = id;
        this.semana = semana;
        this.items = items;
    }

    public LocalDate getSemana() {
        return semana;
    }

    public List<ItemRanking> getItems() {
        return items;
    }

    public void setCoeficiente(Double coeficiente) {
        this.coeficiente = coeficiente;
    }

    public void generar() {
        /*Esto lo hace el proceso calendarizado y lo persiste*/
    }

    public void ordenar(List<ItemRanking> items) {
        Collections.sort(items);
    }
}
