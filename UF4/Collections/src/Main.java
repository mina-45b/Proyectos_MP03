package Proyectos_MP03.UF4.Collections.src;

import java.text.DecimalFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Llapis> Caixa1 = new ArrayList<>();
        List<Llapis> Caixa2 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int color = (int) (Math.random() * 8);
            Caixa1.add(new Llapis(color, 0.1f));
        }

        for (int i = 0; i < 10; i++) {
            int color = (int) (Math.random() * 8);
            float gruix = (float) (Math.random() * 2.9f) + 0.1f;
            Caixa2.add(new Llapis(color, gruix));
        }

        System.out.println("  LLapis caixa 1");
        Iterator<Llapis> iterador1 = Caixa1.iterator();
        while (iterador1.hasNext()) {
            Llapis llapis = iterador1.next();
            System.out.println(llapis);
        }

        System.out.println("\n  LLapis caixa 2");
        Caixa2.forEach(System.out::println);

        List<Llapis> Caixas = new ArrayList<>();
        Caixas.addAll(Caixa1);
        Caixas.addAll(Caixa2);

// 6. Ordena els llapissos per color del més gran al més petit
        Collections.sort(Caixas);

        System.out.println("\n  Caixes ordenades per color");
        for (Llapis llapis : Caixas) {
            System.out.println(llapis);
        }

 // 7. Ordena els llapissos per gruix amb una classe anònima i un Comparator
        Collections.sort(Caixas, new Comparator<Llapis>() {
            @Override
            public int compare(Llapis llapis1, Llapis llapis2) {
                return Float.compare(llapis1.getGruix(), llapis2.getGruix());
            }
        });

        System.out.println("\n  Caixes ordenades per gruix");
        for (Llapis llapis : Caixas) {
            System.out.println(llapis);
        }

        LinkedList<Llapis> lista = new LinkedList<>();
        lista.addAll(Caixa2);
        lista.addAll(Caixa1);

        System.out.println("\n  LinkedList de llapis");
        Iterator<Llapis> iterador2 = lista.iterator();
        while (iterador2.hasNext()) {
            Llapis llapis = iterador2.next();
            System.out.println(llapis);
        }

        List<Llapis> Caixa3 = new ArrayList<>();
        for (Llapis llapis : lista) {
            if (!Caixa3.contains(llapis)) {
                Caixa3.add(llapis);
            }
        }

        Set<Llapis> caixa = new HashSet<>(lista);
        System.out.println("\n  Llapis de LinkedList sense repeticions amb SET");
        for (Llapis llapis : caixa){
            System.out.println(llapis);
        }


        System.out.println("\n  Llapis de LinkedList sense repeticions");
        for (Llapis llapis : Caixa3) {
            System.out.println(llapis);
        }



        Map<Integer,String> map_colors = new HashMap<>();
        map_colors.put(0,"Negre");
        map_colors.put(1,"Vermell");
        map_colors.put(2,"Groc");
        map_colors.put(3,"Verd");
        map_colors.put(4,"Verd");
        map_colors.put(3,"Blanc");

        /* 1. Explica què passa amb el color verd i amb la clau 3. Perquè?
        * La clave se repite también en el color Blanco, por tanto se sobreescribirá el color verde
        */

        System.out.println("\n  Mapa de colors: valor i clau");
        for (Map.Entry<Integer, String> entry : map_colors.entrySet()) {
            System.out.println("Clau: " + entry.getKey() + ", Valor: " + entry.getValue());
        }

        System.out.println("\n  Mapa de colors: valor");
        for (Map.Entry<Integer, String> entry : map_colors.entrySet()) {
            System.out.println("Valor: " + entry.getValue());
        }

        Dau dau1 = new Dau();
        Dau dau2 = new Dau();

        List<Integer> resultatDaus1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int jugar = dau1.tirar() + dau2.tirar();
            resultatDaus1.add(jugar);
        }

        System.out.println("\n  Llançament de dos daus amb resultats: Lis i frecuency");
        for (int i = 2; i < 13; i++) {
            int freq = Collections.frequency(resultatDaus1, i);
            System.out.println(i + " ----> " + freq + " veces ");
        }

        Map<Integer, Integer> resultatDaus2 = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            int jugar = dau1.tirar() + dau2.tirar();
            resultatDaus2.put(i, jugar);
        }

        System.out.println("\n  Lançament de dos daus amb resultats: Map");
        for (int i = 2; i < 13; i++) {
            int freq = 0;
            for (int resultado : resultatDaus2.values()) {
                if (resultado == i) {
                    freq++;
                }
            }
            System.out.println(i + " ----> " + freq + " veces ");
        }

       List<Alumne> listaAlumnes = new ArrayList<>();

        Alumne alumne1 = new Alumne("Maria", Alumne.Carrec.SENSE_CARREC, "0001");
        Alumne alumne2 = new Alumne("José", Alumne.Carrec.DELEGAT, "0002");
        Alumne alumne3 = new Alumne("Alma", Alumne.Carrec.SUBDELEGAT, "0003");

        listaAlumnes.add(alumne1);
        listaAlumnes.add(alumne2);
        listaAlumnes.add(alumne3);

        UF mp6UF1 = new UF("006001", "Persistencia en ficheros");
        UF mp6UF2 = new UF("006002", "Persistencia en BDR-BDOR-BDOO");
        UF mp6UF3 = new UF("006003", "Persistencia en BD nativas");

        for (Alumne alumne : listaAlumnes) {
            double notaUF1 = Math.random() * 10.0;
            double notaUF2 = Math.random() * 10.0;
            double notaUF3 = Math.random() * 10.0;

            if (notaUF1 >= 0.0 && notaUF1 < 5.0){
                alumne.addNota(mp6UF1, Qualifier.NO_SATISFACTORI);
            } else if (notaUF1 >= 5.0 && notaUF1 < 8.0) {
                alumne.addNota(mp6UF1, Qualifier.SATISFACTORI);
            } else if (notaUF1 >= 8.0 && notaUF1 < 10.0) {
                alumne.addNota(mp6UF1, Qualifier.NOTABLE);
            } else {
                alumne.addNota(mp6UF1, Qualifier.EXCELLENT);
            }

            if (notaUF2  >= 0.0 && notaUF2 < 5.0) {
                alumne.addNota(mp6UF2, Qualifier.NO_SATISFACTORI);
            } else if (notaUF2 >= 5.0 && notaUF2 < 8.0) {
                alumne.addNota(mp6UF2, Qualifier.SATISFACTORI);
            } else if (notaUF2 >= 8.0 && notaUF2 < 10.0) {
                alumne.addNota(mp6UF2, Qualifier.NOTABLE);
            } else {
                alumne.addNota(mp6UF2, Qualifier.EXCELLENT);
            }

            if (notaUF3  >= 0.0 && notaUF3 < 5.0) {
                alumne.addNota(mp6UF3, Qualifier.NO_SATISFACTORI);
            } else if (notaUF3 >= 5.0 && notaUF3 < 8.0) {
                alumne.addNota(mp6UF3, Qualifier.SATISFACTORI);
            } else if (notaUF3 >= 8.0 && notaUF3 < 10.0) {
                alumne.addNota(mp6UF3, Qualifier.NOTABLE);
            } else {
                alumne.addNota(mp6UF3, Qualifier.EXCELLENT);
            }

        }

        System.out.println("\n  Llistat de notes dels alumnes");
        for (Alumne alumne : listaAlumnes) {
            System.out.println("Alumne: " + alumne.getNom());
            System.out.println("-------------");
            alumne.getNotes().forEach((k,v) -> System.out.printf("%s -> %s%n",k,v));
            System.out.println("-------------");
        }

        System.out.println("\n  Llistat de alumnes en ordre alfabètic amb la seva nota mitja");
        Map<String, Float> notesMitjanes = new TreeMap<>();
        DecimalFormat decimalFormat = new DecimalFormat("#.#");

        for (Alumne alumne : listaAlumnes) {
            float notaMitjana = 0;
            for (Map.Entry entry : alumne.getNotes().entrySet()) {
                Qualifier qf = (Qualifier) entry.getValue();
                notaMitjana += qf.getValor();
            }
            notaMitjana = notaMitjana / alumne.getNotes().size();
            notesMitjanes.put(alumne.getNom(), notaMitjana);
        }

        notesMitjanes.forEach((k, v) -> System.out.println("Nota mitja de " + k + " es " + decimalFormat.format(v)));


    }
}