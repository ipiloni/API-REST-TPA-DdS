package org.domain.persistence.repositories;

import org.domain.models.Incidente;
import org.domain.persistence.BDUtils;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeIncidentes {

    public static List<Incidente> obtenerTodosLosIncidentes() {
        return (List<Incidente>) BDUtils.createQuery("from Incidente").getResultList();
    }

    public static List<Incidente> obtenerIncidentesDeOrganizacion(Integer idOrganizacion) {
        return (List<Incidente>) BDUtils.createQuery("from Incidente where idOrganizacion="+idOrganizacion).getResultList();
    }
}
