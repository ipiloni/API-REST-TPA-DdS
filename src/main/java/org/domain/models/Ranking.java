package org.domain.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "ranking")
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private LocalDate semana;
    @OneToMany(mappedBy = "ranking")
    private List<ItemRanking> items;
    @Column
    private Double coeficiente = 0.5;

    public Ranking(LocalDate semana, List<ItemRanking> items) {
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
