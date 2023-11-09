package Proyectos_MP03.UF5.Lambdas_Streams;

public class Cotxe {
        String marca;
        public Cotxe(String m) {
            marca = m;
        }

        public String getMarca() {
            return marca;
        }

        @Override
        public String toString() {
            return "Cotxe{" +
                    "marca='" + marca + '\'' +
                    '}';
        }
}
