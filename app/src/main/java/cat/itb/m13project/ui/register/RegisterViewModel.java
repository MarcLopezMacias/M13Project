package cat.itb.m13project.ui.register;

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

public class RegisterViewModel extends ViewModel {

    private MutableLiveData<RegisterFormState> registerFormState = new MutableLiveData<>();
    private MutableLiveData<RegisterResult> registerResult = new MutableLiveData<>();
    private LoginRepository registerRepository;

    RegisterViewModel(LoginRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    LiveData<RegisterFormState> getRegisterFormState() {
        return registerFormState;
    }

    LiveData<RegisterResult> getRegisterResult() {
        return registerResult;
    }

    public boolean register(Usuario user) {
        // can be launched in a separate asynchronous job
        LoginResult<LoggedInUser> loginResult = registerRepository.login(user.getEmail(), user.getPassword());
        boolean validLogin = false;
        if (loginResult instanceof LoginResult.Success) {
            LoggedInUser data = ((LoginResult.Success<LoggedInUser>) loginResult).getData();
            registerResult.setValue(new RegisterResult(new RegisteredInUserView(data.getDisplayName())));
            validLogin = true;
        } else {
            registerResult.setValue(new RegisterResult(R.string.login_failed));
        }
        return validLogin;
    }

    public void loginDataChanged(String username, String password, String repeatPassword) {
        if (!isUserNameValid(username)) {
            registerFormState.setValue(new RegisterFormState(R.string.invalid_username, null, null));
        } else if (!isPasswordValid(password)) {
            registerFormState.setValue(new RegisterFormState(null, R.string.invalid_password, null));
        } else if (!isRepeatPasswordValid(repeatPassword)) {
            registerFormState.setValue(new RegisterFormState(null, null, R.string.invalid_password));
        } else {
            registerFormState.setValue(new RegisterFormState(true));
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

    // A placeholder password validation check
    private boolean isRepeatPasswordValid(String password) {
        return password != null && password.trim().length() > USER_PASSWORD_LENGTH;
    }
}