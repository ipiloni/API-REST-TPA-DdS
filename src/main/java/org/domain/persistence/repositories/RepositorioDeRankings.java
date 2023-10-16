package org.domain.persistence.repositories;

import org.domain.models.ItemRanking;
import org.domain.models.Ranking;
import org.domain.persistence.BDUtils;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RepositorioDeRankings {
    public static List<ItemRanking> obtenerItemsDeRanking(Integer idRanking) {
        return (List<ItemRanking>) BDUtils.createQuery("from ItemRanking where ranking = " + idRanking).getResultList();
    }

    public static Ranking obtenerPorId(Integer idRanking) {
        return (Ranking) BDUtils.createQuery("from Ranking where id = " + idRanking).getSingleResult();
    }

    public static void persistirRanking(Ranking ranking) {
        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);
        em.persist(ranking);
        BDUtils.commit(em);
    }

    public static void persistirItem(ItemRanking item) {
        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);
        em.persist(item);
        BDUtils.commit(em);
    }

    public static void persistirItems(List<ItemRanking> items) {
        for(ItemRanking item : items) {
            persistirItem(item);
        }
    }

    public static Ranking obtenerDeSemana(LocalDate semana) {
        Ranking ranking = null;
        try {
             ranking = Optional.ofNullable((Ranking) BDUtils
                    .createQuery("from Ranking where semana = '" + semana + "'").getSingleResult())
                    .orElse(null);
        } catch (NoResultException e){
            return ranking;
        }

        return ranking;
    }
}
