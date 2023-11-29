package imd.ufrn.br.spotify.controllers;


import imd.ufrn.br.spotify.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;
import imd.ufrn.br.spotify.services.user.ILoginUseCase;
import imd.ufrn.br.spotify.services.user.impl.LoginUseCaseImpl;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {
    private final ILoginUseCase loginUseCase;

    private Stage stage;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Button btnLogin;

    private LoginController(ILoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }
    public LoginController() {
        this.loginUseCase = new LoginUseCaseImpl();
    }

    private void login() {
        String strUsername = username.getText();
        String strPassword = password.getText();

        try {
            User user = this.loginUseCase.execute(strUsername, strPassword);

            Stage stage = (Stage) btnLogin.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/imd/ufrn/br/spotify/home-view.fxml"));

            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);

            stage.setTitle("Home");
            stage.setScene(scene);




        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (UnauthorizedException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleMouseClicked(MouseEvent event) {
        this.login();
    }

//    void handleKeyPressed(KeyEvent event) {
//        if(event.getCode() == KeyCode.ENTER) {
//            this.login();
//        }
//    }



}
