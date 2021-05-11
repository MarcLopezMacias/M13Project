package cat.itb.m13project.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import cat.itb.m13project.R;
import cat.itb.m13project.pojo.Usuario;

import static cat.itb.m13project.MainActivity.dbRef;
import static cat.itb.m13project.MainActivity.loggedUser;
import static cat.itb.m13project.MainActivity.userList;

public class RegisterFragment extends Fragment {

    protected static final int PASSWORD_LENGTH = 7;
    TextInputLayout name;
    TextInputLayout email;
    TextInputLayout password;
    TextInputLayout repeatedPassword;
    TextInputLayout address;
    Button registerButton;
    ProgressBar loadingProgressBar;
    MaterialCheckBox termsCheckBox;
    TextView termsTextView;

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

        name = view.findViewById(R.id.nameTextView);
        email = view.findViewById(R.id.emailTextView);
        password = view.findViewById(R.id.passwordTextView);
        repeatedPassword = view.findViewById(R.id.repeatPasswordTextView);
        address = view.findViewById(R.id.addressTextView);

        registerButton = view.findViewById(R.id.registerButton);
        loadingProgressBar = view.findViewById(R.id.loading);

        termsCheckBox = view.findViewById(R.id.termsCheckBox);
        termsTextView = view.findViewById(R.id.termsMessage);
        termsTextView.setVisibility(View.INVISIBLE);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = RegisterFragment.this.name.getEditText().getText().toString();
                String email = RegisterFragment.this.email.getEditText().getText().toString();
                String password = RegisterFragment.this.password.getEditText().getText().toString();
                String address = RegisterFragment.this.address.getEditText().getText().toString();
                loggedUser.setName(name);
                loggedUser.setEmail(email);
                loggedUser.setPassword(password);
                loggedUser.setAddress(address);
                Query query = FirebaseDatabase.getInstance().getReference("Usuario").orderByChild("email").equalTo(loggedUser.getEmail());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean exists = false;
                        userList.clear();
                        if (snapshot.exists()) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                Usuario user = ds.getValue(Usuario.class);
                                userList.add(user);
                                exists = true;
                            }
                        }
                        if (!exists) {
                            if (isValidData(loggedUser)) {
                                String key = dbRef.push().getKey();
                                loggedUser.setId(key);
                                dbRef.child(key).setValue(loggedUser);
                                loadingProgressBar.setVisibility(View.VISIBLE);
                                Fragment newFragment = new HomeFragment();
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.fragment, newFragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
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
        });


        if (!loggedUser.getEmail().equals(getString(R.string.guest))) {
            email.getEditText().setText(loggedUser.getEmail());
            password.getEditText().setText(loggedUser.getPassword());
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
        if (!termsCheckBox.isChecked()) {
            termsTextView.setTextColor(Color.RED);
            termsTextView.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }
}