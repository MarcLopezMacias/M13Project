package cat.itb.m13project.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import cat.itb.m13project.R;

import static android.view.View.VISIBLE;
import static android.view.View.inflate;
import static cat.itb.m13project.ConstantVariables.CONTEXT;
import static cat.itb.m13project.ConstantVariables.LOGGED_USER;
import static cat.itb.m13project.Fragments.StockFragment.updateListener;

public class ProfileFragment extends Fragment {

    MaterialTextView usernameTextView;

    MaterialButton updateProfile;
    MaterialButton updateProducts;
    MaterialButton developerButton;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        CONTEXT = getContext();

        usernameTextView = v.findViewById(R.id.usernameTextView);
        updateProfile = v.findViewById(R.id.editProfileButton);
        updateProducts = v.findViewById(R.id.updateStock);
        developerButton = v.findViewById(R.id.developerButton);

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_profileFragment2_to_registerFragment2);
            }
        });
        updateProducts.setOnClickListener(updateListener);

        if (checkAdmin()) {
            developerButton.setVisibility(VISIBLE);
            developerButton.setFocusable(true);
            developerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).navigate(R.id.action_profileFragment2_to_stockFragment);
                }
            });
        } else {
            developerButton.setVisibility(View.INVISIBLE);
            developerButton.setFocusable(false);
        }


        return v;
    }

    private static boolean checkAdmin() {
        if (LOGGED_USER != null) {
            return LOGGED_USER.getEmail().equals("e@e.e");
        }
        return false;
    }
}