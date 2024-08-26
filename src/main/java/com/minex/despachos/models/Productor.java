package com.minex.despachos.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Productor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^[0-9]{7,8}-[0-9Kk]$", message = "Rut inválido")
    private String rut;
    @NotBlank
    private String razonSocial;
    @NotBlank
    private String direccionActual;
    @NotBlank
    private String comunaActual;
    @NotBlank
    private String ciudadActual;
    @NotBlank
    private String telefono;
    @NotBlank
    private String email;

    @OneToMany(mappedBy = "productor")
    private List<DespachoCamion> despachoCamiones;

    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    public Productor(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public List<DespachoCamion> getDespachos() {
        return despachoCamiones;
    }

    public void setDespachos(List<DespachoCamion> despachoCamiones) {
        this.despachoCamiones = despachoCamiones;
    }

    public String getDireccionActual() {
        return direccionActual;
    }

    public void setDireccionActual(String direccionActual) {
        this.direccionActual = direccionActual;
    }

    public String getComunaActual() {
        return comunaActual;
    }

    public void setComunaActual(String comunaActual) {
        this.comunaActual = comunaActual;
    }

    public String getCiudadActual() {
        return ciudadActual;
    }

    public void setCiudadActual(String ciudadActual) {
        this.ciudadActual = ciudadActual;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
