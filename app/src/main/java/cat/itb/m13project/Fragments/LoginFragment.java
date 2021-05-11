package cat.itb.m13project.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import cat.itb.m13project.R;
import cat.itb.m13project.pojo.Usuario;

import static cat.itb.m13project.MainActivity.loggedUser;
import static cat.itb.m13project.MainActivity.userList;


public class LoginFragment extends Fragment {

    EditText emailEditText;
    EditText passwordEditText;
    Button loginButton;
    ProgressBar loadingProgressBar;
    ValueEventListener eventValueListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            userList.clear();
            if (snapshot.exists()) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Usuario user = ds.getValue(Usuario.class);
                    userList.add(user);
                }
            }
            if (userList.size() == 1) {
                if (userList.get(0).getEmail().toLowerCase().equals(loggedUser.getEmail()) && userList.get(0).getPassword().equals(loggedUser.getPassword())) {
                    loggedUser = userList.get(0);
                    Fragment newFragment = new HomeFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment, newFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    loadingProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(), "User/Password is not correct", Toast.LENGTH_SHORT).show();
                }
            } else {
                loadingProgressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "You must register first", Toast.LENGTH_SHORT).show();
                Fragment newFragment = new RegisterFragment();
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

        emailEditText = view.findViewById(R.id.email);
        passwordEditText = view.findViewById(R.id.password);
        loginButton = view.findViewById(R.id.loginButton);
        loadingProgressBar = view.findViewById(R.id.loading);

        if (!(loggedUser.getEmail().equals(getString(R.string.guest)))) {
            emailEditText.setText(loggedUser.getEmail());
            passwordEditText.setText(loggedUser.getPassword());
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loggedUser.setEmail(emailEditText.getText().toString().toLowerCase());
                loggedUser.setPassword(passwordEditText.getText().toString());
                Query query = FirebaseDatabase.getInstance().getReference("Usuario").orderByChild("email").equalTo(loggedUser.getEmail());
                query.addListenerForSingleValueEvent(eventValueListener);
                System.out.println("WOW");
            }
        });
    }

}