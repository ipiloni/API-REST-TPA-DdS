package org.domain.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "incidente")
public class Incidente {
    @Id
    @Column(name = "idIncidente")
    private Integer id;
    @Column(name = "fechaInicio")
    private LocalDateTime fechaInicio;
    @Column(name = "idOrganizacion")
    private Integer idOrganizacion;
    @Column(name = "fechaFin")
    private LocalDateTime fechaFin;
    @Column(name = "activo")
    private Boolean activo;

    public Incidente(Integer id, LocalDateTime fechaInicio, Integer idOrganizacion, LocalDateTime fechaFin, Boolean activo) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.idOrganizacion = idOrganizacion;
        this.fechaFin = fechaFin;
        this.activo = activo;
    }

    public Incidente() { }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public LocalDateTime getFechaInicio() { return fechaInicio; }

    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }

    public Integer getIdOrganizacion() { return idOrganizacion; }

    public void setIdOrganizacion(Integer idOrganizacion) { this.idOrganizacion = idOrganizacion; }

    public LocalDateTime getFechaFin() { return fechaFin; }

    public void setFechaFin(LocalDateTime fechaFin) { this.fechaFin = fechaFin; }

    public Boolean getActivo() { return activo; }

    public void setActivo(Boolean activo) { this.activo = activo; }

    public boolean emitidoEnLaSemana() {
        LocalDateTime primerDiaSemana = LocalDateTime.now().with(DayOfWeek.MONDAY).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime ultimoDiaSemana = LocalDateTime.now().with(DayOfWeek.SUNDAY).withHour(23).withMinute(59).withSecond(59);

        return (this.fechaInicio.isAfter(primerDiaSemana) && this.fechaInicio.isBefore(ultimoDiaSemana.plusSeconds(1)));
    }

    public double getIntervaloDeResolucionEnMinutos() {
        return Duration.between(fechaInicio, fechaFin).toMinutes();
    }
}
