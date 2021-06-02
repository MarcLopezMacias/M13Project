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
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cat.itb.m13project.R;
import cat.itb.m13project.adapters.ShopItemAdapter;
import cat.itb.m13project.pojo.Cart;
import cat.itb.m13project.pojo.Producto;

import static cat.itb.m13project.AddedFunctionalities.checkUser;
import static cat.itb.m13project.ConstantVariables.ACCESORIOS;
import static cat.itb.m13project.ConstantVariables.ALMACENAMIENTO_EXTERNO;
import static cat.itb.m13project.ConstantVariables.APPLE;
import static cat.itb.m13project.ConstantVariables.BLOQUE;
import static cat.itb.m13project.ConstantVariables.CABLES_Y_ADAPTADORES;
import static cat.itb.m13project.ConstantVariables.CAMARAS;
import static cat.itb.m13project.ConstantVariables.CARRITO;
import static cat.itb.m13project.ConstantVariables.CART;
import static cat.itb.m13project.ConstantVariables.CODIGO;
import static cat.itb.m13project.ConstantVariables.COMPONENTES;
import static cat.itb.m13project.ConstantVariables.CONSUMIBLES;
import static cat.itb.m13project.ConstantVariables.CONTEXT;
import static cat.itb.m13project.ConstantVariables.CURRENT_PRODUCT;
import static cat.itb.m13project.ConstantVariables.DB;
import static cat.itb.m13project.ConstantVariables.DB_PRODUCTO_REF;
import static cat.itb.m13project.ConstantVariables.DB_USER_REF;
import static cat.itb.m13project.ConstantVariables.DOMOTICA;
import static cat.itb.m13project.ConstantVariables.ELECTRODOMESTICOS_PAE;
import static cat.itb.m13project.ConstantVariables.FECHA_ALTA;
import static cat.itb.m13project.ConstantVariables.GAMING;
import static cat.itb.m13project.ConstantVariables.HOME_PRODUCTS;
import static cat.itb.m13project.ConstantVariables.ILUMINACION;
import static cat.itb.m13project.ConstantVariables.IMPRESORAS_Y_ESCANERES;
import static cat.itb.m13project.ConstantVariables.MONITORES_Y_TELEVISORES;
import static cat.itb.m13project.ConstantVariables.MOUSE_Y_TOUCHPAD;
import static cat.itb.m13project.ConstantVariables.MY_DEFAULT_AMOUNT;
import static cat.itb.m13project.ConstantVariables.NETWORKING;
import static cat.itb.m13project.ConstantVariables.OCIO_Y_TIEMPO_LIBRE;
import static cat.itb.m13project.ConstantVariables.ORDENADORES;
import static cat.itb.m13project.ConstantVariables.PORTATILES;
import static cat.itb.m13project.ConstantVariables.PRECIO_FINAL_PROVEEDOR;
import static cat.itb.m13project.ConstantVariables.PROFILE;
import static cat.itb.m13project.ConstantVariables.PROTECCION_COVID_19;
import static cat.itb.m13project.ConstantVariables.PROYECTORES_Y_ACCESORIOS;
import static cat.itb.m13project.ConstantVariables.QUERY;
import static cat.itb.m13project.ConstantVariables.SAIS_REGLETAS_Y_RACKS;
import static cat.itb.m13project.ConstantVariables.SOFTWARE;
import static cat.itb.m13project.ConstantVariables.SONIDO_Y_MULTIMEDIA;
import static cat.itb.m13project.ConstantVariables.STOCK_VALUE;
import static cat.itb.m13project.ConstantVariables.TABLET_Y_E_BOOK;
import static cat.itb.m13project.ConstantVariables.TECLADOS;
import static cat.itb.m13project.ConstantVariables.TELEFONIA_Y_MOVILIDAD;
import static cat.itb.m13project.ConstantVariables.TPV;
import static cat.itb.m13project.ConstantVariables.USER_LIST;

public class HomeFragment extends Fragment {

    NavigationView navigationView;
    private RecyclerView recyclerView;
    private ShopItemAdapter adapter;
    private DrawerLayout drawerLayout;
    private ExtendedFloatingActionButton sortButton;

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

        CONTEXT = getContext();

        if (USER_LIST == null) {
            USER_LIST = new ArrayList<>();
        }

        CARRITO = new Cart();

        //addProductsWithImages();

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

        DB = FirebaseDatabase.getInstance();
        DB_USER_REF = DB.getReference("Usuario");
        DB_PRODUCTO_REF = DB.getReference("Producto");

        MaterialButton offersButton = v.findViewById(R.id.offersButton);
        MaterialButton outletButton = v.findViewById(R.id.outletButton);
        MaterialButton destacadosButton = v.findViewById(R.id.destacadosButton);

        offersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QUERY = DB_PRODUCTO_REF.orderByChild(PRECIO_FINAL_PROVEEDOR).limitToFirst(MY_DEFAULT_AMOUNT);
                cargarDatos();
            }
        });
        outletButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QUERY = DB_PRODUCTO_REF.orderByChild(FECHA_ALTA).limitToLast(MY_DEFAULT_AMOUNT);
                cargarDatos();
            }
        });
        destacadosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QUERY = DB_PRODUCTO_REF.orderByChild(STOCK_VALUE).limitToLast(MY_DEFAULT_AMOUNT);
                cargarDatos();
            }
        });

        sortButton = v.findViewById(R.id.sortButton);
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("SORTIN");
            }
        });

        // DRAWER
        drawerLayout = v.findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                Producto p = HOME_PRODUCTS.get(recyclerView.getChildAdapterPosition(v));
                b.putSerializable(CURRENT_PRODUCT, p);

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

        try {
            wait(20000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        cargarDatos();

        // NAVIGATION VIEW
        navigationView = v.findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem producto) {
                switch (producto.getTitle().toString().toLowerCase()) {
                    case "accesorios":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(ACCESORIOS).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();
                        break;
                    case "almacenamiento externo":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(ALMACENAMIENTO_EXTERNO).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "apple":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(APPLE).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "cables y adaptadores":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(CABLES_Y_ADAPTADORES).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "cámaras":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(CAMARAS).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "componentes":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(COMPONENTES).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "consumibles":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(CONSUMIBLES).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "domótica":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(DOMOTICA).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "electrodomésticos pae":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(ELECTRODOMESTICOS_PAE).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "gaming":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(GAMING).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "iluminación":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(ILUMINACION).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "impresoras y escáneres":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(IMPRESORAS_Y_ESCANERES).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "monitores y televisores":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(MONITORES_Y_TELEVISORES).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "mouse y touchpad":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(MOUSE_Y_TOUCHPAD).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "networking":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(NETWORKING).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "ocio y tiempo libre":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(OCIO_Y_TIEMPO_LIBRE).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "ordenadores":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(ORDENADORES).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "portátiles":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(PORTATILES).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "protección covid-19":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(PROTECCION_COVID_19).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "proyectores y accesorios":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(PROYECTORES_Y_ACCESORIOS).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "sais, regletas y racks":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE.toLowerCase()).equalTo(SAIS_REGLETAS_Y_RACKS).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "software":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(SOFTWARE).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "sonido y multimedia":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(SONIDO_Y_MULTIMEDIA).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "tablet y e-book":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(TABLET_Y_E_BOOK).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "teclados":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(TECLADOS).limitToFirst(MY_DEFAULT_AMOUNT);
                        System.out.println(TECLADOS);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "telefonia y movilidad":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(TELEFONIA_Y_MOVILIDAD).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    case "tpv":
                        QUERY = DB_PRODUCTO_REF.orderByChild(BLOQUE).equalTo(TPV).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();

                        break;
                    default:
                        QUERY = DB_PRODUCTO_REF.orderByChild(CODIGO).limitToFirst(MY_DEFAULT_AMOUNT).limitToFirst(MY_DEFAULT_AMOUNT);
                        cargarDatos();
                        drawerLayout.close();
                        break;
                }
                return false;
            }
        });

        checkUser();

        return v;
    }

    private void cargarDatos() {
        if (QUERY == null) {
            QUERY = DB_PRODUCTO_REF.orderByChild(CODIGO).limitToFirst(MY_DEFAULT_AMOUNT);
            Toast.makeText(CONTEXT, "LOADING PRODUCTS", Toast.LENGTH_SHORT).show();
        }
        HOME_PRODUCTS.clear();
        QUERY.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Producto producto = dataSnapshot.getValue(Producto.class);
                    if (!HOME_PRODUCTS.contains(producto)) {
                        HOME_PRODUCTS.add(producto);
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