package Proyectos_MP03.UF4.CarretCompra.src;

import java.util.*;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private Comprar comprar;

    public Menu(Comprar comprar) {
        this.comprar = comprar;
    }

    public void mostrarMenuPrincipal() {
        menuPrincipal();
    }

    public void menuPrincipal() {
        int opcio;
        do {
            System.out.println("Benvingut al Teu Supermercat");
            System.out.println("1. Introduir producte");
            System.out.println("2. Passar per caixa");
            System.out.println("3. Mostrar carro de la compra");
            System.out.println("0. Sortir");
            opcio = scanner.nextInt();

            switch (opcio) {
                case 1:
                    comprar.introduirProducte();
                    break;
                case 2:
                    comprar.passarPerCaixa();
                    break;
                case 3:
                    comprar.mostarCarroCompra();
                    break;
                case 0:
                    System.out.println("Gràcies per visitar-nos");
                    break;
                default:
                    System.out.println("Heu de triar una opció entre 0 i 3");
            }
        } while (opcio != 0);
    }

    public int menuIntruirProducte() {
        int opcio;
        do{
            System.out.println("Quin tipus de producte vols afegir?");
            System.out.println("1. Alimentació");
            System.out.println("2. Tèxtil");
            System.out.println("3. Electrònica");
            System.out.println("0. Tornar");
            opcio = scanner.nextInt();
            scanner.nextLine();

            if (opcio < 0 || opcio > 3) {
                System.out.println("Heu de triar una opció entre 0 i 3");
            }
        } while (opcio <  0 || opcio > 3);
        return opcio;
    }

    public Producte menuProducte() {
        System.out.println("Introduïu el nom del producte");
        String nomProducte = scanner.nextLine();
        System.out.println("Introduïu el preu del producte");
        double preuProducte = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Introduïu el codi de barres del producte");
        String codiBarresProducte = scanner.nextLine();

        return new Producte(nomProducte, preuProducte, codiBarresProducte);
    }

    public String menuAlimentacio() {
            System.out.println("Introduïu la data de caducitat del producte (yyyy-MM-dd)");
            String caducitatProducte = scanner.nextLine();

            return caducitatProducte;
    }

    public String menuTextil() {
        System.out.println("Introduïu la composició tèxtil del producte");
        String composicioProducte = scanner.nextLine();

        return composicioProducte;
    }

    public int menuElectronica() {
        System.out.println("Introduïu els dies de garantia del producte");
        int garantiaProducte = scanner.nextInt();

        return garantiaProducte;
    }

    public void tiquet() {
        String nomSupermercat = "Teu Supermercat";
        String dataActual = comprar.obtenirFormatData();
        double total = comprar.obtenirTotalPagar();

        System.out.println(nomSupermercat);
        System.out.println("-----------------------------");
        System.out.println(" Fecha: " + dataActual);
        System.out.println("-----------------------------");
        System.out.printf("%3s %-20s  %7s %8s\n", "Num", "Descripció", "P. Uni", "Import");
        comprar.obtenirFormatProducte();
        System.out.printf("%25s %7.2f\n", "Total", total);
    }

    public void listatCompra() {
        System.out.println("Listat De Productes");
        System.out.println("-----------------------------");
        System.out.printf("%6s %-20s\n", "Quant.", "Descripció");
        comprar.listatProductes();
    }


}
