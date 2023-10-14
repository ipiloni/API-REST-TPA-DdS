package org.domain.models.repositories;

import org.domain.models.EntidadPropietaria;
import org.domain.persistence.BDUtils;

import java.util.List;

public class RepositorioDeEntidades {
    public static List<EntidadPropietaria> obtenerTodasLasEntidades() {
        List<EntidadPropietaria> entidades = (List<EntidadPropietaria>) BDUtils.createQuery("from Organizacion where tipoOrganizacion=ENTIDAD_PROPIETARIA").getResultList();
        return entidades;
    }

    public static EntidadPropietaria obtenerPorId(Integer idEntidad) {
        return (EntidadPropietaria) BDUtils.createQuery("from Organizacion where idOrganizacion =" + idEntidad).getSingleResult();
    }
}
