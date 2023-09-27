package org.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.domain.repositories.RepositorioDeEntidades;
import org.domain.repositories.RepositorioDeIncidentes;
import org.domain.repositories.RepositorioDeRankings;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "ranking")
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRanking;
    @Column
    private LocalDate semana;
    @Transient
    private Double coeficiente = 0.5;

    public Ranking(Integer id, LocalDate semana, List<ItemRanking> items) {
        this.idRanking = id;
        this.semana = semana;
    }

    public List<ItemRanking> getItems() {
        return RepositorioDeRankings.obtenerItemsDeRanking(idRanking);
    }

    public void generarRanking() {
        List<ItemRanking> items = new ArrayList<>();
        List<Entidad> entidades = RepositorioDeEntidades.obtenerTodasLasEntidades();
        List<Incidente> incidentesDeLaSemana = this.obtenerIncidentesDeLaSemana();

        for (Entidad entidad : entidades) {
            items.add(this.generarItem(this, entidad.getIdEntidad(), incidentesDeLaSemana));
        }

        items.sort(Comparator.comparingDouble(ItemRanking::getValor));

        int posicion = 1;
        for (ItemRanking item : items) {
            item.setPosicionEnRanking(posicion);
            posicion++;
        }

        RepositorioDeRankings.persistirRanking(this);
        RepositorioDeRankings.persistirItems(items);
    }

    public ItemRanking generarItem(Ranking ranking, Integer idEntidad, List<Incidente> incidentesDeLaSemana) {
        double valor = 0.0;

        List<Incidente> incidentesCerrados = incidentesDeLaSemana.stream().filter(i -> !i.getActivo()).collect(Collectors.toList());
        valor += incidentesCerrados.stream().mapToDouble(Incidente::getIntervaloDeResolucionEnMinutos).sum();

        valor += incidentesDeLaSemana.stream().filter(Incidente::getActivo).count() * coeficiente;

        return new ItemRanking(ranking, idEntidad, valor);
    }

    private List<Incidente> obtenerIncidentesDeLaSemana() {
        List<Incidente> incidentes = RepositorioDeIncidentes.obtenerTodosLosIncidentes();
        return incidentes.stream().filter(Incidente::emitidoEnLaSemana).collect(Collectors.toList());
    }

    public void ordenar(List<ItemRanking> items) {
        Collections.sort(items);
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
