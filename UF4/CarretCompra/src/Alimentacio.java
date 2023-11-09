package Proyectos_MP03.UF4.CarretCompra.src;

public class Alimentacio extends Producte {
    private String dataCaducitat;


    public Alimentacio(String nom, double preu, String codiBarres, String dataCaducitat) {
        super(nom, preu, codiBarres);
        this.dataCaducitat = dataCaducitat;
    }

    public String getDataCaducitat() {
        return dataCaducitat;
    }

    public void setDataCaducitat(String dataCaducitat) {
        this.dataCaducitat = dataCaducitat;
    }

    @Override
    public double getPreu() {
        return super.getPreu();
    }
}
