package com.aldocurso.screenmatch2.principal;

import java.util.Arrays;
import java.util.List;

public class EjemploStreams {

    public void mostrarEjemplo (){

        List<String> nombres = Arrays.asList("aldo", "erik", "coco", "bertin", "juan", "marian", "pepe");

        nombres.stream()
                .sorted()
                .limit(4)
                .map(n -> n.toUpperCase())
                .forEach(System.out::println);

    }

}
