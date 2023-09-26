package org.domain.persistence;

import org.domain.models.Entidad;
import org.domain.models.ItemRanking;
import org.domain.models.Ranking;

import java.time.LocalDate;
import java.util.List;

public class Rankingdb {

    public Ranking get(Integer id){
        //TODO lo dejo hardcodeado
        Entidad entidad = new Entidad(3,"EntidadEjemplo", List.of());
        ItemRanking item = new ItemRanking(1, entidad, 50.4);
        Ranking ranking = new Ranking(1, LocalDate.of(2023, 9, 11),List.of(item));
        return ranking;
    }

    public Ranking getSemanal(){
        //TODO lo dejo hardcodeado
        Entidad entidad = new Entidad(3,"EntidadEjemplo", List.of());
        ItemRanking item = new ItemRanking(1, entidad, 50.4);
        Ranking ranking = new Ranking(1, LocalDate.of(2023, 9, 11),List.of(item));
        return ranking;
    }
}
