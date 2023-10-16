import java.util.Scanner;

public class JocDaus {
    private Dau dau1;
     private Dau dau2;
   private Dau dau3;

   private int ganadas;

    public JocDaus() {
        this.dau1 =  new Dau();
        this.dau2 = new Dau();
        this.dau3 = new Dau();
        ganadas = 0;
    }

    public void start() {

        menu();
    }

    public void menu (){
        Scanner sc = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("1. Tirar dados");
            System.out.println("0. Volver al menú principal");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    jugar();
                    break;
                case 0:
                    System.out.printf("Has ganado %d partidas%n", ganadas);
                    break;
            }
        } while (opcion != 0);
    }

    public void jugar() {
        dau1.tirar();
        dau2.tirar();
        dau3.tirar();

        System.out.println(dau1);
        System.out.println(dau2);
        System.out.println(dau3);

        if (dau1.equals(dau2) && dau2.equals(dau3)) {
            ganadas++;
        }
    }
}
