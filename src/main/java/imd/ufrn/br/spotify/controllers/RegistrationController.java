package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
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

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.UUID;

public class RegistrationController implements Initializable {
    Navigator navigator;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField fullName;

    @FXML
    private PasswordField password;

    private String[] typeUsers = {"FREE", "VIP"};
    @FXML
    private ChoiceBox<String> typeUserBox;

    @FXML
    private TextField username;
    private final ICreateUserUseCase createUserUseCase;

    public RegistrationController(ICreateUserUseCase createUserUseCase, Navigator navigator) {
        this.createUserUseCase = createUserUseCase;
        this.navigator = navigator;
    }
    public RegistrationController() {
        this.createUserUseCase = new CreateUserUseCaseImpl();
        this.navigator = Navigator.getInstance();
    }


    public void register() throws EntityNotFoundException, IOException {
        String strUsername = this.username.getText();
        String strPassword = this.password.getText();
        String strFullName = this.fullName.getText();
        String strTypeUser = this.typeUserBox.getValue();


        User user = new User(strUsername, strPassword, strFullName, Objects.equals(strTypeUser, "VIP"));

        this.createUserUseCase.execute(user);
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
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
