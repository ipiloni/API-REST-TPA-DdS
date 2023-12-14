package org.domain.persistence;

import org.domain.models.EntidadPropietaria;
import org.domain.models.Incidente;
import org.domain.models.ItemRanking;
import org.domain.models.Ranking;
import org.domain.persistence.repositories.RepositorioDeEntidades;
import org.domain.persistence.repositories.RepositorioDeIncidentes;
import org.domain.persistence.repositories.RepositorioDeRankings;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class PersistenceTest {
    // TODO: VERIFICAR QUE ESTE EN UPDATE LA CONEXION CON BASE DE DATOS!

    @Test
    public void baseDeDatosConectaCorrectamente() {
        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);
        BDUtils.commit(em);
    }

    @Test
    public void generarInformacionEnBaseDeDatos() {
       //EntidadPropietaria entidad1 = new EntidadPropietaria(1, "Don Satur");
       // EntidadPropietaria entidad2 = new EntidadPropietaria(2, "Boreal");
       // EntidadPropietaria entidad3 = new EntidadPropietaria(3, "Nivea");
       // EntidadPropietaria entidad4 = new EntidadPropietaria(4, "Adidas");
        Incidente incidente1 = new Incidente(13, LocalDateTime.now().minusDays(0), 1, LocalDateTime.now().plusDays(2), false);
        Incidente incidente2 = new Incidente(14, LocalDateTime.now().minusDays(0).plusHours(25), 1, LocalDateTime.now().plusDays(2).plusHours(2), false);
        Incidente incidente3 = new Incidente(15, LocalDateTime.now().minusDays(0).plusHours(12), 1, LocalDateTime.now().plusDays(2).plusHours(5), false);
        Incidente incidente4 = new Incidente(16, LocalDateTime.now().minusDays(0).plusHours(12), 1, LocalDateTime.now().plusDays(1).plusHours(12), false);
        Incidente incidente5 = new Incidente(17, LocalDateTime.now().minusDays(0).plusHours(5), 2, LocalDateTime.now().plusDays(2).minusHours(12), false);
        Incidente incidente6 = new Incidente(6, LocalDateTime.now().minusDays(5).plusHours(14), 2, LocalDateTime.now().minusDays(1).plusHours(5), false);
        Incidente incidente7 = new Incidente(7, LocalDateTime.now().minusDays(1).plusHours(6), 2, LocalDateTime.now().minusMinutes(30), false);
        Incidente incidente8 = new Incidente(8, LocalDateTime.now().minusDays(2).plusHours(5), 3, LocalDateTime.now().minusDays(2), false);
        Incidente incidente9 = new Incidente(9, LocalDateTime.now().minusDays(1).minusHours(5), 3, LocalDateTime.now().minusDays(1), false);
        Incidente incidente10 = new Incidente(10, LocalDateTime.now().minusDays(5).plusHours(6), 4, LocalDateTime.now().minusDays(2), false);
        Incidente incidente11 = new Incidente(11, LocalDateTime.now().minusDays(2).plusHours(5), 4, LocalDateTime.now().minusDays(1), false);
        Incidente incidente12 = new Incidente(12, LocalDateTime.now().minusDays(5).plusHours(12), 4, LocalDateTime.now().minusDays(2), false);

        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);
       // em.persist(entidad1);
       // em.persist(entidad2);
       // em.persist(entidad3);
       // em.persist(entidad4);
        BDUtils.commit(em);
        BDUtils.comenzarTransaccion(em);
        em.merge(incidente1);
        em.merge(incidente2);
        em.merge(incidente3);
        em.merge(incidente4);
        em.merge(incidente5);
        em.merge(incidente6);
        em.merge(incidente7);
        em.merge(incidente8);
        em.merge(incidente9);
        em.merge(incidente10);
        em.merge(incidente11);
        em.merge(incidente12);
        BDUtils.commit(em);
    }

    @Test
    public void obtenerEntidadesPrestadorasCorrectamente() {
        List<EntidadPropietaria> entidades = RepositorioDeEntidades.obtenerTodasLasEntidades();
        entidades.forEach(e -> System.out.println("Entidad: " + e.getRazonSocial()));
    }

    @Test
    public void obtenerIncidentesCorrectamente() {
        List<Incidente> incidentes = RepositorioDeIncidentes.obtenerIncidentesDeOrganizacion(1);
        Assert.assertEquals(4, incidentes.size());
    }

    @Test
    public void persistirRankingEnBaseDeDatosCorrectamente() {
        Ranking ranking = new Ranking();
        ranking.setSemana(Ranking.obtenerSemana());
        System.out.println(ranking.generarRanking(0.8));
    }

    @Test
    public void obtenerRankingSemanalCorrectamente() {
        Ranking ranking = RepositorioDeRankings.obtenerDeSemana(Ranking.obtenerSemana());
        System.out.println("Ranking: " + ranking.getSemana());
    }

    @Test
    public void obteneItemsDeRanking() {
        List<ItemRanking> items = RepositorioDeRankings.obtenerItemsDeRanking(2);
        items.forEach(e -> System.out.println("Item: " + e.getPosicion() + " " + e.getOrganizacion()));
    }

}
