package imd.ufrn.br.spotify.front.controllers;

import imd.ufrn.br.spotify.back.controllers.AuthController;
import imd.ufrn.br.spotify.exceptions.EmptyTextFieldsException;

import imd.ufrn.br.spotify.front.services.IAuthService;
import imd.ufrn.br.spotify.front.services.impl.AuthServiceImpl;
import imd.ufrn.br.spotify.front.utils.Navigator;
import imd.ufrn.br.spotify.front.utils.PathViews;
import imd.ufrn.br.spotify.front.utils.TitleViews;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationControllerFXML implements Initializable {
    private IAuthService authService;
    Navigator navigator;
    @FXML
    private TextField fullName;

    @FXML
    private PasswordField password;

    private String[] typeUsers = {"FREE", "VIP"};

    @FXML
    private ChoiceBox<String> typeUserBox;

    @FXML
    private Text msgErrorRegister;

    @FXML
    private TextField username;

    public RegistrationControllerFXML() {
        this.navigator = Navigator.getInstance();
        this.authService = new AuthServiceImpl();
    }

    public void register() throws EmptyTextFieldsException, IOException {
        String strUsername = this.username.getText();
        String strPassword = this.password.getText();
        String strFullName = this.fullName.getText();
        String strTypeUser = this.typeUserBox.getValue();

        this.authService.register(strUsername, strPassword, strFullName, strTypeUser);

        this.formClear();

        Stage stage = this.navigator.configure(username, TitleViews.LOGIN_VIEW, PathViews.LOGIN_VIEW);
        this.navigator.execute(stage);
    }

    private void formClear() {
        this.username.clear();
        this.password.clear();
        this.fullName.clear();
//        this.typeUserBox
    }
    @FXML
    void handleMouseClicked(MouseEvent event) {
        try {
            this.register();
        } catch (EmptyTextFieldsException e) {
            msgErrorRegister.setVisible(true);
            msgErrorRegister.setText(e.getMessage());
        } catch (IOException e) {
            msgErrorRegister.setVisible(true);
            msgErrorRegister.setText(e.getMessage());
        }
    }

    @FXML
    public void backToLogin(MouseEvent event) throws IOException {
        Stage stage = this.navigator.configure(username, TitleViews.LOGIN_VIEW, PathViews.LOGIN_VIEW);
        this.navigator.execute(stage);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeUserBox.getItems().addAll(typeUsers);
    }

}
