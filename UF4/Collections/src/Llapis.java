import java.util.Objects;

public class Llapis implements Comparable<Llapis>{

    private int color;
    private float gruix;


    public Llapis(int color, float gruix) {
        this.color = color;
        this.gruix = gruix;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getGruix() {
        return gruix;
    }

    public void setGruix(float gruix) {
        this.gruix = gruix;
    }

    @Override
    public String toString() {
        return "Llapis: " + "color = " + color + ", gruix = " + gruix;
    }

    //Implementación del método comparable para Collections.sort();
    @Override
    public int compareTo(Llapis llapis) {
        return Integer.compare(this.color, llapis.getColor());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Llapis llapis = (Llapis) object;
        return color == llapis.color && Float.compare(gruix, llapis.gruix) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, gruix);
    }
}
