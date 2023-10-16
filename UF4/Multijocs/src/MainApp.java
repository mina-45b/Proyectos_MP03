import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        int opciones;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("1.- Dados");
            System.out.println("2.- Parchis");
            System.out.println("3.- tic-tac-toe");
            System.out.println("0.- Salir");

            opciones = sc.nextInt();

            switch (opciones) {
                case 1:
                    JocDaus jocDaus = new JocDaus();
                    jocDaus.start();
                    break;
                case 2:
                    System.out.println("Iniciando la partida");
                    break;
                case 3:
                    System.out.println("Iniciando la partida");
                    break;
                case 0:
                    System.out.println("Gracias por juegar");
                    break;
                default:
                    System.out.println("Debes escoger una opción entre 0 y 3");
            }
        } while (opciones != 0);



    }
}