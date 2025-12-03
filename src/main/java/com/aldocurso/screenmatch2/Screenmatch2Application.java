package com.aldocurso.screenmatch2;

import com.aldocurso.screenmatch2.model.DatosEpisodio;
import com.aldocurso.screenmatch2.model.DatosSerie;
import com.aldocurso.screenmatch2.model.DatosTemporada;
import com.aldocurso.screenmatch2.principal.EjemploStreams;
import com.aldocurso.screenmatch2.principal.Principal;
import com.aldocurso.screenmatch2.service.ConsumoAPI;
import com.aldocurso.screenmatch2.service.ConvertirDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

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

        var ejemploStreams = new EjemploStreams();
        ejemploStreams.mostrarEjemplo();

//        Principal principal = new Principal();
//        principal.mostrarElMenu();

//        var consumoApi = new ConsumoAPI();
//        var json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&&apikey=eca75ade");
//
//        //MOSTRANDO JASON CRUDO
//        System.out.println("Json crudo:");
//        System.out.println(json);
//        System.out.println("\n");
//
//        //MOSTRANDO UN OBJETO TIPO SERIE
//        System.out.println("--- SERIE ---");
//        ConvertirDatos conversor = new ConvertirDatos();
//        var datosSerie = conversor.obtenerDatos(json, DatosSerie.class);
//        System.out.println("Json convetido a un objeto tipo 'Serie':");
//        System.out.println(datosSerie);
//        System.out.println("\n");
//
//        //MOSTRANDO UN OBJETO TIPO EPISODIO
//        System.out.println("--- EPISODIO ---");
//        json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&Season=1&Episode=1&apikey=eca75ade");
//        var datosEpisodio = conversor.obtenerDatos(json, DatosEpisodio.class);
//        System.out.println("Json convetido a un objeto tipo 'Episodio':");
//        System.out.println(datosEpisodio);
//        System.out.println("\n");
//
//        //MOSTRAR TODAS LAS TEMPORADAS DE LA SERIE CON SUS DATOS
//        System.out.println("--- TEMPORADAS ---");
//        List<DatosTemporada> temporadas = new ArrayList<>();
//        for (int i = 1; i <= datosSerie.totalTemporadas(); i++) {
//            json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&Season=" + i + "&&apikey=eca75ade");
//            var datosTemporada = conversor.obtenerDatos(json, DatosTemporada.class);
//            temporadas.add(datosTemporada);
//        }
//        System.out.println("Json convetido a un objeto tipo 'Temporada':");
//        temporadas.forEach(System.out::println);
    }
}
