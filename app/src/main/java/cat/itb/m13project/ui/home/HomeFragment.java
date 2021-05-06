package cat.itb.m13project.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import cat.itb.m13project.R;
import cat.itb.m13project.adapters.ShopItemAdapter;

public class HomeFragment extends Fragment {

    NavigationView navigationView;

    RecyclerView recyclerView;
    ShopItemAdapter adapter;

    DrawerLayout drawerLayout;

    ActionBarDrawerToggle drawerToggle;


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

        drawerLayout = v.findViewById(R.id.drawerLayout);
        navigationView = v.findViewById(R.id.navigation_view);

        drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, R.string.open, R.string.close);

        // DRAWER
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(getContext(), "WTF", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

//        recyclerView = v.findViewById(R.id.main_recycler_view);
//        adapter = new ShopItemAdapter();
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setReverseLayout(true);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(adapter);

        return v;
    }
}