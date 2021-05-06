package cat.itb.m13project.ui.login;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.itb.m13project.R;
import cat.itb.m13project.data.LoginRepository;
import cat.itb.m13project.data.LoginResult;
import cat.itb.m13project.data.model.LoggedInUser;
import cat.itb.m13project.pojo.Usuario;

import static cat.itb.m13project.ConstantVariables.USER_PASSWORD_LENGTH;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<cat.itb.m13project.ui.login.LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<cat.itb.m13project.ui.login.LoginResult> getLoginResult() {
        return loginResult;
    }

    public boolean login(Usuario user) {
        // can be launched in a separate asynchronous job
        LoginResult<LoggedInUser> loginResult = loginRepository.login(user.getEmail(), user.getPassword());
        boolean validLogin = false;
        if (loginResult instanceof LoginResult.Success) {
            LoggedInUser data = ((LoginResult.Success<LoggedInUser>) loginResult).getData();
            this.loginResult.setValue(new cat.itb.m13project.ui.login.LoginResult(new LoggedInUserView(data.getDisplayName())));
            validLogin = true;
        } else {
            this.loginResult.setValue(new cat.itb.m13project.ui.login.LoginResult(R.string.login_failed));
        }
        return validLogin;
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > USER_PASSWORD_LENGTH;
    }
}