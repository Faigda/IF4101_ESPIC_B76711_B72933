package com.example.if4101_espic_b76711_b72933.Solicitud;

public class Paciente {
    public int cedula;
    public String nombre;
    public String apellido;
    public String Tipo_Sangre;
    public String Estado_Civil;
    public String telefono;
    public String domicilio;
    public String contrasenna;

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipo_Sangre() {
        return Tipo_Sangre;
    }

    public void setTipo_Sangre(String tipo_Sangre) {
        Tipo_Sangre = tipo_Sangre;
    }

    public String getEstado_Civil() {
        return Estado_Civil;
    }

    public void setEstado_Civil(String estado_Civil) {
        Estado_Civil = estado_Civil;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }
}
