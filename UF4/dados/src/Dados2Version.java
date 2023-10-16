import java.util.Scanner;

public class Dados2Version {
    public static void main(String[] args) {

        int[] veces = new int[12];

        Scanner sc = new Scanner(System.in);

        System.out.println("¿Cuántas veces quieres lanzar el dado?");
        int repeticiones = sc.nextInt();

        int dado1;
        int dado2;

        for (int i = 0; i < repeticiones; i++) {
            dado1 = (int) (Math.random() * 6) +1;
            dado2 = (int) (Math.random() * 6) +1;

            veces[dado1+dado2]++;
        }

        for (int i = 2; i <= 12; i++) {

            System.out.println(i + "--> " + veces[i-1]);

        }
    }
}
