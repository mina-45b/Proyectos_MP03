package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ConectaAplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ConectaAplication.class.getResource("conecta-app.fxml"));
       String css = getClass().getResource("estilos/temaClaro.css").toExternalForm();


        SplitPane root = fxmlLoader.load();
        ConectaController controller = fxmlLoader.getController();

        Scene scene = new Scene(root, 977, 435);
        controller.inicializarEscena(scene);
        scene.getStylesheets().add(css);
        stage.setTitle("Conecta 4");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
