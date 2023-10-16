public class Dau {

    int valor;

    public Dau() {
        this.valor = getValor();
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }


    public int tirar () {
        valor = (int) (Math.random() * 6) + 1;
        return valor;
    }

    public boolean equals(Object object) {
        if(this == object) {
            return true;
        }
        if(object == null || getClass() != object.getClass()) {
            return false;
        }

        Dau dau2 = (Dau) object;
        return valor == dau2.valor;
    }

    @Override
    public String toString() {
        return "Dau{" +
                "valor=" + valor +
                '}';
    }

}
