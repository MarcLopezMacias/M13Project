package cat.itb.m13project.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cat.itb.m13project.R;
import cat.itb.m13project.adapters.ShopItemAdapter;
import cat.itb.m13project.pojo.Producto;

import static cat.itb.m13project.ConstantVariables.CART;
import static cat.itb.m13project.ConstantVariables.PROFILE;
import static cat.itb.m13project.ConstantVariables.SHOW_ME_DEFAULT_AMOUNT;
import static cat.itb.m13project.MainActivity.dbProductoRef;

public class HomeFragment extends Fragment {

    NavigationView navigationView;

    RecyclerView recyclerView;
    ShopItemAdapter adapter;

    DrawerLayout drawerLayout;

    ActionBarDrawerToggle drawerToggle;

    public static List<Producto> homeProductos;
    Query filter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        filter = dbProductoRef.orderByChild("codigo");

        setHasOptionsMenu(true);

        // TOOLBAR
        MaterialToolbar toolbar = v.findViewById(R.id.mainToolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem producto) {
                System.out.println(producto.toString());
                Fragment newFragment;
                FragmentManager fragmentManager;
                FragmentTransaction fragmentTransaction;
                switch (producto.toString()) {
                    case PROFILE:
                        newFragment = new ProfileFragment();
                        fragmentManager = getFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment, newFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                    case CART:
                        newFragment = new CarritoFragment();
                        fragmentManager = getFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment, newFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                }
                return false;
            }

        });

        // DRAWER
        drawerLayout = v.findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // NAVIGATION VIEW
        navigationView = v.findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem producto) {
                Toast.makeText(getContext(), "WTF", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // RECYCLER
        recyclerView = v.findViewById(R.id.main_recycler_view);
        adapter = new ShopItemAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                Producto p = homeProductos.get(recyclerView.getChildAdapterPosition(v));
                b.putSerializable("currentProduct", p);

                Fragment newFragment;
                FragmentManager fragmentManager;
                FragmentTransaction fragmentTransaction;

                newFragment = new ShopItemFragment();
                newFragment.setArguments(b);
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        recyclerView.setAdapter(adapter);

        homeProductos = new ArrayList<>();
        cargarDatos();

        return v;
    }

    private void cargarDatos() {
        filter.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Producto producto = dataSnapshot.getValue(Producto.class);
                    if (!homeProductos.contains(producto)) {
                        homeProductos.add(producto);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}