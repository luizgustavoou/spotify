package imd.ufrn.br.spotify.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowModal {
    static private ShowModal instance;

    private ShowModal(){}

    static public ShowModal getInstance() {
        if(instance == null) {
            instance = new ShowModal();
        }

        return instance;
    }
    public void execute(Node node, String title, String viewPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(viewPath));
        Parent root = fxmlLoader.load();

        Stage parentStage = (Stage) node.getScene().getWindow();
        Stage dialog = new Stage();


        dialog.setScene(new Scene(root, 300, 300));

        dialog.initOwner(parentStage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(title);
        dialog.setResizable(false);
        dialog.showAndWait();
    }
}