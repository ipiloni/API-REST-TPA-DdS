package org.domain.models.dbo;

public class ItemResponse {
    private Integer posicion;
    private Integer id_entidad;
    private Double valor;

    public ItemResponse(Integer posicion, Integer id_entidad, Double valor) {
        this.posicion = posicion;
        this.id_entidad = id_entidad;
        this.valor = valor;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public Integer getId_entidad() {
        return id_entidad;
    }

    public void setId_entidad(Integer id_entidad) {
        this.id_entidad = id_entidad;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
