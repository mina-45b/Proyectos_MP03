package Proyectos_MP03.UF5.Lambdas_Streams;

import java.time.LocalDate;

public class Alumne implements Comparable<Alumne>{
    private LocalDate dataNaixament;
    private String nom;


    public Alumne(String nom) {
        this.nom = nom;
    }

    public LocalDate getDataNaixament() {
        return dataNaixament;
    }

    public void setDataNaixament(LocalDate dataNaixament) {
        this.dataNaixament = dataNaixament;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Alumne{" +
                "dataNaixament=" + dataNaixament +
                ", nom='" + nom + '\'' +
                '}';
    }

    @Override
    public int compareTo(Alumne o) {
        if(dataNaixament.isBefore(o.getDataNaixament())) return -1;
        else if(dataNaixament.isAfter(o.getDataNaixament())) return 1;
        else return 0;
    }



}