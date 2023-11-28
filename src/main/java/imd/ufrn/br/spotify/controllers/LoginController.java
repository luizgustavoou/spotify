package imd.ufrn.br.spotify.controllers;


import imd.ufrn.br.spotify.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;
import imd.ufrn.br.spotify.services.user.ILoginUseCase;
import imd.ufrn.br.spotify.services.user.impl.LoginUseCaseImpl;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Button btnLogin;

    private final ILoginUseCase loginUseCase;

    private LoginController(ILoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }
    public LoginController() {
        this.loginUseCase = new LoginUseCaseImpl();
    }

    @FXML
    void login(MouseEvent event) {
        String strUsername = username.getText();
        String strPassword = password.getText();

        try {
            User user = this.loginUseCase.execute(strUsername, strPassword);
            System.out.println(user);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        } catch (UnauthorizedException e) {
            e.printStackTrace();
        }



    }

}
