package imd.ufrn.br.spotify.front.controllers;

import imd.ufrn.br.spotify.back.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;


import imd.ufrn.br.spotify.front.services.IAuthService;
import imd.ufrn.br.spotify.front.services.impl.AuthServiceImpl;
import imd.ufrn.br.spotify.front.stores.UserStore;
import imd.ufrn.br.spotify.front.utils.Navigator;
import imd.ufrn.br.spotify.front.utils.PathViews;
import imd.ufrn.br.spotify.front.utils.TitleViews;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginControllerFXML {
    private final IAuthService authService;
    private final UserStore userStore;

    private final Navigator navigator;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Button btnLogin;

    @FXML
    private Text msgError;

    public LoginControllerFXML() {
        this.navigator = Navigator.getInstance();
        this.userStore = UserStore.getInstance();
        this.authService = new AuthServiceImpl();
    }

    private void login() {
        String strUsername = username.getText();
        String strPassword = password.getText();

        try {
            Stage stage;
            User user = this.authService.login(strUsername, strPassword);

            userStore.setUser(user);

            if(userStore.getIsVip()) {
                stage = this.navigator.configure(btnLogin, TitleViews.VIP_HOME_VIEW, PathViews.VIP_HOME_VIEW);

            }else {
                stage = this.navigator.configure(btnLogin, TitleViews.FREE_HOME_VIEW, PathViews.FREE_HOME_VIEW);
            }

            this.navigator.execute(stage);


        } catch (EntityNotFoundException e) {
            msgError.setVisible(true);
            msgError.setText(e.getMessage());
        } catch (UnauthorizedException e) {
            msgError.setVisible(true);
            msgError.setText(e.getMessage());
        } catch (IOException e) {
            msgError.setVisible(true);
            msgError.setText(e.getMessage());
        }
    }

    @FXML
    void handleMouseClicked(MouseEvent event) {
        this.login();
    }

    @FXML
    void navigateToRegister(MouseEvent event) throws IOException {
       Stage stage = this.navigator.configure(btnLogin, TitleViews.REGISTER_VIEW, PathViews.REGISTER_VIEW);
       this.navigator.execute(stage);
    }
}
