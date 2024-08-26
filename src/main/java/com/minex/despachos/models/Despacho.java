package com.minex.despachos.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Despacho {
    public enum UnidadDeMedida {
        M3, CM3, TON, KG
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long numero;
    @NotBlank
    private String direccion;
    @NotBlank
    private String comuna;
    @NotBlank
    private String ciudad;
    @NotBlank
    private String descripcionProducto;
    @NotNull
    private Long cantidad;
    @NotNull
    private UnidadDeMedida unidadDeMedida;
    @NotNull
    private Long precioUnitario;
    @NotNull
    private Long precioNeto;
    @NotNull
    private Long impuestoAdicional;

    @NotNull
    @ManyToOne
    private Productor productor;

    @NotNull
    @ManyToOne
    private Cliente cliente;

    @OneToOne(mappedBy = "despacho")
    private DespachoCamion despachoCamion;
    
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

    public Despacho(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public UnidadDeMedida getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(UnidadDeMedida unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public Long getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Long precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Long getPrecioNeto() {
        return precioNeto;
    }

    public void setPrecioNeto(Long precioNeto) {
        this.precioNeto = precioNeto;
    }

    public Long getImpuestoAdicional() {
        return impuestoAdicional;
    }

    public void setImpuestoAdicional(Long impuestoAdicional) {
        this.impuestoAdicional = impuestoAdicional;
    }

    public Productor getProductor() {
        return productor;
    }

    public void setProductor(Productor productor) {
        this.productor = productor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public DespachoCamion getDespachoCamion() {
        return despachoCamion;
    }

    public void setDespachoCamion(DespachoCamion despachoCamion) {
        this.despachoCamion = despachoCamion;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

}
