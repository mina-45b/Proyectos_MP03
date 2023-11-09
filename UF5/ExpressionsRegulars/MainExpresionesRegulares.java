package Proyectos_MP03.UF5.ExpressionsRegulars;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainExpresionesRegulares {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("santako.txt"));

        Pattern paraNoel = Pattern.compile("\\*<]:-DOo");
        Pattern rens = Pattern.compile(">:o\\)");
        Pattern follets = Pattern.compile("<]:-D");

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            Matcher matcherNoel = paraNoel.matcher(line);
            Matcher matcherRens = rens.matcher(line);
            Matcher matcherFollets = follets.matcher(line);

            int compteNoels = 0;
            int compteRens = 0;
            int compteFollets = 0;

            while (matcherNoel.find()){
                compteNoels++;
                compteFollets--;
            }

            while (matcherRens.find()) {
                compteRens++;
            }

            while (matcherFollets.find()){
                compteFollets++;
            }

            String resultat = "";

            if (compteNoels > 0){
                resultat += "Pare Noel ("+compteNoels+") ";
            }

            if (compteRens > 0) {
                resultat += "Ren ("+compteRens+") ";
            }

            if (compteFollets > 0) {
                resultat += "Follet ("+compteFollets+") ";
            }

            if (!resultat.isEmpty()){
                System.out.println(resultat);
            } else {
                System.out.println();
            }

        }
    }
}
