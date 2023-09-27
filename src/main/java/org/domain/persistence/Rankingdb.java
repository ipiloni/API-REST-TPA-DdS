package org.domain.persistence;

import org.domain.models.Entidad;
import org.domain.models.ItemRanking;
import org.domain.models.Ranking;
import org.domain.repositories.RepositorioDeRankings;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Rankingdb {

    public Ranking get(Integer id){
        return RepositorioDeRankings.obtenerPorId(id);
    }

    public Ranking getSemanal() {
        Optional<Ranking> ranking = RepositorioDeRankings.obtenerDeSemana(Ranking.obtenerSemana());

        return ranking.orElse(null);
    }

}
