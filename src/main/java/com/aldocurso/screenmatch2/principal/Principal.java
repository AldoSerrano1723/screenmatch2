package com.aldocurso.screenmatch2.principal;

import com.aldocurso.screenmatch2.model.DatosEpisodio;
import com.aldocurso.screenmatch2.model.DatosSerie;
import com.aldocurso.screenmatch2.model.DatosTemporada;
import com.aldocurso.screenmatch2.service.ConsumoAPI;
import com.aldocurso.screenmatch2.service.ConvertirDatos;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvertirDatos conversor = new ConvertirDatos();

    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=eca75ade";

    public void mostrarElMenu(){

        //OBTENIENDO LA SERIE A BUSCAR
        System.out.println("Ingrese el nombre de la seire que desea buscar:");
        var entradaUsuario = sc.nextLine();
        var nombreSerie = URLEncoder.encode(entradaUsuario, StandardCharsets.UTF_8);

        //HACIENDO LA URL PARA LA BUSQUEDA EN LA API
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie + API_KEY);

        //CONVIRTIENDO LOS DATOS A UNA SERIE Y MOSTRANDOLOS
        System.out.println("--- SERIE ---");
        var datosSerie = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datosSerie);
        System.out.println("\n");

        //MOSTRAR TODAS LAS TEMPORADAS DE LA SERIE CON SUS DATOS
        System.out.println("--- TEMPORADAS ---");
        List<DatosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= datosSerie.totalTemporadas(); i++) {
            json = consumoApi.obtenerDatos(URL_BASE + nombreSerie + "&Season=" + i + API_KEY);
            var datosTemporada = conversor.obtenerDatos(json, DatosTemporada.class);
            temporadas.add(datosTemporada);
        }
        System.out.println("Json convetido a un objeto tipo 'Temporada':");
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
        System.out.println("\n");

        //CREAMOS UNA LISTA DE TODOS LOS EPISODIOS DE TODAS LAS TEMPORADAS
        System.out.println("lista de episodios de todas las temporadas");
        List<DatosEpisodio> datosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println(datosEpisodios.size());

        //AHORA UTILIZAMOS ESA LISTA DE EPISODIOS PARA SACAR LOS 5 MEJORES EPISODIOS DE TODA LA SERIE
        System.out.println("--- 5 MEJORES EPISODIOS DE LA SERIE ---");
        datosEpisodios.stream()
                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
                .limit(5)
                .forEach(System.out::println);

    }
}
