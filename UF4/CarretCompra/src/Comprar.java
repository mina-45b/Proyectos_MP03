package Proyectos_MP03.UF4.CarretCompra.src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Comprar {
    private Menu menu;
    private CarretCompra carretCompra;


    public Comprar() {
        menu = new Menu(this);
        carretCompra = new CarretCompra(100);
    }

    public void inici(){
        menu.mostrarMenuPrincipal();
    }

    public void introduirProducte() {
        int opcion;
        Producte producte;
        Alimentacio producte1;
        Textil producte2;
        Electronica producte3;

        opcion = menu.menuIntruirProducte();

        switch (opcion) {
            case 1:
                LocalDate dataActual = LocalDate.now();

                producte =  menu.menuProducte();
                String caducitat = menu.menuAlimentacio();

                LocalDate dataCaducitat = LocalDate.parse(caducitat, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                long diesDiferencia = ChronoUnit.DAYS.between(dataCaducitat, dataActual);

                double preuAlimentacio = producte.getPreu() - producte.getPreu() * (1.0 / (diesDiferencia + 1)) + producte.getPreu() * 0.1;

                producte1 = new Alimentacio(producte.getNom(), preuAlimentacio, producte.getCodiBarres(), caducitat);

                carretCompra.afegirProducte(producte1);
                break;
            case 2:
                producte = menu.menuProducte();
                String composicio = menu.menuTextil();

                producte2 = new Textil(producte.getNom(), producte.getPreu(), producte.getCodiBarres(), composicio);

                carretCompra.afegirProducte(producte2);
                break;
            case 3:
                producte = menu.menuProducte();

                int garantia = menu.menuElectronica();

                double preuTextil = producte.getPreu() + producte.getPreu() * ((double) garantia / 365) * 0.1;

                producte3 = new Electronica(producte.getNom(), preuTextil, producte.getCodiBarres(), garantia);

                carretCompra.afegirProducte(producte3);
                break;
        }
    }

    public void passarPerCaixa() {
        menu.tiquet();
        carretCompra.buidarCarret();
    }

    public void mostarCarroCompra() {
        menu.listatCompra();
    }

    public String obtenirFormatData() {

        LocalDate dataActual = LocalDate.now();

        DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMMM yyyy").withLocale(new Locale("cat"));

        return dataActual.format(format);
    }

    public void obtenirFormatProducte() {
        int quantitatProductes = carretCompra.obternirQuantitatProductes();

        Map<Producte, Integer> comptatgeProductes = new HashMap<>();

        for (int i = 0; i < quantitatProductes; i++){
            Producte producte = carretCompra.obtenirProducte(i);
            comptatgeProductes.put(producte, comptatgeProductes.getOrDefault(producte, 0)+1);
        }

        for (Map.Entry<Producte, Integer> entry : comptatgeProductes.entrySet()){

            Producte producte = entry.getKey();
            int quantitat = entry.getValue();
            double preu = producte.getPreu();

            System.out.printf("%3d %-20s %7.2f %7.2f\n", quantitat, producte.getNom(), preu, preu * quantitat);
        }
    }

    public double obtenirTotalPagar() {
        int quantitatProductes = carretCompra.obternirQuantitatProductes();
        double totalPagar = 0.0;

        for (int i = 0; i < quantitatProductes; i++) {
            Producte producte = carretCompra.obtenirProducte(i);
            double preu = producte.getPreu();
            int quantitat = 1;

            double importProducte = preu * quantitat;
            totalPagar += importProducte;
        }

        return totalPagar;
    }

    public void listatProductes() {
        int quantitatProductes = carretCompra.obternirQuantitatProductes();

        Map<Producte, Integer> comtatgeProductes = new HashMap<>();

        for (int i = 0; i < quantitatProductes; i++) {
            Producte producte = carretCompra.obtenirProducte(i);

            boolean producteTrobat = false;

            for (Producte key : comtatgeProductes.keySet()) {
                if (key.getCodiBarres().equals(producte.getCodiBarres())) {
                    comtatgeProductes.put(key, comtatgeProductes.get(key) + 1);
                    producteTrobat = true;
                    break;
                }
            }

            if (!producteTrobat) {
                comtatgeProductes.put(producte, 1);
            }
        }

        for (Map.Entry<Producte, Integer> entry : comtatgeProductes.entrySet()) {
            Producte producte = entry.getKey();
            int quantitat = entry.getValue();

            System.out.printf("%3d    %-20s\n", quantitat, producte.getNom());
        }
    }
}
