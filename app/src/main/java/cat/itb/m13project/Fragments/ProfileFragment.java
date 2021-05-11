package cat.itb.m13project.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import cat.itb.m13project.R;

import static cat.itb.m13project.MainActivity.loggedUser;

public class ProfileFragment extends Fragment {

    TextView usernameTextView;

    MaterialButton updateProfile;

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

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putSerializable("currentUser", loggedUser);

            }
        });

        return v;
    }
}