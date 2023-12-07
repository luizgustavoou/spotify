package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.User;
import imd.ufrn.br.spotify.exceptions.EmptyTextFieldsException;
import imd.ufrn.br.spotify.services.user.ICreateUserUseCase;
import imd.ufrn.br.spotify.services.user.impl.CreateUserUseCaseImpl;

import imd.ufrn.br.spotify.utils.Navigator;
import imd.ufrn.br.spotify.utils.PathViews;
import imd.ufrn.br.spotify.utils.TitleViews;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegistrationControllerFXML implements Initializable {
    AuthController authController;
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
        this.authController = new AuthController();
    }

    public void register() throws EmptyTextFieldsException, IOException {
        String strUsername = this.username.getText();
        String strPassword = this.password.getText();
        String strFullName = this.fullName.getText();
        String strTypeUser = this.typeUserBox.getValue();

        this.authController.register(strUsername, strPassword, strFullName, strTypeUser);

        this.formClear();

        navigator.to(username, TitleViews.LOGIN_VIEW, PathViews.LOGIN_VIEW);
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
        navigator.to(username, TitleViews.LOGIN_VIEW, PathViews.LOGIN_VIEW);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeUserBox.getItems().addAll(typeUsers);
    }

}
