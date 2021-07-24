package com.example.if4101_espic_b76711_b72933.Respuesta;

public class Vacuna {
    public int id_Vacuna;
    public String nombre;
    public String descripcion;
    public String fecha_Vacuna;
    public String fecha_Siquiente;
    public String centro_Salud;

    public int getId_Vacuna() {
        return id_Vacuna;
    }

    public void setId_Vacuna(int id_Vacuna) {
        this.id_Vacuna = id_Vacuna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha_Vacuna() {
        return fecha_Vacuna;
    }

    public void setFecha_Vacuna(String fecha_Vacuna) {
        this.fecha_Vacuna = fecha_Vacuna;
    }

    public String getFecha_Siquiente() {
        return fecha_Siquiente;
    }

    public void setFecha_Siquiente(String fecha_Siquiente) {
        this.fecha_Siquiente = fecha_Siquiente;
    }

    public String getCentro_Salud() {
        return centro_Salud;
    }

    public void setCentro_Salud(String centro_Salud) {
        this.centro_Salud = centro_Salud;
    }
}
