package org.domain.repositories;

import org.domain.models.Entidad;
import org.domain.persistence.EntityManagerHelper;

import java.util.List;

public class RepositorioDeEntidades {
    public static List<Entidad> obtenerTodasLasEntidades() {
        List<Entidad> entidades = (List<Entidad>) EntityManagerHelper.createQuery("from Entidad").getResultList();
        return entidades;
    }

    public static Entidad obtenerPorId(Integer idEntidad) {
        return (Entidad) EntityManagerHelper.createQuery("from Entidad where idEntidad =" + idEntidad).getSingleResult();
    }
}
