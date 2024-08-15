package com.minex.despachos.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DespachoCamionPK implements Serializable{
    @Column(name = "id_despacho")
    private Long idDespacho;
    @Column(name = "id_camion")
    private Long idCamion;
    @Column(name = "id_chofer")
    private Long idChofer;

    public DespachoCamionPK(){}

    public Long getIdDespacho() {
        return idDespacho;
    }

    public void setIdDespacho(Long idDespacho) {
        this.idDespacho = idDespacho;
    }

    public Long getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(Long idCamion) {
        this.idCamion = idCamion;
    }

    public Long getIdChofer() {
        return idChofer;
    }

    public void setIdChofer(Long idChofer) {
        this.idChofer = idChofer;
    }
}
