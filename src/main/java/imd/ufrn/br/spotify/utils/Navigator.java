package imd.ufrn.br.spotify.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigator {
    static private Navigator instance;

    private Navigator(){}

    static public Navigator getInstance() {
        if(instance == null) {
            instance = new Navigator();
        }

        return instance;
    }

    public void to(Node node, String title, String viewPath) throws IOException {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(viewPath));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
