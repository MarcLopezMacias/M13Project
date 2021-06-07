package cat.itb.m13project.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import cat.itb.m13project.R;
import cat.itb.m13project.SaveSharedPreference;
import cat.itb.m13project.pojo.Usuario;

import static cat.itb.m13project.ConstantVariables.CONTEXT;
import static cat.itb.m13project.ConstantVariables.LOGGED_USER;
import static cat.itb.m13project.ConstantVariables.USER_LIST;


public class LoginFragment extends Fragment {

    EditText emailEditText;
    EditText passwordEditText;
    MaterialButton loginButton;
    ProgressBar loadingProgressBar;
    MaterialButton forgottenPasswordButton;
    ValueEventListener forgotListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            USER_LIST.clear();
            if (snapshot.exists()) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Usuario user = ds.getValue(Usuario.class);
                    USER_LIST.add(user);
                }
            }
            if (USER_LIST.size() == 1) {
                if (USER_LIST.get(0).getEmail().toLowerCase().equals(LOGGED_USER.getEmail())) {
                    Toast.makeText(getContext(), USER_LIST.get(0).getForgottenPasswordHint(), Toast.LENGTH_SHORT).show();
                }
            } else {
                loadingProgressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), getString(R.string.account_doesnt_exist), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CONTEXT = getContext();

        emailEditText = view.findViewById(R.id.email);
        passwordEditText = view.findViewById(R.id.password);
        loginButton = view.findViewById(R.id.loginButton);
        loadingProgressBar = view.findViewById(R.id.loading);
        forgottenPasswordButton = view.findViewById(R.id.forgotPasswordButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                LOGGED_USER.setEmail(emailEditText.getText().toString().toLowerCase());
                LOGGED_USER.setPassword(passwordEditText.getText().toString());
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
                        if (USER_LIST.size() >= 1) {
                            for (int i = 0; i < USER_LIST.size(); i++) {
                                if (USER_LIST.get(i).getEmail().toLowerCase().equals(LOGGED_USER.getEmail()) && USER_LIST.get(i).getPassword().equals(LOGGED_USER.getPassword())) {
                                    LOGGED_USER = USER_LIST.get(i);
                                    SaveSharedPreference.setUserName(getContext(), LOGGED_USER.getEmail());
                                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment);
                                }
                            }
                        } else {
                            loadingProgressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getContext(), "You must register first", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        forgottenPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                if (!email.isEmpty()) {
                    LOGGED_USER.setEmail(email);
                    Query query = FirebaseDatabase.getInstance().getReference("Usuario").orderByChild("email").equalTo(LOGGED_USER.getEmail());
                    query.addListenerForSingleValueEvent(forgotListener);
                } else {
                    Toast.makeText(getContext(), getString(R.string.invalid_email), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}