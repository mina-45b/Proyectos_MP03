package app;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Objects;

public class Pieza extends Circle {
    private int valor; // 1 para jugador 1, -1 para jugador 2

    public int getValor() {
        return valor;
    }

    public Pieza(int valor) {
        super(25); // Radio del círculo
        this.valor = valor;
        configurarColor();
    }

    private void configurarColor() {
        if (valor == 1) {
            setFill(Color.valueOf("ff0400"));
        } else if (valor == -1){
            setFill(Color.valueOf("f6ea00"));
        }
    }


}
