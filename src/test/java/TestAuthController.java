import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import imd.ufrn.br.spotify.back.controllers.AuthController;
import imd.ufrn.br.spotify.back.entities.User;
import imd.ufrn.br.spotify.back.services.user.ICreateUserUseCase;
import imd.ufrn.br.spotify.back.services.user.ILoginUseCase;
import imd.ufrn.br.spotify.back.services.user.impl.CreateUserUseCaseImpl;
import imd.ufrn.br.spotify.back.services.user.impl.LoginUseCaseImpl;
import imd.ufrn.br.spotify.exceptions.EmptyTextFieldsException;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;

public class TestAuthController {
    private AuthController authController;

    private ILoginUseCase loginUseCase;

    private ICreateUserUseCase createUserUseCase;

    @Before
    public void setup() {
        this.loginUseCase = mock(LoginUseCaseImpl.class);

        this.createUserUseCase = mock(CreateUserUseCaseImpl.class);

        this.authController = new AuthController(loginUseCase, createUserUseCase);

    }

    @Test
    public void happyPathTestLoin() throws EntityNotFoundException, UnauthorizedException {
        User user = new User("Luiz", "123", "Luiz Umbelino", true);

        when(loginUseCase.execute("Luiz", "123")).thenReturn(user);

        User userLogged = this.authController.login("Luiz", "123");

        assertEquals(user, userLogged);
    }

    @Test(expected = EntityNotFoundException.class)
    public void entityNotFoundLogin() throws EntityNotFoundException, UnauthorizedException {
        when(loginUseCase.execute("luiz", "123")).thenThrow(EntityNotFoundException.class);

        User userLogged = this.authController.login("luiz", "123");

    }

    @Test(expected = UnauthorizedException.class)
    public void unauthorizedLogin() throws EntityNotFoundException, UnauthorizedException {
        when(loginUseCase.execute("luiz", "123")).thenThrow(UnauthorizedException.class);

        User userLogged = this.authController.login("luiz", "123");

    }

    @Test
    public void happyPathTestRegister() throws EmptyTextFieldsException {
        User user = new User("Luiz", "123", "Luiz Umbelino", true);

        when(createUserUseCase.execute(user)).thenReturn(user);

        String username = authController.register("Luiz", "123", "Luiz Umbelino", "VIP").getUsername();

        assertEquals("Luiz", username);

    }

    @Test(expected = EmptyTextFieldsException.class)
    public void usernameIsEmpty() throws EmptyTextFieldsException {
        authController.register("", "123", "Luiz Umbelino", "VIP");

    }

    @Test(expected = EmptyTextFieldsException.class)
    public void passwordIsEmpty() throws EmptyTextFieldsException {
        authController.register("Luiz", "", "Luiz Umbelino", "VIP");

    }

    @Test(expected = EmptyTextFieldsException.class)
    public void fullNameIsEmpty() throws EmptyTextFieldsException {
        authController.register("Luiz", "123", "", "VIP");

    }

    @Test(expected = EmptyTextFieldsException.class)
    public void typeUserIsEmpty() throws EmptyTextFieldsException {
        authController.register("Luiz", "123", "Luiz Umbelino", "");

    }
}
