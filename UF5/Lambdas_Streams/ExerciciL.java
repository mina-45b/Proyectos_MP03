package Proyectos_MP03.UF5.Lambdas_Streams;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ExerciciL {

    public static void main(String[] args) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyy");
        Persona p1 = new Persona("Arya", Persona.Genere.DONA, LocalDate.parse("25/12/2002",format) );
        Persona p2 = new Persona("Tyrion", Persona.Genere.HOME, LocalDate.parse("12/10/1980",format));
        Persona p3 = new Persona("Cersei", Persona.Genere.DONA, LocalDate.parse("10/01/1984",format));
        Persona p4 = new Persona("Eddard", Persona.Genere.HOME, LocalDate.parse("24/04/1974",format));
        Persona p5 = new Persona("Sansa", Persona.Genere.DONA, LocalDate.parse("24/04/1992",format));
        Persona p6 = new Persona("Jaime", Persona.Genere.HOME, LocalDate.parse("24/04/1979",format));
        Persona p7 = new Persona("Khal", Persona.Genere.HOME, LocalDate.parse("10/08/1979",format));
        Persona p8 = new Persona("Daenerys", Persona.Genere.DONA, LocalDate.parse("12/11/1992",format));
        Persona p9 = new Persona("Davos", Persona.Genere.HOME, LocalDate.parse("12/11/1965",format));
        Persona p10 = new Persona("Jon Neu", Persona.Genere.HOME, LocalDate.parse("12/11/1986",format));
        Persona p11 = new Persona("Brienne", Persona.Genere.DONA, LocalDate.parse("12/11/1989",format));

        Persona[] lpers = {p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11};
        List<Persona> llistaPersones;
        llistaPersones = new ArrayList<>(Arrays.asList(lpers));
        Map<Integer,Integer> mapPersones = new HashMap<>();

        // 1 - Canviar a lambda
        /**
         * Collections.sort(llistaPersones, new Comparator<Persona>() {
         *             @Override
         *             public int compare(Persona o1, Persona o2) {
         *                 if(o1.getNom().charAt(0) >= o2.getNom().charAt(0)) return 1;
         *                 else return -1;
         *             }
         *         });
         */
        System.out.println("Exercici 1");
        Collections.sort(llistaPersones, (o1, o2) ->  {
                if(o1.getNom().charAt(0) >= o2.getNom().charAt(0)) {
                    return 1;
                } else {
                    return -1;
                }
        });

        // 2 - Canviar a Lambda
        /**
         *for(Persona p: llistaPersones) {
         *             System.out.println(p);
         *         }
         */
        System.out.println("\nExercici 2");
        llistaPersones.forEach(p -> System.out.println(p));


        // 3 - Canvia a classe anònima
        // ordenació alfabètica inversa del nom
        /**
         * llistaPersones.sort((o1,o2) -> o2.getNom().compareTo(o1.getNom()));
         */
        System.out.println("\nExercici 3");
        llistaPersones.sort(new Comparator<Persona>() {
            @Override
            public int compare(Persona o1, Persona o2) {
                return o2.getNom().compareTo(o1.getNom());
            }
        });


        // 4 - fes servir un reference method en comptes del for-loop
        /**for(Persona p: llistaPersones) {
            System.out.println(p);
        };*/
        System.out.println("\nExercici 4");
        llistaPersones.forEach(System.out::println);



        // 5 - Omplir map. Canviar el for-llop per un forEach amb lambda
        /**for(Persona per : llistaPersones) {
            mapPersones.put(per.getAge(),1);
        }*/
        System.out.println("\nExercici 5");
        llistaPersones.forEach(per -> mapPersones.put(per.getAge(),1));


        // 6 - Canvia el for-loop per un recorregut forEach amb lambda
        /**for(Map.Entry entry : mapPersones.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }*/
        System.out.println("\nExercici 6");
        mapPersones.forEach((key, value) -> System.out.println(key + ":" + value));

        /* 7 -
            Esbrina com s'utilitzen i perquè serveixen els mètodes de map següents
                map.putIfAbsent -> Sirve para copiar todas las asignaciones de un mapa a otro y no reemplar asignaciones de clave-valor existente.
                map.computeIfAbsent -> Si la clave se asocia a un valor intenta calcular su valor utilizando la función pasada por parámetro y añade el valor.
                map.computeIfPresent -> Si el valor de la clave existe y no es null, calcula un nuevo valor con el valor actual.

             afegeix al map la freqüència d'edat de les persones, de la llistaPersones

             **Si vols fes-ho primer sense els mètodes anomenats i sense lambdes
             i després amb els mètodes i amb lambdes**

             La sortida és aquesta:
                34 anys -> 1
                38 anys -> 1
                39 anys -> 2
                26 anys -> 2
                44 anys -> 1
                15 anys -> 1
         */
        System.out.println("\nExercici 7 sense metodes");
        Map<Integer,Integer> mapFrecuenciaEdatsPersones  = new HashMap<>();

        for (Persona per : llistaPersones) {
            int edad = per.getAge();
            mapFrecuenciaEdatsPersones.put(edad, mapFrecuenciaEdatsPersones.getOrDefault(edad, 0)+1);
        }

        for (Map.Entry<Integer, Integer> entry : mapFrecuenciaEdatsPersones.entrySet()) {
            System.out.println(entry.getKey() + " anys -> " + entry.getValue());
        }


        System.out.println("\nExercici 7 amb lambda");
        mapFrecuenciaEdatsPersones.clear();
        llistaPersones.forEach(per -> {
            int edad = per.getAge();
            mapFrecuenciaEdatsPersones.putIfAbsent(edad, 0);
            mapFrecuenciaEdatsPersones.computeIfAbsent(edad, k -> 0);
            mapFrecuenciaEdatsPersones.computeIfPresent(edad, (k, v) -> v + 1);
        });

        mapFrecuenciaEdatsPersones.forEach((edad, frecuencia) ->
                System.out.println(edad + " anys -> " + frecuencia)
        );

        // STREAMS

        // 8 - llistat de persones DONA amb lambda (stream)
        List<Persona> dones = llistaPersones.stream()
                .filter(persona -> Persona.Genere.DONA.equals(persona.getGenere())) //filtra las mujeres
                .collect(Collectors.toList()); //se almacena el resultado

        System.out.println("\nListat amb DONES");
        dones.forEach(persona -> System.out.println(persona.getNom() + ": " + persona.getGenere()));

        // 9 - Llistat dels dos HOMES més joves (stream)
        List<Persona> homesJoves = llistaPersones.stream()
                .filter(persona -> Persona.Genere.HOME.equals(persona.getGenere())) //filtra los hombres
                .sorted(Comparator.comparingInt(Persona::getAge)) //compara las edades
                .limit(2) //limita a los dos primeros
                .collect(Collectors.toList()); //llena la lista

        System.out.println("\nListat dels dos homes més joves");
        homesJoves.forEach(persona -> System.out.println(persona.getNom() + ": " + persona.getAge()));

        // 10- Esborrar (no filtrar o imprimir) del llistat les persones entre 30 i 40 anys (amb lambda)
        llistaPersones.removeIf(persona -> persona.getAge() >= 30 && persona.getAge() <= 40);
        System.out.println("\n Listat sense persones entre 30 i 40 anys");
        llistaPersones.forEach(persona -> System.out.println(persona.getNom() + ": " +persona.getAge()));

        // 11 - Persones que tinguin una 'a' al seu nom
        List<Persona> personesAmbA = llistaPersones.stream()
                .filter(persona -> persona.getNom().contains("a"))
                .collect(Collectors.toList());
        System.out.println("\n Persones amb a en el seu nom");
        personesAmbA.forEach(persona -> System.out.println(persona.getNom()));

        //12 - Llistat de les dates de naixament + dos dies
        List<LocalDate> datesMesDos = llistaPersones.stream()
                .map(persona -> persona.getDataNaixament().plusDays(2))
                .collect(Collectors.toList());
        System.out.println("\n Datas de naixement amb dos dies");
            datesMesDos.forEach(data -> System.out.println(data));


        //13 - Rejovenir dos anys a totes les persones
        List<Persona> personasRejuvenecides = llistaPersones.stream()
                .map(persona -> {
                    LocalDate novaData = persona.getDataNaixament().minusYears(2);
                    return new Persona(persona.getNom(), persona.getGenere(), novaData);
                }).collect(Collectors.toList());
        System.out.println("\nPersones amd dos anys menys");
        personasRejuvenecides.forEach(persona -> System.out.println(persona.getNom() + ": " + persona.getDataNaixament()));


        //14. A partir de la següent llista de noms d'alumnes crea una nova llista d'alumnes amb aquests alumnes nous
        // pots modificar la classe alumne si cal. I imprimeix la llista dels nous alumnes
        List<String> alumnesNous = Arrays.asList("Pedro","Pablo","Bilma");

        List<Alumne> alumnes = alumnesNous.stream()
                .map(nom -> new Alumne(nom))
                .collect(Collectors.toList());
        System.out.println("\n Nous alumnes");
        alumnes.forEach(alumne -> System.out.println(alumne));

        //Creem cotxes i els assignem a les diferents persones
        Cotxe c1 = new Cotxe("Seat Ibiza");
        Cotxe c2 = new Cotxe("Seat Leon");
        Cotxe c3 = new Cotxe("Seat Arona");
        Cotxe c4 = new Cotxe("Toyota Auris");
        Cotxe c5 = new Cotxe("Toyota Corolla");
        Cotxe c6 = new Cotxe("Toyota Yaris");
        p1.setCotxe(c1); p2.setCotxe(c2);
        p3.setCotxe(c1); p4.setCotxe(c3);
        p5.setCotxe(c4); p6.setCotxe(c4);
        p7.setCotxe(c5); p8.setCotxe(c6);
        p9.setCotxe(c5); p10.setCotxe(c3);
        p11.setCotxe(c2);
        //15. Crear una llista de persones amb el coxes de la marca Seat i una altra
        // amb les persones que tenen un Toyota. Fes servir streams
        // imprimeix les dues llistes per ordenades per l'edat de les persones
        List<Persona> personasAmbSeat = llistaPersones.stream()
                .filter(persona -> persona.getCotxe().getMarca().startsWith("Seat"))
                .sorted(Comparator.comparingInt(Persona::getAge))
                .collect(Collectors.toList());

        List<Persona> personasAmbToyota = llistaPersones.stream()
                .filter(persona -> persona.getCotxe().getMarca().startsWith("Toyota"))
                .sorted(Comparator.comparingInt(Persona::getAge))
                .collect(Collectors.toList());

        System.out.println("\n15 - Persones amb Seat i Toyota");
        personasAmbSeat.forEach(persona -> System.out.println(persona.getNom() + ": " + persona.getCotxe()));
        personasAmbToyota.forEach(persona -> System.out.println(persona.getNom() + ": " + persona.getCotxe()));

        //16. Imprimeix les persones ordenades per ordre alfabètic de la marca dels cotxes
        List<Persona> personesCotxesOrdreMarca = llistaPersones.stream()
                .sorted(Comparator.comparing(persona -> persona.getCotxe().getMarca()))
                .collect(Collectors.toList());

        System.out.println("\n16 - Persones ordenades per ordre alfabètic de la marca dels cotxes");
        personesCotxesOrdreMarca.forEach(persona -> System.out.println(persona.getNom() + ": " + persona.getCotxe()));





    }


}