package org.domain.models.dbo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ItemResponse {
    private Integer posicion;
    private Integer id_entidad;
    private Double valor;
}
