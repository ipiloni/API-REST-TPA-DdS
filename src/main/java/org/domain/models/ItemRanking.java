package org.domain.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter @Getter
@Entity
@Table(name = "ItemRanking")
public class ItemRanking implements Comparable<ItemRanking> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne @JoinColumn(name = "id_ranking")
    private Ranking ranking;
    @Column
    private Integer posicion;
    @ManyToOne @JoinColumn(name = "id_entidad")
    private Entidad id_entidad;
    @Column
    private Double valor;

    public ItemRanking(Integer posicion, Entidad id_entidad, Double valor) {
        this.posicion = posicion;
        this.id_entidad = id_entidad;
        this.valor = valor;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public Entidad getId_entidad() {
        return id_entidad;
    }

    public Double getValor() {
        return valor;
    }

    @Override
    public int compareTo(ItemRanking otroItem) {
        return Double.compare(this.valor, otroItem.valor);
    }
}
