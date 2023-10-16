package org.domain.models;

import org.domain.persistence.repositories.RepositorioDeIncidentes;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "organizacion")
public class EntidadPropietaria {
    @Id
    @Column(name = "idOrganizacion")
    private Integer idOrganizacion;
    @Column(name = "razonSocial")
    private String razonSocial;
    @Column(name = "tipoOrganizacion")
    private String tipoOrganizacion = "ENTIDAD_PROPIETARIA";

    public EntidadPropietaria() { }

    public EntidadPropietaria(Integer id, String nombre) {
        this.idOrganizacion = id;
        this.razonSocial = nombre;
    }

    public Integer getIdOrganizacion() { return idOrganizacion; }

    public String getRazonSocial() { return razonSocial; }

    public String getTipoOrganizacion() { return tipoOrganizacion; }

    public void setIdOrganizacion(Integer idOrganizacion) { this.idOrganizacion = idOrganizacion; }

    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }

    public void setTipoOrganizacion(String tipoOrganizacion) { this.tipoOrganizacion = tipoOrganizacion; }

    public List<Incidente> filtrarIncidentesDeLaSemana() {
        List<Incidente> incidentes = RepositorioDeIncidentes.obtenerIncidentesDeOrganizacion(this.idOrganizacion);
        return incidentes.stream().filter(Incidente::emitidoEnLaSemana).collect(Collectors.toList());
    }

    public Double getNivelDeImpacto(Double coeficiente){
        return this.sumTiempoResolucionIncidentes() + this.countIncidentesActivos() * coeficiente;
    }

    private Double sumTiempoResolucionIncidentes(){
        List<Incidente> incidentesCerrados = this.getIncidentesCerrados();
        return incidentesCerrados.stream().mapToDouble(Incidente::getIntervaloDeResolucionEnMinutos).sum();
    }

    private List<Incidente> getIncidentesCerrados(){
        List<Incidente> incidentes = this.filtrarIncidentesDeLaSemana();
        return incidentes.stream().filter(i -> !i.getActivo()).collect(Collectors.toList());
    }

    private Integer countIncidentesActivos(){
        List<Incidente> incidentes = this.filtrarIncidentesDeLaSemana();
        return Math.toIntExact(incidentes.stream().filter(Incidente::getActivo).count());
    }
}
