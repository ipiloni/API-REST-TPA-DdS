package org.domain.repositories;

import org.domain.models.Incidente;
import org.domain.persistence.EntityManagerHelper;

import java.util.List;

public class RepositorioDeIncidentes {
    public static List<Incidente> obtenerTodosLosIncidentes() {
        return (List<Incidente>) EntityManagerHelper.createQuery("from Incidente").getResultList();
    }
}
