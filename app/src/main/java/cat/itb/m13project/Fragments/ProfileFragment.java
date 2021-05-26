package cat.itb.m13project.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import cat.itb.m13project.R;

import static cat.itb.m13project.ConstantVariables.CONTEXT;
import static cat.itb.m13project.Fragments.StockFragment.updateListener;

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

        CONTEXT = getContext();

        usernameTextView = v.findViewById(R.id.usernameTextView);
        updateProfile = v.findViewById(R.id.editProfileButton);
        updateProducts = v.findViewById(R.id.updateStock);

        // updateProfile.setOnClickListener(updateStockListener);
        updateProducts.setOnClickListener(updateListener);
        return v;
    }
}