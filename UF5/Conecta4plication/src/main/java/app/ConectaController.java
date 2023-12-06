package app;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConectaController {

    Scene scene;

   @FXML
    private Button col0, col1, col2, col3, col4, col5, col6, nuevoJuego;

   @FXML
   private Circle turnoCirculo;

   @FXML
    private MenuItem menuItemJvsJ, menuItemOvsO, menuItemJvsO;

   @FXML
    private GridPane gridPane;

   @FXML
   private Label labelEstadoJuego, jugador2Nombre, jugador1Nombre, vJugador1, vJugador2, dJugador1, dJugador2,
    tPartidasJ1, tPartidasJ2, eJugador1, eJugador2;

   Clasificaciones clasificaciones = new Clasificaciones();

   private boolean partidaIniciada = false;
   private String modoParida = "JJ";
   String nombreAnteriorJugador1 = "Jugador 1";
   String nombreAnteriorJugador2 = "Jugador 2";
   int turno = 1;
   int turnosJugadores = 0;
   int partidasEnJvsJ;
   int partidasEnJvsO;
   int partidasEnOvsO;

   Optional<String> nombresJvsJ;
   Optional<String> nombreJvsO;

    private Pieza[][] tablero = new Pieza[6][7];
    private ScheduledExecutorService scheduler;

    public void inicializarEscena(Scene scene) {
        this.scene = scene;
    }


    //Método para cerrar la ventana
    public void menuItemCerrar(ActionEvent actionEvent) {
        Platform.exit();
    }

    //Métodos para actualizar el modo de juego
    public void clicMenuItemJJ(ActionEvent actionEvent) throws IOException {
        partidasEnJvsJ = 0;
        modoParida = "JJ";
        actualizarEstadoJuego("Jugador vs Jugador");

    }

    public void clicMenuItemOO(ActionEvent actionEvent) throws IOException {
        partidasEnOvsO = 0;
        modoParida = "OO";
        actualizarEstadoJuego("Ordenador vs Ordenador");
    }

    public void clicMenuItemJO(ActionEvent actionEvent) throws IOException {
        partidasEnJvsO = 0;
        modoParida = "JO";
        actualizarEstadoJuego("Jugador vs Ordenador");
    }


    //metodo para cambiar de temas
    public void modoClaro(ActionEvent actionEvent) {
        scene.getStylesheets().clear();
        String css = getClass().getResource("estilos/temaClaro.css").toExternalForm();
        scene.getStylesheets().add(css);
    }

    public void modoOscuro(ActionEvent actionEvent) {
        scene.getStylesheets().clear();
        String css = getClass().getResource("estilos/temaOscuro.css").toExternalForm();
        scene.getStylesheets().add(css);
    }


    //metodo para la ayuda
    public void tutorial(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tutorial");
        alert.setHeaderText(null);
        alert.setContentText("Objetivo: Conectar cuatro fichas del mismo color en línea.\nTablero: 7 columnas x 6 filas." +
                "\nTurnos: Dos jugadores colocan fichas por turno en las columnas.\nVictorias: Gana el primero en formar una línea de cuatro fichas." +
                "\nEmpate: Si el tablero se llena sin un ganador, el juego termina en empate.");
        alert.showAndWait();
    }

    //Desabilitar los botones al iniciar el juego
    public void initialize() {
        col0.setDisable(true);
        col1.setDisable(true);
        col2.setDisable(true);
        col3.setDisable(true);
        col4.setDisable(true);
        col5.setDisable(true);
        col6.setDisable(true);
    }

    //muestra el modo de juego que tenemos
    public void  actualizarEstadoJuego(String nuevotexto) {
        labelEstadoJuego.setText(nuevotexto);
    }

    //metodo para activar los botones
    public void activarBotones() {
        col0.setDisable(false);
        col1.setDisable(false);
        col2.setDisable(false);
        col3.setDisable(false);
        col4.setDisable(false);
        col5.setDisable(false);
        col6.setDisable(false);
    }


    //metodo Iniciar juego
    public void iniciarPartida(ActionEvent actionEvent) {
        if (!partidaIniciada) {
            reiniciarTablero();
            turnoCirculo.setFill(Color.RED);
            switch (modoParida) {
                case "JJ":
                    activarBotones();

                    turno = 1;
                    partidaIniciada = true;

                    if (partidasEnJvsJ > 1) {
                        nombreAnteriorJugador1 = clasificaciones.getJugador1Nombre();
                        nombreAnteriorJugador2 = clasificaciones.getJugador2Nombre();
                    }

                    //ventana para el nombre del jugador
                    TextInputDialog dialog1 = new TextInputDialog(clasificaciones.getJugador1Nombre());
                    dialog1.setTitle("Nuevo juego");
                    dialog1.setHeaderText("Introduzca su nombre");
                    dialog1.setContentText("nombre:");

                    nombresJvsJ = dialog1.showAndWait();
                    nombresJvsJ.ifPresent(rojo -> jugador1Nombre.setText(rojo));


                    TextInputDialog dialog2 = new TextInputDialog(clasificaciones.getJugador2Nombre());
                    dialog2.setTitle("Nuevo juego");
                    dialog2.setHeaderText("Introduzca su nombre");
                    dialog2.setContentText("nombre:");

                    nombresJvsJ = dialog2.showAndWait();
                    nombresJvsJ.ifPresent(amarillo -> jugador2Nombre.setText(amarillo));

                    //desactivamos los botones para evitar conflictos
                    nuevoJuego.setDisable(true);
                    menuItemJvsJ.setDisable(true);
                    menuItemOvsO.setDisable(true);
                    menuItemJvsO.setDisable(true);

                    clasificaciones.setJugador1Nombre(jugador1Nombre.getText());
                    clasificaciones.setJugador2Nombre(jugador2Nombre.getText());

                    //Si los nombres de jugador son diferentes se reinician las puntuaciones
                    if (!clasificaciones.getJugador1Nombre().equals(nombreAnteriorJugador1)) {
                        clasificaciones.setVictoriaJ1(0);
                        clasificaciones.setDerrotasJ1(0);
                        clasificaciones.setEmpatesJ1(0);
                        clasificaciones.setTotalPartidaJ1(0);

                        vJugador1.setText("0");
                        dJugador1.setText("0");
                        eJugador1.setText("0");
                        tPartidasJ1.setText("0");
                    }

                    System.out.println(clasificaciones.getVictoriaJ1());

                    if (!clasificaciones.getJugador2Nombre().equals(nombreAnteriorJugador2)) {
                        clasificaciones.setVictoriaJ2(0);
                        clasificaciones.setDerrotasJ2(0);
                        clasificaciones.setEmpatesJ2(0);
                        clasificaciones.setTotalPartidasJ2(0);

                        vJugador2.setText("0");
                        dJugador2.setText("0");
                        eJugador2.setText("0");
                        tPartidasJ2.setText("0");
                    }

                    partidasEnJvsJ++;
                    break;

                case "JO":
                    activarBotones();

                    turno = 1;
                    partidaIniciada = true;

                    if (partidasEnJvsO > 0) {
                        nombreAnteriorJugador1 = clasificaciones.getJugador1Nombre();
                        nombreAnteriorJugador2 = clasificaciones.getJugador2Nombre();
                    }

                    TextInputDialog dialog = new TextInputDialog(clasificaciones.getJugador1Nombre());
                    dialog.setTitle("Nuevo juego");
                    dialog.setHeaderText("Introduzca su nombre");
                    dialog.setContentText("nombre:");

                    nombreJvsO = dialog.showAndWait();
                    nombreJvsO.ifPresent(rojo -> jugador1Nombre.setText(rojo));
                    jugador2Nombre.setText("Ordenador");

                    nuevoJuego.setDisable(true);
                    menuItemJvsJ.setDisable(true);
                    menuItemOvsO.setDisable(true);
                    menuItemJvsO.setDisable(true);

                    clasificaciones.setJugador1Nombre(jugador1Nombre.getText());
                    clasificaciones.setJugador2Nombre(jugador2Nombre.getText());

                    //reiniciar los contadores si es un nuevo jugador
                    if (!clasificaciones.getJugador1Nombre().equals(nombreAnteriorJugador1)) {
                        clasificaciones.setVictoriaJ1(0);
                        clasificaciones.setDerrotasJ1(0);
                        clasificaciones.setEmpatesJ1(0);
                        clasificaciones.setTotalPartidaJ1(0);

                        vJugador1.setText("0");
                        dJugador1.setText("0");
                        eJugador1.setText("0");
                        tPartidasJ1.setText("0");
                    }

                    if (!clasificaciones.getJugador2Nombre().equals(nombreAnteriorJugador2)) {
                        clasificaciones.setVictoriaJ2(0);
                        clasificaciones.setDerrotasJ2(0);
                        clasificaciones.setEmpatesJ2(0);
                        clasificaciones.setTotalPartidasJ2(0);

                        vJugador2.setText("0");
                        dJugador2.setText("0");
                        eJugador2.setText("0");
                        tPartidasJ2.setText("0");
                    }

                    clasificaciones.setVictoriaJ2(0);
                    clasificaciones.setDerrotasJ2(0);
                    clasificaciones.setEmpatesJ2(0);
                    clasificaciones.setTotalPartidasJ2(0);

                    partidasEnJvsO++;
                    break;
                case "OO":

                    activarBotones();

                    turno = 1;
                    partidaIniciada = true;

                    jugador1Nombre.setText("Ordenador 1");
                    jugador2Nombre.setText("Ordenador 2");

                    nuevoJuego.setDisable(true);
                    menuItemJvsJ.setDisable(true);
                    menuItemOvsO.setDisable(true);
                    menuItemJvsO.setDisable(true);

                    clasificaciones.setJugador1Nombre(jugador1Nombre.getText());
                    clasificaciones.setJugador2Nombre(jugador2Nombre.getText());

                    if (partidasEnOvsO == 0) {
                        //reinicio los contadores
                        vJugador1.setText("0");
                        dJugador1.setText("0");
                        eJugador1.setText("0");
                        tPartidasJ1.setText("0");

                        vJugador2.setText("0");
                        dJugador2.setText("0");
                        eJugador2.setText("0");
                        tPartidasJ2.setText("0");

                        clasificaciones.setVictoriaJ1(0);
                        clasificaciones.setDerrotasJ1(0);
                        clasificaciones.setEmpatesJ1(0);
                        clasificaciones.setTotalPartidaJ1(0);

                        clasificaciones.setVictoriaJ2(0);
                        clasificaciones.setDerrotasJ2(0);
                        clasificaciones.setEmpatesJ2(0);
                        clasificaciones.setTotalPartidasJ2(0);
                    }


                    scheduler = Executors.newScheduledThreadPool(1);
                    scheduler.scheduleAtFixedRate(() -> {
                        Platform.runLater(() -> jugarOrdenador());
                    }, 0, 1, TimeUnit.SECONDS);

                    partidasEnOvsO++;
                    break;
            }
        }
    }

    //método para los botones que asignan las fichas
    public void botonClicado (ActionEvent event) {
        if (event.getSource() instanceof Button) {
            Button clickedButton = (Button) event.getSource();
            String buttonId = clickedButton.getId();
            int columnIndex = Integer.parseInt(buttonId.substring(3));

            if (colocarFicha(columnIndex)) {
                //colocar fichas de acuerdo a las modalidades
                    if (modoParida.equals("OO")) {
                        if (partidaIniciada) {
                            actualizarTablero();
                            comprobarGanador(turno);
                            cambiarTurno();
                        }
                    } else {
                        actualizarTablero();
                        comprobarGanador(turno);
                        cambiarTurno();
                    }


                if (modoParida.equals("JO") && turno == -1) {
                    jugarOrdenador();
                }
            }
        }
    }

    //método para colocar las fichas
    private boolean colocarFicha(int columna) {
        for (int fila = 5; fila >= 0; fila--) {
            if (tablero[fila][columna] == null) {
                tablero[fila][columna] = new Pieza(turno);
                return true;
            }
        }
        return false;
    }

    private void actualizarTablero() {
        for (int fila = 0; fila < 6; fila++) {
            for (int columna = 0; columna < 7; columna++) {
                Pieza pieza = tablero[fila][columna];
                if (pieza != null) {
                    // Verificar si la celda ya contiene una ficha antes de agregarla
                    if (!gridPane.getChildren().contains(pieza)) {
                        gridPane.add(pieza, columna, fila);
                        GridPane.setHalignment(pieza, HPos.CENTER);
                        GridPane.setValignment(pieza, VPos.CENTER);
                    }
                }
            }
        }
    }

    //método para que el ordenador ponga fichas
    private void jugarOrdenador() {
        if (!partidaIniciada) {
            //si la partida ha terminado, detener el scheduler
            detenerSchedule();
            return;
        }
        int columnaAleatoria;

        columnaAleatoria = (int) (Math.random() * 7);


        // Seleccionar el botón correspondiente y realizar el movimiento
        String nombreBoton = "col" + columnaAleatoria;
        Button miboton = (Button) scene.lookup("#" + nombreBoton);

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            Platform.runLater(() -> {
                miboton.fire(); // Tocar boton
            });
        });
        pause.play();
    }


    //detener scheduler
    public void detenerSchedule() {
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }

    //método para cambiar entre turno
    public void cambiarTurno() {
        turno *= -1;
        turnosJugadores++;
        if (turno == 1) {
            turnoCirculo.setFill(Color.RED);
        } else if (turno == -1) {
            turnoCirculo.setFill(Color.YELLOW);
        }

    }

    //método par comprobar ganadores
    public void comprobarGanador(int turno) {
        if (turno == 1) {
            if (hayGanador(turno)) {
                detenerSchedule();
                //jugador uno gana
                partidaIniciada = false;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Partida finalizada");
                alert.setHeaderText(null);
                alert.setContentText("¡Ha ganado "+ jugador1Nombre.getText()+"!");
                alert.showAndWait();
                clasificaciones.aumentarVictorias(clasificaciones.getJugador1Nombre());
                clasificaciones.aumentarDerrotas(clasificaciones.getJugador2Nombre());

                clasificaciones.setVictoriaJ1(clasificaciones.getVictoriaJ1()+1);
                clasificaciones.setTotalPartidaJ1(clasificaciones.getTotalPartidaJ1()+1);

                String vic = String.valueOf(clasificaciones.getVictoriaJ1());
                String pt1 = String.valueOf(clasificaciones.getTotalPartidaJ1());

                vJugador1.setText(vic);
                tPartidasJ1.setText(pt1);

                clasificaciones.setDerrotasJ2(clasificaciones.getDerrotasJ2()+1);
                clasificaciones.setTotalPartidasJ2(clasificaciones.getTotalPartidasJ2()+1);

                String de = String.valueOf(clasificaciones.getDerrotasJ2());
                String pt2 = String.valueOf(clasificaciones.getTotalPartidasJ2());

                dJugador2.setText(de);
                tPartidasJ2.setText(pt2);

                nuevoJuego.setDisable(false);
                menuItemJvsJ.setDisable(false);
                menuItemOvsO.setDisable(false);
                menuItemJvsO.setDisable(false);

                initialize();
            }

        } else {
            //jugador dos gana
            if (hayGanador(turno)) {
                detenerSchedule();

                partidaIniciada = false;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Partida finalizada");
                alert.setHeaderText(null);
                alert.setContentText("¡Ha ganado "+ jugador2Nombre.getText()+"!");
                alert.showAndWait();
                clasificaciones.aumentarDerrotas(clasificaciones.getJugador1Nombre());
                clasificaciones.aumentarVictorias(clasificaciones.getJugador2Nombre());

                clasificaciones.setVictoriaJ2(clasificaciones.getVictoriaJ2()+1);
                clasificaciones.setTotalPartidasJ2(clasificaciones.getTotalPartidasJ2()+1);

                String vic = String.valueOf(clasificaciones.getVictoriaJ2());
                String pt2 = String.valueOf(clasificaciones.getTotalPartidasJ2());

                vJugador2.setText(vic);
                tPartidasJ2.setText(pt2);

                clasificaciones.setDerrotasJ1(clasificaciones.getDerrotasJ1()+1);
                clasificaciones.setTotalPartidaJ1(clasificaciones.getTotalPartidaJ1()+1);

                String de = String.valueOf(clasificaciones.getDerrotasJ1());
                String pt1 = String.valueOf(clasificaciones.getTotalPartidaJ1());

                dJugador1.setText(de);
                tPartidasJ1.setText(pt1);

                nuevoJuego.setDisable(false);
                menuItemJvsJ.setDisable(false);
                menuItemOvsO.setDisable(false);
                menuItemJvsO.setDisable(false);

                initialize();
            }

        }

        // empate
        if (hayEmpate()) {
            detenerSchedule();
            //empate
            partidaIniciada = false;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Partida finalizada");
            alert.setHeaderText(null);
            alert.setContentText("¡Ha habido un empate!");
            alert.showAndWait();

            clasificaciones.aumentarEmpates(clasificaciones.getJugador1Nombre());
            clasificaciones.aumentarEmpates(clasificaciones.getJugador2Nombre());

            clasificaciones.setEmpatesJ1(clasificaciones.getEmpatesJ1()+1);
            clasificaciones.setTotalPartidaJ1(clasificaciones.getTotalPartidaJ1()+1);

            String emp1 = String.valueOf(clasificaciones.getEmpatesJ1());
            String pt1 = String.valueOf(clasificaciones.getTotalPartidaJ1());

            eJugador1.setText(emp1);
            tPartidasJ1.setText(pt1);

            clasificaciones.setEmpatesJ2(clasificaciones.getEmpatesJ2()+1);
            clasificaciones.setTotalPartidasJ2(clasificaciones.getTotalPartidasJ2()+1);

            String emp2 = String.valueOf(clasificaciones.getEmpatesJ2());
            String pt2 = String.valueOf(clasificaciones.getTotalPartidasJ2());

            eJugador2.setText(emp2);
            tPartidasJ2.setText(pt2);

            nuevoJuego.setDisable(false);
            menuItemJvsJ.setDisable(false);
            menuItemOvsO.setDisable(false);
            menuItemJvsO.setDisable(false);

            initialize();
        }
    }

    //métodos para comprobar ganadores por filas, columnas o diagonales
    private boolean hayGanador(int jugador) {
        // Verificar ganador en filas
        for (int fila = 0; fila < 6; fila++) {
            for (int columna = 0; columna < 4; columna++) {
                if (verificarLinea(tablero[fila][columna], tablero[fila][columna + 1],
                        tablero[fila][columna + 2], tablero[fila][columna + 3], jugador)) {
                    return true;
                }
            }
        }

        // Verificar ganador en columnas
        for (int columna = 0; columna < 7; columna++) {
            for (int fila = 0; fila < 3; fila++) {
                if (verificarLinea(tablero[fila][columna], tablero[fila + 1][columna],
                        tablero[fila + 2][columna], tablero[fila + 3][columna], jugador)) {
                    return true;
                }
            }
        }

        // Verificar ganador en diagonales ascendentes
        for (int fila = 3; fila < 6; fila++) {
            for (int columna = 0; columna < 4; columna++) {
                if (verificarLinea(tablero[fila][columna], tablero[fila - 1][columna + 1],
                        tablero[fila - 2][columna + 2], tablero[fila - 3][columna + 3], jugador)) {
                    return true;
                }
            }
        }

        // Verificar ganador en diagonales descendentes
        for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 4; columna++) {
                if (verificarLinea(tablero[fila][columna], tablero[fila + 1][columna + 1],
                        tablero[fila + 2][columna + 2], tablero[fila + 3][columna + 3], jugador)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean verificarLinea(Pieza a, Pieza b, Pieza c, Pieza d, int jugador) {
        // Verificar si las piezas no son nulas y son del mismo jugador
        return (a != null && a.getValor() == jugador) &&
                (b != null && b.getValor() == jugador) &&
                (c != null && c.getValor() == jugador) &&
                (d != null && d.getValor() == jugador);
    }


    //conocer si hay empate
    private boolean hayEmpate() {
        for (int fila = 0; fila < 6; fila++) {
            for (int columna = 0; columna < 7; columna++) {
                if (tablero[fila][columna] == null) {
                    // Hay al menos una celda vacía, no hay empate
                    return false;
                }
            }
        }
        // Todas las celdas están ocupadas, es un empate
        return true;
    }

    //método que reinicia el tablero para volver a jugar
    public void reiniciarTablero() {
        gridPane.getChildren().removeIf(node -> node instanceof Pieza);
        for (int fila = 0; fila < 6; fila++) {
            for (int columna = 0; columna < 7; columna++) {
                tablero[fila][columna] = null;
            }
        }
    }

    //método para generar la ventana con las clasificaciones
    public void clasificaciones(ActionEvent actionEvent) {
        TableView<Jugador> clasificacionesLista = null;

        try {
            clasificacionesLista = FXMLLoader.load(getClass().getResource("clasificaciones.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();

        stage.setTitle("Clasificaciones");
        stage.setScene(new Scene(clasificacionesLista));
        stage.setMinWidth(475);
        stage.setMinHeight(300);
        stage.show();

        clasificacionesLista.getItems().clear();
        clasificacionesLista.getColumns().clear();

        clasificacionesLista.getColumns().addAll(clasificaciones.jugador, clasificaciones.victorias, clasificaciones.derrotas,
                clasificaciones.empates, clasificaciones.partidasJugadas);

        clasificacionesLista.getItems().addAll(clasificaciones.getListaClasificaciones());
    }

}
