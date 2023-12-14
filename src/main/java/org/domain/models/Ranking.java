package org.domain.models;

import org.domain.persistence.repositories.RepositorioDeEntidades;
import org.domain.persistence.repositories.RepositorioDeIncidentes;
import org.domain.persistence.repositories.RepositorioDeRankings;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "ranking")
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idRanking;
    @Column(name = "semana")
    private LocalDate semana;

    @Column(name = "tipoRanking")
    private String tipoRanking;
    @Column(name = "coeficiente")
    private Double coeficiente = 0.5;

    public Ranking(Integer id, LocalDate semana) {
        this.idRanking = id;
        this.semana = semana;
    }

    public Ranking() { }

    public Integer getIdRanking() { return idRanking; }

    public void setIdRanking(Integer idRanking) { this.idRanking = idRanking; }

    public LocalDate getSemana() { return semana; }

    public void setSemana(LocalDate semana) { this.semana = semana; }

    public Double getCoeficiente() { return coeficiente; }

    public void setCoeficiente(Double coeficiente) { this.coeficiente = coeficiente; }

    public void generarRanking() {
        this.tipoRanking = "GRADO_IMPACTO_PROBLEMATICAS";
        List<ItemRanking> items = new ArrayList<>();
        List<EntidadPropietaria> entidades = RepositorioDeEntidades.obtenerTodasLasEntidades();
        List<Incidente> incidentesDeLaSemana = this.obtenerIncidentesDeLaSemana();

        for (EntidadPropietaria entidadPropietaria : entidades) {
            items.add(this.generarItem(this, entidadPropietaria.getIdOrganizacion(), incidentesDeLaSemana));
        }

        items.sort(Comparator.comparingDouble(ItemRanking::getValor).reversed());

        int posicion = 1;
        for (ItemRanking item : items) {
            item.setPosicion(posicion);
            posicion++;
        }

        RepositorioDeRankings.persistirRanking(this);
        RepositorioDeRankings.persistirItems(items);
    }

    public ItemRanking generarItem(Ranking ranking, Integer idEntidad, List<Incidente> incidentesDeLaSemana) {
        double valor = 0.0;

        List<Incidente> incidentesCerrados = incidentesDeLaSemana.stream().filter(i -> !i.getActivo() && Objects.equals(i.getIdOrganizacion(), idEntidad)).collect(Collectors.toList());
        valor += incidentesCerrados.stream().mapToDouble(Incidente::getIntervaloDeResolucionEnMinutos).sum();

        valor += incidentesDeLaSemana.stream().filter(Incidente::getActivo).count() * coeficiente;

        return new ItemRanking(ranking, idEntidad, valor);
    }

    private List<Incidente> obtenerIncidentesDeLaSemana() {
        List<Incidente> incidentes = RepositorioDeIncidentes.obtenerTodosLosIncidentes();
        return incidentes.stream().filter(Incidente::emitidoEnLaSemana).collect(Collectors.toList());
    }

    public static LocalDate obtenerSemana() {
        Integer lunesDeLaSemana = obtenerLunes();
        Month mes = LocalDate.now().getMonth();
        int anio = LocalDate.now().getYear();
        return LocalDate.of(anio, mes, lunesDeLaSemana);
    }

    public static Integer obtenerLunes() {
        DayOfWeek diaDeLaSemana = LocalDate.now().getDayOfWeek();
        int numeroDelDia = LocalDate.now().getDayOfMonth();
        switch (diaDeLaSemana) {
            case MONDAY: return numeroDelDia;
            case TUESDAY: return numeroDelDia - 1;
            case WEDNESDAY: return numeroDelDia - 2;
            case THURSDAY: return numeroDelDia - 3;
            case FRIDAY: return numeroDelDia - 4;
            case SATURDAY: return numeroDelDia - 5;
            case SUNDAY: return numeroDelDia - 6;
            default: throw new RuntimeException("Algo paso y no es culpa nuestra :)");
        }
    }
}
