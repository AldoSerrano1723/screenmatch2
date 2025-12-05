package com.aldocurso.screenmatch2.principal;

import com.aldocurso.screenmatch2.model.DatosEpisodio;
import com.aldocurso.screenmatch2.model.DatosSerie;
import com.aldocurso.screenmatch2.model.DatosTemporada;
import com.aldocurso.screenmatch2.model.Episodio;
import com.aldocurso.screenmatch2.service.ConsumoAPI;
import com.aldocurso.screenmatch2.service.ConvertirDatos;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
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
        System.out.println("--- TODOS LOS EPISODIOS SIN INFORMACION ---");
        List<DatosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= datosSerie.totalTemporadas(); i++) {
            json = consumoApi.obtenerDatos(URL_BASE + nombreSerie + "&Season=" + i + API_KEY);
            var datosTemporada = conversor.obtenerDatos(json, DatosTemporada.class);
            temporadas.add(datosTemporada);
        }
//        System.out.println("Json convetido a un objeto tipo 'Temporada':");
//        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
//        System.out.println("\n");

        //CREAMOS UNA LISTA DE TODOS LOS EPISODIOS DE TODAS LAS TEMPORADAS
        System.out.println("--- CANTIDAD DE EPISODIOS DE LA SERIE---");
        List<DatosEpisodio> datosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println(datosEpisodios.size());
        System.out.println("\n");

        //AHORA UTILIZAMOS ESA LISTA DE EPISODIOS PARA SACAR LOS 5 MEJORES EPISODIOS DE TODA LA SERIE
        System.out.println("--- 5 MEJORES EPISODIOS DE LA SERIE ---");
        datosEpisodios.stream()
                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
                .limit(5)
                .forEach(System.out::println);
        System.out.println("\n");

        //CREANDO UN LISTA CON OBJETOS DE TIPO EPISODIO, QUE TIENE LA TEMPORADA INCLUIDA
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(e -> new Episodio(t.numeroTemporada(), e)))
                .collect(Collectors.toList());

//        //IMPRIME LA LISTA DE EPISODIOS
//        System.out.println("--- TODOS LOS EPISODIOS DE LA SERIE CON INFORAMCION ---");
//        episodios.forEach(System.out::println);
//        System.out.println("\n");
//
//        //HACER UNA BUSQUEDA DE EPISODIOS POR AÑO
//        System.out.println("--- EPISODIOS APARTIR DE UNA FECHA ESPECIFICADA ---");
//        System.out.println("Indica el año a partir del cual deseas ver los episodios");
//        var fecha = sc.nextInt();
//        sc.nextLine();
//        System.out.println("");
//
//        LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodios.stream()
//                .filter(e -> e.getFechaLanzamiento() != null)
//                .filter(e -> e.getFechaLanzamiento().isAfter(fechaBusqueda))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getTemporada() +
//                                "/ Episodio: " + e.getTitulo() +
//                                "/ Fecha de lanzamiento: " + e.getFechaLanzamiento().format(dtf)
//                ));

//        //BUSCANDO UN EPISODIO POR NOMBRE Y MOSTRANDO EL PRIMERO QUE COINCIDA
//        System.out.println("Escribe el nombre del episodio que quieres ver:");
//        var pedazoTitulo = sc.nextLine();
//
//        Optional<Episodio> episodioBuscado = episodios.stream()
//                .filter(e -> e.getTitulo().toUpperCase().contains(pedazoTitulo.toUpperCase()))
//                .findFirst();
//
//        if (episodioBuscado.isPresent()){
//            System.out.println("Episodio encontrado");
//            System.out.println("Los datos son: " + episodioBuscado.get());
//        }else {
//            System.out.println("No encontramos el episodio");
//        }

        System.out.println("--- EVALUACION POR TEMPORADAS ---");
        Map<Integer, Double> evaluacionesPorTemporada = episodios.stream()
                .filter(e -> e.getEvaluacion() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getEvaluacion)));

        System.out.println(evaluacionesPorTemporada);

    }
}
