package Proyectos_MP03.UF4.CarretCompra.src;

public class Textil extends Producte {

    private String composicioTextil;

    public Textil(String nom, double preu, String codiBarres, String composicioTextil) {
        super(nom, preu, codiBarres);
        this.composicioTextil = composicioTextil;
    }

    public String getComposicioTextil() {
        return composicioTextil;
    }

    public void setComposicioTextil(String composicioTextil) {
        this.composicioTextil = composicioTextil;
    }


}
