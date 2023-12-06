package app;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Comparator;

public class Clasificaciones {
    private String jugador1Nombre;
    private String jugador2Nombre;

    private TableView<Jugador> tablaClasificaciones = null;

    private ObservableList<Jugador> listaClasificaciones;

    int victoriaJ1, victoriaJ2;
    int derrotasJ1, derrotasJ2;
    int empatesJ1, empatesJ2;
    int totalPartidaJ1, totalPartidasJ2;

    boolean jugadorEncontrado;


    //Columna de la tabla
    TableColumn<Jugador, String>  jugador = new TableColumn<>("Jugador");
    TableColumn<Jugador, Integer> victorias = new TableColumn<>("Victorias");
    TableColumn<Jugador, Integer> derrotas = new TableColumn<>("Derrotas");
    TableColumn<Jugador, Integer> empates = new TableColumn<>("Empates");
    TableColumn<Jugador, Integer> partidasJugadas = new TableColumn<>("Partidas Jugadas");


    public String getJugador1Nombre() {
        return jugador1Nombre;
    }

    public void setJugador1Nombre(String jugador1Nombre) {
        this.jugador1Nombre = jugador1Nombre;
    }

    public String getJugador2Nombre() {
        return jugador2Nombre;
    }

    public void setJugador2Nombre(String jugador2Nombre) {
        this.jugador2Nombre = jugador2Nombre;
    }

    public int getVictoriaJ1() {
        return victoriaJ1;
    }

    public void setVictoriaJ1(int victoriaJ1) {
        this.victoriaJ1 = victoriaJ1;
    }

    public int getVictoriaJ2() {
        return victoriaJ2;
    }

    public void setVictoriaJ2(int victoriaJ2) {
        this.victoriaJ2 = victoriaJ2;
    }

    public int getDerrotasJ1() {
        return derrotasJ1;
    }

    public void setDerrotasJ1(int derrotasJ1) {
        this.derrotasJ1 = derrotasJ1;
    }

    public int getDerrotasJ2() {
        return derrotasJ2;
    }

    public void setDerrotasJ2(int derrotasJ2) {
        this.derrotasJ2 = derrotasJ2;
    }

    public int getEmpatesJ1() {
        return empatesJ1;
    }

    public void setEmpatesJ1(int empatesJ1) {
        this.empatesJ1 = empatesJ1;
    }

    public int getEmpatesJ2() {
        return empatesJ2;
    }

    public void setEmpatesJ2(int empatesJ2) {
        this.empatesJ2 = empatesJ2;
    }

    public int getTotalPartidaJ1() {
        return totalPartidaJ1;
    }

    public void setTotalPartidaJ1(int totalPartidaJ1) {
        this.totalPartidaJ1 = totalPartidaJ1;
    }

    public int getTotalPartidasJ2() {
        return totalPartidasJ2;
    }

    public void setTotalPartidasJ2(int totalPartidasJ2) {
        this.totalPartidasJ2 = totalPartidasJ2;
    }

    public ObservableList<Jugador> getListaClasificaciones() {
        return listaClasificaciones.sorted(Comparator.comparingInt(Jugador::getVictorias).reversed());
    }

    public Clasificaciones() {
        jugador1Nombre = "Jugador 1";
        jugador2Nombre = "Jugador 2";
        this.victoriaJ1 = 0;
        this.victoriaJ2 = 0;
        this.derrotasJ1 = 0;
        this.derrotasJ2 = 0;
        this.empatesJ1 = 0;
        this.empatesJ2 = 0;
        this.totalPartidaJ1 = 0;
        this.totalPartidasJ2 = 0;

        //tratamos de cargar la tabla y rellenar las columnas
        try {
            tablaClasificaciones = FXMLLoader.load(getClass().getResource("clasificaciones.fxml"));
            listaClasificaciones = tablaClasificaciones.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }

        jugador.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        victorias.setCellValueFactory(new PropertyValueFactory<>("victorias"));
        derrotas.setCellValueFactory(new PropertyValueFactory<>("derrotas"));
        empates.setCellValueFactory(new PropertyValueFactory<>("empates"));
        partidasJugadas.setCellValueFactory(new PropertyValueFactory<>("partidasJugadas"));

        partidasJugadas.setPrefWidth(153);
    }

    //método para añadir jugadores a la ObserveList
    public void anyadirJugador(String nombre) {
        listaClasificaciones.add(new Jugador(nombre));
    }

    //métedos para sumar puntos a los jugadores de acuerdo a si pierden, ganan, empatan
    public void aumentarVictorias(String nombreJugador) {
        Jugador jugador = buscarJugador(nombreJugador);

        if (jugadorEncontrado) {
            jugador.setVictorias(jugador.getVictorias()+1);
            jugador.setPartidasJugadas(jugador.getPartidasJugadas()+1);
        }else {
            anyadirJugador(nombreJugador);
            jugador = buscarJugador(nombreJugador);
            if (jugadorEncontrado){
                jugador.setVictorias(jugador.getVictorias()+1);
                jugador.setPartidasJugadas(jugador.getPartidasJugadas()+1);
            }
        }
    }

    public void aumentarDerrotas(String nombreJugador) {
        Jugador jugador = buscarJugador(nombreJugador);

        if (jugadorEncontrado) {
            jugador.setDerrotas(jugador.getDerrotas()+1);
            jugador.setPartidasJugadas(jugador.getPartidasJugadas()+1);
        }else {
            anyadirJugador(nombreJugador);
            jugador = buscarJugador(nombreJugador);
            if (jugadorEncontrado){
                jugador.setDerrotas(jugador.getDerrotas()+1);
                jugador.setPartidasJugadas(jugador.getPartidasJugadas()+1);
            }
        }

    }

    public void aumentarEmpates(String nombreJugador) {
        Jugador jugador = buscarJugador(nombreJugador);

        if (jugadorEncontrado) {
            jugador.setEmpates(jugador.getEmpates()+1);
            jugador.setPartidasJugadas(jugador.getPartidasJugadas()+1);
        }else {
            anyadirJugador(nombreJugador);
            jugador = buscarJugador(nombreJugador);
            if (jugadorEncontrado){
                jugador.setEmpates(jugador.getEmpates()+1);
                jugador.setPartidasJugadas(jugador.getPartidasJugadas()+1);
            }
        }
    }

    //método para buscar jugadores dentro de la ObserveList
    public Jugador buscarJugador(String nombre) {
        for (Jugador jugador:listaClasificaciones) {
            if (jugador.getNombre().equals(nombre)) {
                jugadorEncontrado = true;
                return jugador;
            }
        }
        jugadorEncontrado = false;
        return null;
    }

}
