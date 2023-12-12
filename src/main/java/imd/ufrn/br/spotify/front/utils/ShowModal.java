package imd.ufrn.br.spotify.front.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowModal {
//    static private ShowModal instance;

//    private ShowModal(){}

//    static public ShowModal getInstance() {
//        if(instance == null) {
//            instance = new ShowModal();
//        }
//
//        return instance;
//    }

    private FXMLLoader fxmlLoader;

    public FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    public Stage configure(Node node, String title, String viewPath) throws IOException {
        this.fxmlLoader = new FXMLLoader(getClass().getResource(viewPath));

        Parent root = fxmlLoader.load();

        Stage parentStage = (Stage) node.getScene().getWindow();
        Stage dialog = new Stage();

        Scene scene = new Scene(root, 300, 300);

        dialog.setScene(scene);

        dialog.initOwner(parentStage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(title);
        dialog.setResizable(false);

        return dialog;
    }

    public void execute(Stage stage) {
        stage.showAndWait();
    }
}