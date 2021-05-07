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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import cat.itb.m13project.MainActivity;
import cat.itb.m13project.R;
import cat.itb.m13project.pojo.*;

import static cat.itb.m13project.MainActivity.db;
import static cat.itb.m13project.MainActivity.dbRef;


public class LoginFragment extends Fragment {

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

        final EditText emailEditText = view.findViewById(R.id.email);
        final EditText passwordEditText = view.findViewById(R.id.password);
        final Button loginButton = view.findViewById(R.id.loginButton);
        final ProgressBar loadingProgressBar = view.findViewById(R.id.loading);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);

                final Usuario userAttempt = new Usuario();
                userAttempt.setEmail(emailEditText.getText().toString().toLowerCase());
                userAttempt.setPassword(passwordEditText.getText().toString());

                Query q = dbRef.orderByChild("email").equalTo(userAttempt.getEmail());
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Toast.makeText(getContext(), snapshot.child("email").toString(), Toast.LENGTH_SHORT).show();
                        Usuario user = new Usuario();

                        Toast.makeText(getContext(), snapshot.getKey(), Toast.LENGTH_SHORT).show();

                        user.setEmail(dbRef.child(snapshot.getKey()).child("email").toString().toLowerCase());
                        user.setPassword(dbRef.child(snapshot.getKey()).child("password").toString());

                        Toast.makeText(getContext(), user.getEmail(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), user.getPassword(), Toast.LENGTH_SHORT).show();

                        if (user.getEmail().equals(userAttempt.getEmail()) && user.getPassword().equals(userAttempt.getPassword())) {
                            Fragment newFragment = new HomeFragment();
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragment, newFragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        } else {
                            loadingProgressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}