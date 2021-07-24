package com.example.if4101_espic_b76711_b72933.Respuesta;

public class Cita {
    public int id_Cita;
    public String centro_Salud;
    public String fecha;
    public String especialidad;
    public String detalle;
    public String nombre;
    public int codigoc_Medico;

    public int getCodigoc_Medico() {
        return codigoc_Medico;
    }

    public void setCodigoc_Medico(int codigoc_Medico) {
        this.codigoc_Medico = codigoc_Medico;
    }

    public int getId_Cita() {
        return id_Cita;
    }

    public void setId_Cita(int id_Cita) {
        this.id_Cita = id_Cita;
    }

    public String getCentro_Salud() {
        return centro_Salud;
    }

    public void setCentro_Salud(String centro_Salud) {
        this.centro_Salud = centro_Salud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
