package org.domain.models.dbo;

public class ItemResponse {
    private Integer posicion;
    private String nombre_entidad;
    private Integer id_entidad;
    private Double valor;

    public ItemResponse(Integer posicion, String nombre_entidad, Integer id_entidad, Double valor) {
        this.posicion = posicion;
        this.nombre_entidad = nombre_entidad;
        this.id_entidad = id_entidad;
        this.valor = valor;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public String getNombre_entidad() {
        return nombre_entidad;
    }

    public void setNombre_entidad(String nombre_entidad) {
        this.nombre_entidad = nombre_entidad;
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
