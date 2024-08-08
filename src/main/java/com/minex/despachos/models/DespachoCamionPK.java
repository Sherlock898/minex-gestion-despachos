package com.minex.despachos.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DespachoCamionPK implements Serializable{
    @Column(name = "id_despacho")
    private Long idDespacho;
    @Column(name = "patente_camion")
    private String patenteCamion;

    public DespachoCamionPK(){}

    public Long getIdDespacho() {
        return idDespacho;
    }

    public void setIdDespacho(Long idDespacho) {
        this.idDespacho = idDespacho;
    }

    public String getPatenteCamion() {
        return patenteCamion;
    }

    public void setPstenteCamion(String patenteCamion) {
        this.patenteCamion = patenteCamion;
    }
}
