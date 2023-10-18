/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.practica3.POJO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.ManyToAny;

/**
 *
 * @author obed
 */
@Entity(name ="detalle_venta")
public class DetVenta{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="detVenta_id")
    private Long idVenta;
    
    @ManyToOne()
    @JoinColumn(name="venta_id")
    private Venta venta;
    
    @ManyToOne()
    @JoinColumn(name="producto_id")
    private Producto Producto;
    
    @Column
    private int cantidad;
    
    @Column
    private String descripcion;
    
    @Column
    private double precio;

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return Producto;
    }

    public void setProducto(Producto producto) {
        this.Producto = producto;
        this.descripcion=producto.getDescripcion();
        this.precio=producto.getPrecio();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    

    
    
    
    
}
