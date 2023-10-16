import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("¿Cuántas veces quieres lanzar el dado?");

        int repeticiones = sc.nextInt();

        int[] veces = new int[11];

        int resultado;
        for (int i = 0; i < repeticiones; i++) {

            resultado = (int) (Math.random() * 6 + 1 + Math.random() * 6 + 1);
            switch (resultado){
                case 2: veces[0]++; break;
                case 3: veces[1]++; break;
                case 4: veces[2]++; break;
                case 5: veces[3]++; break;
                case 6: veces[4]++; break;
                case 7: veces[5]++; break;
                case 8: veces[6]++; break;
                case 9: veces[7]++; break;
                case 10: veces[8]++; break;
                case 11: veces[9]++; break;
                case 12: veces[10]++; break;
            }

        }

        for (int i = 1; i < veces.length ; i++) {
            System.out.println(i+1 + "---> " + veces[i-1] + " veces");
        }





        
    }
}