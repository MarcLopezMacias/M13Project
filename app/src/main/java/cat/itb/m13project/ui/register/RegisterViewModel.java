package cat.itb.m13project.ui.register;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.itb.m13project.R;
import cat.itb.m13project.data.LoginRepository;
import cat.itb.m13project.data.Result;
import cat.itb.m13project.data.model.LoggedInUser;

import static cat.itb.m13project.ConstantVariables.USER_PASSWORD_LENGTH;

public class RegisterViewModel extends ViewModel {

    private MutableLiveData<RegisterFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<RegisterResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    RegisterViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<RegisterFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<RegisterResult> getLoginResult() {
        return loginResult;
    }

    public boolean login(String username, String password) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password);
        boolean validLogin = false;
        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new RegisterResult(new RegisteredInUserView(data.getDisplayName())));
            validLogin = true;
        } else {
            loginResult.setValue(new RegisterResult(R.string.login_failed));
        }
        return validLogin;
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new RegisterFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new RegisterFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new RegisterFormState(true));
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