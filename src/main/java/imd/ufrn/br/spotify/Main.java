package imd.ufrn.br.spotify;

import imd.ufrn.br.spotify.utils.PathViews;
import imd.ufrn.br.spotify.utils.TitleViews;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PathViews.REGISTER_VIEW));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        stage.setTitle(TitleViews.REGISTER_VIEW);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}