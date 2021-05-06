package cat.itb.m13project.data;

import java.io.IOException;

import cat.itb.m13project.data.model.LoggedInUser;
import cat.itb.m13project.pojo.Usuario;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public LoginResult<LoggedInUser> login(Usuario user) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            user.getName());
            return new LoginResult.Success<>(fakeUser);
        } catch (Exception e) {
            return new LoginResult.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}