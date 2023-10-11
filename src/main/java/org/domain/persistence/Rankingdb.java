package org.domain.persistence;

import org.domain.models.Entidad;
import org.domain.models.ItemRanking;
import org.domain.models.Ranking;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Rankingdb {

    public Ranking get(Integer id){
        //TODO lo dejo hardcodeado
        Entidad entidad = new Entidad(3,"EntidadEjemplo");
        ItemRanking item = new ItemRanking(1, entidad, 50.4);
        Ranking ranking = new Ranking(LocalDate.of(2023, 9, 11),List.of(item));
        return ranking;
    }

    public Ranking getSemanal(){
        //TODO lo dejo hardcodeado
        /*Entidad entidad = new Entidad(3,"EntidadEjemplo");
        ItemRanking item = new ItemRanking(1, entidad, 50.4);
        Ranking ranking = new Ranking(LocalDate.of(2023, 9, 11),List.of(item));
        return ranking;*/
        Optional<Ranking> ranking = (Optional<Ranking>) BDUtils.createQuery(" from Ranking where semana =" + "2023-10-08").getSingleResult();
        return ranking.orElse(null);
    }
}
