package org.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter @Getter
public class Entidad {
    private Integer id;
    private String nombre;
    private List<Incidente> incidentes;

    public Double calcularNivelDeImpacto() {
        List<Incidente> incidentesCerrados = this.incidentes.stream().filter(i -> !i.getActivo()).collect(Collectors.toList());
        return incidentesCerrados.stream().mapToDouble(Incidente::generarTiempoDeResolucion).sum();
    }

    public ItemRanking generarItemEnRanking(Double coeficiente) {
        ItemRanking itemRanking = new ItemRanking();
        itemRanking.setEntidad(this);
        Double valor = this.calcularNivelDeImpacto();
        Integer incidentesNoResueltos = Math.toIntExact(this.incidentes.stream().filter(Incidente::getActivo).count());
        itemRanking.setValor(valor + incidentesNoResueltos * coeficiente);
        return itemRanking;
    }

}
