package modelo;

import controlador.TDA.listas.LinkedList;

public class Estacion {
    
    private String nombre;
    private Double latitud;
    private Double longitud;
    private LinkedList<String> fotos;

    public Estacion(String nombre, Double latitud, Double longitud, LinkedList<String> fotos) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fotos = fotos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public LinkedList<String> getFotos() {
        return fotos;
    }

    public void setFotos(LinkedList<String> fotos) {
        this.fotos = fotos;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
