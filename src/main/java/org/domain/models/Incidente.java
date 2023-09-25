package org.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;

@Getter @Setter
public class Incidente {
    private Date fechaInicio;
    private Date fechaFin;
    private Boolean activo;

    public Incidente(Date fI, Date fF, Boolean a) {
        this.fechaInicio = fI;
        this.fechaFin = fF;
        this.activo = a;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public double getTiempoDeResolucion() {
        long tiempoInicio = fechaInicio.getTime();
        long tiempoFin = fechaFin.getTime();

        return (double) (tiempoFin - tiempoInicio) / 1000;
    }

    public boolean emitidoEnLaSemana() {
        Calendar calendarDomingo = Calendar.getInstance();
        Calendar calendarLunes = Calendar.getInstance();
        calendarDomingo.setTime(new Date());
        calendarLunes.setTime(new Date());

        calendarDomingo.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendarLunes.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        calendarDomingo.set(Calendar.HOUR_OF_DAY, 23);
        calendarDomingo.set(Calendar.MINUTE, 59);
        calendarDomingo.set(Calendar.SECOND, 59);

        calendarLunes.set(Calendar.HOUR_OF_DAY, 0);
        calendarLunes.set(Calendar.MINUTE, 0);
        calendarLunes.set(Calendar.SECOND, 0);

        Date ultimoDiaSemana = calendarDomingo.getTime();
        Date primerDiaSemana = calendarLunes.getTime();

        return (this.fechaInicio.after(primerDiaSemana) && this.fechaInicio.before(ultimoDiaSemana));
    }
}
