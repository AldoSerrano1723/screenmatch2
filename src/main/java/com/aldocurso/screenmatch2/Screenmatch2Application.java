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

        Principal principal = new Principal();
        principal.mostrarElMenu();

//        var ejemploStreams = new EjemploStreams();
//        ejemploStreams.mostrarEjemplo();

    }
}
