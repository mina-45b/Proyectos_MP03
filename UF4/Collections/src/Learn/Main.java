package Learn;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList <Persona> Personas = new ArrayList<>();

        Persona persona1 = new Persona("Juan", 123456789);
        Persona persona2 = new Persona("Pepe", 234678379);
        Persona persona3 = new Persona("Alberto", 867643457);

        Personas.add(persona1);
        Personas.add(persona2);
        Personas.add(persona3);

        for (Persona j:Personas) {
            System.out.println("Persona " + "nom: " + j.getNom() + " edat: " + j.getEdad() + " dni: " + j.getDni());
        }

        persona1.setEdad(20);
        System.out.println("Persona " + "nom: " + persona1.getNom() + " edat: " + persona1.getEdad());
    }
}
