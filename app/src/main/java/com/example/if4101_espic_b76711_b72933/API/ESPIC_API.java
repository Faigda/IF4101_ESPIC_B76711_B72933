package com.example.if4101_espic_b76711_b72933.API;

import com.example.if4101_espic_b76711_b72933.Interfaz.Solicitudes;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ESPIC_API {

    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Asociamos el interceptor a las peticiones
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);



        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .baseUrl("http://faigdaapi-001-site1.itempurl.com/api/Pacientes/")
                .build();


        return retrofit;
    }

    public static Solicitudes getSolicitudes(){
        return getRetrofit().create(Solicitudes.class);
    }


}
