package cat.itb.m13project.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import cat.itb.m13project.R;

public class HomeFragment extends Fragment {

    NavigationView navigationView;
    MaterialToolbar toolbar;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        navigationView = v.findViewById(R.id.navigationView);
        toolbar = v.findViewById(R.id.mainToolbar);

        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);

        return v;
    }
}