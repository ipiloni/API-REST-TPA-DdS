package org.domain.models;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ItemRanking implements Comparable<ItemRanking> {
    private Entidad entidad;
    private Double valor;

    @Override
    public int compareTo(ItemRanking otroItem) {
        return otroItem.valor.compareTo(this.valor);
    }
}
