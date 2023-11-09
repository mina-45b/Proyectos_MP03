package Proyectos_MP03.UF4.CarretCompra.src;

import java.util.Objects;

public class Producte {
    private double preu;
    private String nom;
    private String codiBarres;

    public Producte(String nom, double preu, String codiBarres) {
        this.preu = preu;
        this.nom = nom;
        this.codiBarres = codiBarres;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodiBarres() {
        return codiBarres;
    }

    public void setCodiBarres(String codiBarres) {
        this.codiBarres = codiBarres;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Producte producte = (Producte) object;
        return Double.compare(preu, producte.preu) == 0 && Objects.equals(codiBarres, producte.codiBarres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(preu, codiBarres);
    }
}
