package org.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

@Getter @Setter
public class Incidente {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Boolean activo;

    public Incidente() {}

    public Incidente(LocalDateTime fI, LocalDateTime fF, Boolean a) {
        this.fechaInicio = fI;
        this.fechaFin = fF;
        this.activo = a;
    }

    public double generarTiempoDeResolucion() {
        Duration duracion = Duration.between(fechaInicio, fechaFin);

        return duracion.toSeconds();
    }

    public boolean emitidoEnLaSemana() {
        LocalDateTime primerDiaSemana = LocalDateTime.now().with(DayOfWeek.MONDAY).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime ultimoDiaSemana = LocalDateTime.now().with(DayOfWeek.SUNDAY).withHour(23).withMinute(59).withSecond(59);

        return (this.fechaInicio.isAfter(primerDiaSemana) && this.fechaInicio.isBefore(ultimoDiaSemana.plusSeconds(1)));
    }
}
