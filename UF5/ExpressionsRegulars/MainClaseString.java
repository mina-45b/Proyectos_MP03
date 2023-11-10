package Proyectos_MP03.UF5.ExpressionsRegulars;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainClaseString {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("santako.txt"));


        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            String paraNoel = "*<]:-DOo";
            String rens = ">:o)";
            String follets = "<]:-D";

            int numParaNoel = 0;
            int numsRens = 0;
            int numsFollets = 0;

            int indexParaNoel = line.indexOf(paraNoel);
            while (indexParaNoel != -1) {
                numParaNoel++;
                numsFollets--;
                indexParaNoel = line.indexOf(paraNoel, indexParaNoel + 1);
            }

            if (line.contains(rens)) {
                numsRens++;
            }

            if (line.contains(follets)) {
                numsFollets++;
            }

            String resultat = "";

            if (numParaNoel > 0){
                resultat += "Pare Noel ("+numParaNoel+") ";
            }

            if (numsRens > 0) {
                resultat += "Ren ("+numsRens+") ";
            }

            if (numsFollets > 0) {
                resultat += "Follet ("+numsFollets+") ";
            }

            if (!resultat.isEmpty()){
                System.out.println(resultat);
            } else {
                System.out.println();
            }

        }

    }
}
