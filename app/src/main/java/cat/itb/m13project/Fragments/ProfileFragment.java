package cat.itb.m13project.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButton;

import cat.itb.m13project.R;
import cat.itb.m13project.data.base.ProviderWebServices2;

import static cat.itb.m13project.MainActivity.loggedUser;

public class ProfileFragment extends Fragment {

    TextView usernameTextView;

    MaterialButton updateProfile;
    MaterialButton updateProducts;

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

        usernameTextView = v.findViewById(R.id.usernameTextView);
        updateProfile = v.findViewById(R.id.editProfileButton);
        updateProducts = v.findViewById(R.id.updateStock);

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new RegisterFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        updateProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ProviderWebServices2.class);
                startActivity(i);
            }
        });

        return v;
    }
}