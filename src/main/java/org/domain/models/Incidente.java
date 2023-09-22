package org.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter @Setter
public class Incidente {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Boolean activo;

    public double generarTiempoDeResolucion() {
        Duration duracion = Duration.between(fechaInicio, fechaFin);

        return duracion.toSeconds();
    }
}
