package com.example.if4101_espic_b76711_b72933.Interfaz;

import com.example.if4101_espic_b76711_b72933.Respuesta.ActualizarRespuesta;
import com.example.if4101_espic_b76711_b72933.Respuesta.Alergia;
import com.example.if4101_espic_b76711_b72933.Respuesta.Cita;
import com.example.if4101_espic_b76711_b72933.Respuesta.LoginRespuesta;
import com.example.if4101_espic_b76711_b72933.Respuesta.PacienteRespuesta;
import com.example.if4101_espic_b76711_b72933.Respuesta.RegistroRespuesta;
import com.example.if4101_espic_b76711_b72933.Solicitud.BuscarAlergia;
import com.example.if4101_espic_b76711_b72933.Solicitud.BuscarCita;
import com.example.if4101_espic_b76711_b72933.Solicitud.BuscarVacuna;
import com.example.if4101_espic_b76711_b72933.Solicitud.Login;
import com.example.if4101_espic_b76711_b72933.Solicitud.Paciente;
import com.example.if4101_espic_b76711_b72933.Respuesta.Vacuna;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Solicitudes {

    @POST("Login")
    Call<LoginRespuesta> login(@Body Login login);

    @POST("Registro")
    Call<RegistroRespuesta> registro(@Body Paciente paciente);

    @PUT("Actualizar")
    Call<ActualizarRespuesta> actualizar(@Body Paciente paciente);

    @GET("Datos/{cedula}")
    Call<PacienteRespuesta> datos(@Path("cedula") int cedula);

    @GET("Vacunas/{cedula}")
    Call<ArrayList<Vacuna>> vacunas(@Path("cedula") int cedula);

    @POST("Vacunas/Detalle")
    Call<Vacuna> detalleVacunas(@Body BuscarVacuna buscarVacuna);

    @GET("Citas/{cedula}")
    Call<ArrayList<Cita>> citas(@Path("cedula") int cedula);

    @POST("Citas/Detalle")
    Call<Cita> detalleCitas(@Body BuscarCita buscarCita);

    @GET("Alergias/{cedula}")
    Call<ArrayList<Alergia>> alergias(@Path("cedula") int cedula);

    @POST("Alergias/Detalle")
    Call<Alergia> detalleAlergias(@Body BuscarAlergia buscarAlergia);

}
