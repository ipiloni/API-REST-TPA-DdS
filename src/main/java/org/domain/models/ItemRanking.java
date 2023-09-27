package org.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter @Getter
@NoArgsConstructor
@Entity
@Table(name = "ItemRanking")
public class ItemRanking implements Comparable<ItemRanking> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Ranking ranking;
    @Column
    private Integer posicionEnRanking;
    @Column
    private Integer idEntidad;
    @Column
    private Double valor;

    public ItemRanking(Ranking r, Integer id, Double v) {
        this.ranking = r;
        this.idEntidad = id;
        this.valor = v;
    }

    @Override
    public int compareTo(ItemRanking otroItem) {
        return Double.compare(this.valor, otroItem.valor);
    }
}
