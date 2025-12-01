package com.aldocurso.screenmatch2;

import com.aldocurso.screenmatch2.model.DatosSerie;
import com.aldocurso.screenmatch2.service.ConsumoAPI;
import com.aldocurso.screenmatch2.service.ConvertirDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Screenmatch2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Screenmatch2Application.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n");
        System.out.println("-----MI PRIMER PROYECTO SRPING SIN WEB-----");
        System.out.println("\n");

        var consumoApi = new ConsumoAPI();
        var json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&&apikey=eca75ade");

        System.out.println("Json crudo:");
        System.out.println(json);
        System.out.println("\n");

        ConvertirDatos conversor = new ConvertirDatos();
        DatosSerie datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println("Json convetido a un objeto de java:");
        System.out.println(datos);
    }
}
