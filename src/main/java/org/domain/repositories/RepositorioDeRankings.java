package org.domain.repositories;

import org.domain.models.ItemRanking;
import org.domain.models.Ranking;
import org.domain.persistence.EntityManagerHelper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RepositorioDeRankings {
    public static List<ItemRanking> obtenerItemsDeRanking(Integer idRanking) {
        return (List<ItemRanking>) EntityManagerHelper.createQuery("from ItemRanking where ranking = " + idRanking).getResultList();
    }

    public static Ranking obtenerPorId(Integer idRanking) {
        return (Ranking) EntityManagerHelper.createQuery("from Ranking where id = " + idRanking).getSingleResult();
    }

    public static void persistirRanking(Ranking ranking) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(ranking);
        EntityManagerHelper.commit();
    }

    public static void persistirItem(ItemRanking item) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(item);
        EntityManagerHelper.commit();
    }

    public static void persistirItems(List<ItemRanking> items) {
        for(ItemRanking item : items) {
            persistirItem(item);
        }
    }

    public static Optional<Ranking> obtenerDeSemana(LocalDate semana) {
        Optional<Ranking> ranking = (Optional<Ranking>) EntityManagerHelper.createQuery("from Ranking where semana =" + semana).getSingleResult();
        return ranking;
    }

}
