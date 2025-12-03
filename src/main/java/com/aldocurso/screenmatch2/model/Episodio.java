package com.aldocurso.screenmatch2.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {

    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double evaluacion;
    private LocalDate fechaLanzamiento;

    public Episodio(Integer numero, DatosEpisodio episodio) {
        this.temporada = numero;
        this.titulo = episodio.titulo();
        this.numeroEpisodio = episodio.numeroEpisodio();

        // 1. Manejo de Evaluación (String -> Double)
        try {
            this.evaluacion = Double.valueOf(episodio.evaluacion());
        } catch (NumberFormatException e) {
            this.evaluacion = 0.0; // Si falla (es "N/A"), ponemos 0.0
        }

        // 2. Manejo de Fecha (String -> LocalDate)
        try {
            this.fechaLanzamiento = LocalDate.parse(episodio.fechaDeLanzamiento());
        } catch (DateTimeParseException e) {
            this.fechaLanzamiento = null; // Si falla (es "N/A"), ponemos null
        }
    }


    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    @Override
    public String toString() {
        return
                "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", evaluacion=" + evaluacion +
                ", fechaLanzamiento=" + fechaLanzamiento;
    }
}
