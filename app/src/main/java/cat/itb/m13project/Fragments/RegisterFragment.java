package cat.itb.m13project.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import cat.itb.m13project.R;
import cat.itb.m13project.SaveSharedPreference;
import cat.itb.m13project.pojo.Usuario;

import static cat.itb.m13project.ConstantVariables.CONTEXT;
import static cat.itb.m13project.ConstantVariables.DB_USER_REF;
import static cat.itb.m13project.ConstantVariables.LOGGED_USER;
import static cat.itb.m13project.ConstantVariables.USER_LIST;

public class RegisterFragment extends Fragment {

    protected static final int PASSWORD_LENGTH = 7;
    TextInputLayout name;
    TextInputLayout email;
    TextInputLayout password;
    TextInputLayout repeatedPassword;
    TextInputLayout address;
    TextInputLayout hint;
    MaterialButton registerButton;
    MaterialButton loginButton;
    ProgressBar loadingProgressBar;
    MaterialCheckBox termsCheckBox;
    MaterialTextView termsTextView;
    String stringName, stringEmail, stringPassword, stringRepeatedPassword, stringAddress, stringHint;
    View.OnClickListener registerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println("REGISTER LISTENER");
            stringName = RegisterFragment.this.name.getEditText().getText().toString();
            stringEmail = RegisterFragment.this.email.getEditText().getText().toString();
            stringPassword = RegisterFragment.this.password.getEditText().getText().toString();
            stringRepeatedPassword = RegisterFragment.this.repeatedPassword.getEditText().getText().toString();
            stringAddress = RegisterFragment.this.address.getEditText().getText().toString();
            stringHint = RegisterFragment.this.hint.getEditText().getText().toString();

            LOGGED_USER.setName(stringName);
            LOGGED_USER.setEmail(stringEmail);
            LOGGED_USER.setPassword(stringPassword);
            LOGGED_USER.setAddress(stringAddress);
            LOGGED_USER.setForgottenPasswordHint(stringHint);

            Query query = FirebaseDatabase.getInstance().getReference("Usuario").orderByChild("email").equalTo(LOGGED_USER.getEmail());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    boolean exists = false;
                    USER_LIST.clear();
                    if (snapshot.exists()) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Usuario user = ds.getValue(Usuario.class);
                            USER_LIST.add(user);
                            exists = true;
                        }
                    }
                    if (!exists) {
                        if (stringPassword.equals(stringRepeatedPassword)) {
                            if (isValidData(LOGGED_USER)) {
                                String key = DB_USER_REF.push().getKey();
                                LOGGED_USER.setId(key);
                                DB_USER_REF.child(key).setValue(LOGGED_USER);
                                SaveSharedPreference.setUserName(getContext(), LOGGED_USER.getEmail());
                                loadingProgressBar.setVisibility(View.VISIBLE);
                                Fragment newFragment = new HomeFragment();
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.fragment, newFragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                            }
                        } else {
                            repeatedPassword.setHint(getString(R.string.invalid_password));
                            repeatedPassword.getEditText().setText("");
                        }
                    } else {
                        Toast.makeText(getContext(), "Account already exists.", Toast.LENGTH_SHORT).show();
                        Fragment newFragment = new LoginFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment, newFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    };
    View.OnClickListener updateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println("UPDATE LISTENER");
            stringName = RegisterFragment.this.name.getEditText().getText().toString();
            stringPassword = RegisterFragment.this.password.getEditText().getText().toString();
            stringAddress = RegisterFragment.this.address.getEditText().getText().toString();
            stringHint = RegisterFragment.this.hint.getEditText().getText().toString();

            LOGGED_USER.setName(stringName);
            LOGGED_USER.setPassword(stringPassword);
            LOGGED_USER.setAddress(stringAddress);
            LOGGED_USER.setForgottenPasswordHint(stringHint);

            Query query = FirebaseDatabase.getInstance().getReference("Usuario").orderByChild("email").equalTo(LOGGED_USER.getEmail());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    USER_LIST.clear();
                    if (snapshot.exists()) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Usuario user = ds.getValue(Usuario.class);
                            USER_LIST.add(user);
                        }
                    }
                    if (isValidData(LOGGED_USER)) {
                        LOGGED_USER.setId(USER_LIST.get(0).getId());
                        DB_USER_REF.child(LOGGED_USER.getId()).setValue(LOGGED_USER);
                        loadingProgressBar.setVisibility(View.VISIBLE);
                        Fragment newFragment = new ProfileFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment, newFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    };
    private MaterialTextView titleTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CONTEXT = getContext();

        name = view.findViewById(R.id.nameTextView);
        email = view.findViewById(R.id.emailTextView);
        password = view.findViewById(R.id.passwordTextView);
        repeatedPassword = view.findViewById(R.id.repeatPasswordTextView);
        address = view.findViewById(R.id.addressTextView);
        hint = view.findViewById(R.id.hintTextView);

        loginButton = view.findViewById(R.id.loginButton);
        registerButton = view.findViewById(R.id.registerButton);
        loadingProgressBar = view.findViewById(R.id.loading);

        termsCheckBox = view.findViewById(R.id.termsCheckBox);
        termsTextView = view.findViewById(R.id.termsMessage);
        termsTextView.setVisibility(View.INVISIBLE);

        titleTextView = view.findViewById(R.id.registerTextView);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });


        if (LOGGED_USER != null) {
            if (!LOGGED_USER.getEmail().equals(getString(R.string.guest)) && !(LOGGED_USER.getName().equals(getString(R.string.guest)))) {
                name.getEditText().setText(LOGGED_USER.getName());
                email.getEditText().setText(LOGGED_USER.getEmail());
                password.getEditText().setText(LOGGED_USER.getPassword());
                repeatedPassword.getEditText().setText(LOGGED_USER.getPassword());
                address.getEditText().setText(LOGGED_USER.getAddress());
                hint.getEditText().setText(LOGGED_USER.getForgottenPasswordHint());

                termsCheckBox.setVisibility(View.INVISIBLE);
                termsCheckBox.setChecked(true);
                loginButton.setVisibility(View.INVISIBLE);

                registerButton.setOnClickListener(updateListener);
                registerButton.setText(getString(R.string.edit_profile));

                email.setFocusable(false);
                email.getEditText().setFocusable(false);
                email.setClickable(false);
                email.getEditText().setClickable(false);
                email.setOnKeyListener(null);
                email.setOnClickListener(null);
                titleTextView.setText(getString(R.string.edit_profile));
            } else {
                registerButton.setOnClickListener(registerListener);
            }
        } else {
            Toast.makeText(CONTEXT, "ERROR: INVALID USER", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isValidData(Usuario u) {
        if (u.getName().length() == 0) {
            name.setHint(R.string.invalid_name);
            name.getEditText().setText(R.string.blank);
            return false;
        }
        if (u.getEmail().length() < 5
                || !u.getEmail().contains("@")
                || !(u.getEmail().lastIndexOf(".") > u.getEmail().indexOf("@"))
                || u.getEmail().indexOf("@") != u.getEmail().lastIndexOf("@")
                || u.getEmail().lastIndexOf(".") + 1 == u.getEmail().length()) {
            email.getEditText().setHintTextColor(Color.RED);
            email.getEditText().setText(R.string.blank);
            email.setHint(R.string.invalid_email);
            return false;
        }
        if (!u.getPassword().equals(password.getEditText().getText().toString())
                && !u.getPassword().equals(repeatedPassword.getEditText().getText().toString())) {
            password.getEditText().setHintTextColor(Color.RED);
            password.getEditText().setText(R.string.blank);
            password.setHint(R.string.invalid_password);

            repeatedPassword.getEditText().setHintTextColor(Color.RED);
            repeatedPassword.getEditText().setText(R.string.blank);
            repeatedPassword.setHint(R.string.invalid_password);
            return false;
        }
        if (u.getPassword().length() < PASSWORD_LENGTH) {
            password.getEditText().setHintTextColor(Color.RED);
            password.getEditText().setText(R.string.blank);
            password.setHint(R.string.short_password);

            repeatedPassword.getEditText().setHintTextColor(Color.RED);
            repeatedPassword.getEditText().setText(R.string.blank);
            repeatedPassword.setHint(R.string.short_password);
            return false;
        }
        if (u.getAddress().length() == 0) {
            address.getEditText().setHintTextColor(Color.RED);
            address.getEditText().setText(R.string.blank);
            address.setHint(R.string.invalid_address);
            return false;
        }
        if (u.getForgottenPasswordHint().length() == 0 || u.getForgottenPasswordHint().isEmpty()) {
            hint.getEditText().setHintTextColor(Color.RED);
            hint.setHint(getString(R.string.hintHelp));
            hint.getEditText().setText(R.string.blank);
            return false;
        }
        if (!termsCheckBox.isChecked()) {
            termsTextView.setTextColor(Color.RED);
            termsTextView.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }
}