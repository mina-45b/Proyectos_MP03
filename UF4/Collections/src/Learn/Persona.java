package Learn;

public class Persona {

    String nom;
    int edad;
    int dni;

    public Persona(String nombrePersona,  int dni) {
        nom = nombrePersona;
        this.edad = 18;
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
}
