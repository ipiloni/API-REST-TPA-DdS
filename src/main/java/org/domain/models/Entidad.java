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

    public Entidad(Integer id, String nombre, List<Incidente> incidentes) {
        this.id = id;
        this.nombre = nombre;
        this.incidentes = incidentes;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void filtrarIncidentesDeLaSemana() {
        incidentes = incidentes.stream().filter(Incidente::emitidoEnLaSemana).collect(Collectors.toList());
    }

    public Double getNivelDeImpacto(Double coeficiente){
        return this.sumTiempoResolucionIncidentes() + this.countIncidentesActivos() * coeficiente;
    }

    private Double sumTiempoResolucionIncidentes(){
        List<Incidente> incidentesCerrados = this.getIncidentesCerrados();
        return incidentesCerrados.stream().mapToDouble(Incidente::getTiempoDeResolucion).sum();
    }

    private List<Incidente> getIncidentesCerrados(){
        return incidentes.stream().filter(i -> !i.getActivo()).collect(Collectors.toList());
    }

    private Integer countIncidentesActivos(){
        return Math.toIntExact(this.incidentes.stream().filter(Incidente::getActivo).count());
    }
}
