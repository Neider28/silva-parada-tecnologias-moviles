package com.example.silva_parada;

import java.io.Serializable;

public class Producto implements Serializable {
    private String id;
    private String nombre;
    private Double precio;
    private String urlImage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Producto() {

    }

    public Producto(String id, String nombre, Double precio, String urlImage) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.urlImage = urlImage;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
