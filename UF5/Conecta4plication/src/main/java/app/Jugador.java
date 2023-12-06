package app;

import javafx.beans.property.StringProperty;

public class Jugador {
    private String nombre;
    private int victorias;
    private int derrotas;
    private int empates;
    private int partidasJugadas;

    public Jugador(String nombre) {
        this.nombre = nombre;
        victorias = 0;
        derrotas = 0;
        empates = 0;
        partidasJugadas = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public void setPartidasJugadas(int partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }

}
