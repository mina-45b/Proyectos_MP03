package Proyectos_MP03.UF4.CarretCompra.src;

public class CarretCompra {
    private Producte[] productes;
    private int tamanyMaxim;

    public CarretCompra(int tamanyMaxim) {
        this.tamanyMaxim = tamanyMaxim;
        productes = new Producte[tamanyMaxim];
    }

    public void afegirProducte(Producte producte) {
        for (int i = 0; i < productes.length; i++) {
            if (productes[i] == null) {
                productes[i] = producte;
                System.out.println("Producte introduït al carret");
                return;
            }
        }
        System.out.println("El carret està ple, no es pot afegir més productes");
    }

    public Producte obtenirProducte(int indexi) {
        if (indexi >= 0 && indexi < productes.length) {
            return productes[indexi];
        }
        return null;
    }

    public int obternirQuantitatProductes() {
        int quantitat = 0;
        for (Producte producte : productes) {
            if (producte != null) {
                quantitat++;
            }
        }
        return quantitat;
    }

    public void buidarCarret() {
        for (int i = 0; i < productes.length; i++){
            productes[i] = null;
        }
    }

}
