package org.domain.models;

import javax.persistence.*;

@Entity
@Table(name = "ItemRanking")
public class ItemRanking implements Comparable<ItemRanking> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "ranking_idRanking")
    private Ranking ranking;
    @Column(name = "posicionEnRanking")
    private Integer posicionEnRanking;
    @Column(name = "entidad_idOrganizacion")
    private Integer idOrganizacion;
    @Column(name = "valor")
    private Double valor;

    public ItemRanking(Ranking ranking, Integer idOrganizacion, Double valor) {
        this.ranking = ranking;
        this.idOrganizacion = idOrganizacion;
        this.valor = valor;
    }

    public ItemRanking() {}

    public ItemRanking(Integer posicion, Integer idOrganizacion, Double valor) {
        this.posicionEnRanking = posicion;
        this.idOrganizacion = idOrganizacion;
        this.valor = valor;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Ranking getRanking() { return ranking; }

    public void setRanking(Ranking ranking) { this.ranking = ranking; }

    public Integer getPosicion() { return posicionEnRanking; }

    public void setPosicion(Integer posicion) { this.posicionEnRanking = posicion; }

    public Integer getOrganizacion() { return idOrganizacion; }

    public void setOrganizacion(Integer idOrganizacion) { this.idOrganizacion = idOrganizacion; }

    public Double getValor() { return valor; }

    public void setValor(Double valor) { this.valor = valor; }

    @Override
    public int compareTo(ItemRanking otroItem) {
        return Double.compare(this.valor, otroItem.valor);
    }
}
