package cat.itb.m13project.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;

import cat.itb.m13project.R;
import cat.itb.m13project.pojo.Cart;

import static cat.itb.m13project.AddedFunctionalities.checkUser;
import static cat.itb.m13project.ConstantVariables.CARRITO;
import static cat.itb.m13project.ConstantVariables.CONTEXT;
import static cat.itb.m13project.ConstantVariables.GUEST;
import static cat.itb.m13project.ConstantVariables.LOGGED_USER;

public class WelcomeFragment extends Fragment {

    TextView titleTextView;
    MaterialButton loginButton;
    MaterialButton registerButton;
    MaterialButton forgotPasswordButton;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_welcome, container, false);

        CONTEXT = getContext();

        titleTextView = v.findViewById(R.id.title);

        CARRITO = new Cart();

        loginButton = v.findViewById(R.id.loginButton);
        registerButton = v.findViewById(R.id.registerButton);
        forgotPasswordButton = v.findViewById(R.id.forgotPasswordButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LOGGED_USER == null) {
                    Toast.makeText(CONTEXT, "WAIT A SEC", Toast.LENGTH_SHORT).show();
                } else if (LOGGED_USER.getEmail().equals(GUEST)) {
                    Navigation.findNavController(v).navigate(R.id.action_welcomeFragment_to_loginFragment);
                } else {
                    System.out.println("CURRENT USER IS: " + LOGGED_USER.getEmail());
                    Navigation.findNavController(v).navigate(R.id.action_welcomeFragment_to_homeFragment);
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LOGGED_USER == null) {
                    Toast.makeText(CONTEXT, "WAIT A SEC", Toast.LENGTH_SHORT).show();
                } else if (LOGGED_USER.getEmail().equals(GUEST)) {
                    Navigation.findNavController(v).navigate(R.id.action_welcomeFragment_to_registerFragment);
                } else {
                    System.out.println("CURRENT USER IS: " + LOGGED_USER.getEmail());
                    Navigation.findNavController(v).navigate(R.id.action_welcomeFragment_to_homeFragment);
                }
            }
        });

        checkUser();
        //addProductsWithImages();

        return v;
    }

}