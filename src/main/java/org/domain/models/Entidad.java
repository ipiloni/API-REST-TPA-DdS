package org.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

@Setter @Getter
@AllArgsConstructor
public class Entidad {
    private Integer idEntidad;
    private String nombre;
}
