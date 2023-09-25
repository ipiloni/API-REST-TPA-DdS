package org.domain.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ItemRanking implements Comparable<ItemRanking> {
    private Integer posicion;
    private Entidad entidad;
    private Double valor;

    public ItemRanking(Integer posicion, Entidad entidad, Double valor) {
        this.posicion = posicion;
        this.entidad = entidad;
        this.valor = valor;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public Double getValor() {
        return valor;
    }

    @Override
    public int compareTo(ItemRanking otroItem) {
        return Double.compare(this.valor, otroItem.valor);
    }
}
