package cat.itb.m13project.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import cat.itb.m13project.R;

import cat.itb.m13project.pojo.*;

import static cat.itb.m13project.MainActivity.dbRef;

public class RegisterFragment extends Fragment {

    protected static final int PASSWORD_LENGTH = 7;


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
        final EditText nameEditText = view.findViewById(R.id.nameEditText);
        final EditText emailEditText = view.findViewById(R.id.emailEditText);
        final EditText passwordEditText = view.findViewById(R.id.passwordEditText);
        final EditText repeatPasswordEditText = view.findViewById(R.id.repeatPasswordEditText);
        final EditText addressEditText = view.findViewById(R.id.addressEditText);

        final Button registerButton = view.findViewById(R.id.registerButton);
        final ProgressBar loadingProgressBar = view.findViewById(R.id.loading);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emailEditText.getText().toString().contains("@")) {
                    emailEditText.setBackgroundColor(0xFF2800);
                } else {
                    String key = dbRef.push().getKey();
                    Usuario user = new Usuario(
                            key,
                            nameEditText.getText().toString(),
                            emailEditText.getText().toString(),
                            passwordEditText.getText().toString(),
                            addressEditText.getText().toString());
                    dbRef.child(key).setValue(user);
                    loadingProgressBar.setVisibility(View.VISIBLE);
                    Fragment newFragment = new HomeFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment, newFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

            }
        });
    }
}