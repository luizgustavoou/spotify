package imd.ufrn.br.spotify.controllers;


import imd.ufrn.br.spotify.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;
import imd.ufrn.br.spotify.services.user.ILoginUseCase;
import imd.ufrn.br.spotify.services.user.impl.LoginUseCaseImpl;


import imd.ufrn.br.spotify.stores.UserStore;
import imd.ufrn.br.spotify.utils.Navigator;
import imd.ufrn.br.spotify.utils.PathViews;
import imd.ufrn.br.spotify.utils.TitleViews;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    private final UserStore userStore;

    private final ILoginUseCase loginUseCase;

    Navigator navigator;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Button btnLogin;

    private LoginController(ILoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
        this.userStore = UserStore.getInstance();
    }

    public LoginController() {
        this.loginUseCase = new LoginUseCaseImpl();
        this.navigator = Navigator.getInstance();
        this.userStore = UserStore.getInstance();
    }

    private void login() {
        String strUsername = username.getText();
        String strPassword = password.getText();

        try {
            User user = this.loginUseCase.execute(strUsername, strPassword);

            userStore.setUser(user);

            this.navigator.to(btnLogin, TitleViews.HOME_VIEW, PathViews.HOME_VIEW);

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
