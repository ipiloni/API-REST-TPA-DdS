package org.domain.persistence;

import org.domain.models.EntidadPropietaria;
import org.domain.models.ItemRanking;
import org.domain.models.Ranking;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Rankingdb {

    /*public Ranking get(Integer id){
        //TODO lo dejo hardcodeado
        EntidadPropietaria entidadPropietaria = new EntidadPropietaria(3,"EntidadEjemplo");
        ItemRanking item = new ItemRanking(1, entidadPropietaria, 50.4);
        Ranking ranking = new Ranking(LocalDate.of(2023, 9, 11),List.of(item));
        return ranking;
    }*/

    public Ranking getSemanal(){
        Optional<Ranking> ranking = (Optional<Ranking>) BDUtils.createQuery(" from Ranking where semana =" + Ranking.obtenerSemana()).getSingleResult();
        if(ranking.isPresent()) {
            return ranking.get();
        } else {
            Ranking r = new Ranking();
            r.generarRanking();
            return r;
        }
    }
}
