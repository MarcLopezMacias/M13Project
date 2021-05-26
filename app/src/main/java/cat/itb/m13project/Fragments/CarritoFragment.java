package cat.itb.m13project.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;
import java.util.Locale;

import cat.itb.m13project.R;
import cat.itb.m13project.adapters.CartItemAdapter;
import cat.itb.m13project.payment.PayPalAPI;
import cat.itb.m13project.pojo.Producto;

import static cat.itb.m13project.ConstantVariables.CURRENCY;
import static cat.itb.m13project.ConstantVariables.CURRENT_PRODUCT;
import static cat.itb.m13project.Fragments.HomeFragment.cartProducts;

import static cat.itb.m13project.ConstantVariables.CONTEXT;

public class CarritoFragment extends Fragment {

    RecyclerView recyclerView;
    CartItemAdapter adapter;

    MaterialButton buyButton;

    MaterialTextView infoTextView;

    double totalValue = 0;

    public CarritoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cart, container, false);

        CONTEXT = getContext();

        recyclerView = v.findViewById(R.id.carritoRecyclerView);
        adapter = new CartItemAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                Producto p = cartProducts.get(recyclerView.getChildAdapterPosition(v));
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
        adapter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Producto p = cartProducts.get(recyclerView.getChildAdapterPosition(v));
                cartProducts.remove(p);
                Toast.makeText(CONTEXT, p + " deleted", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        recyclerView.setAdapter(adapter);

        infoTextView = v.findViewById(R.id.cartInfoTextView);
        double totalValue = getTotalCost(cartProducts);
        infoTextView.setText(String.valueOf(cartProducts.size()).concat(" products. " + String.format(Locale.ENGLISH, "%.2f",totalValue).concat(" ").concat(CURRENCY)));

        buyButton.setOnClickListener(buyListener);

        return v;
    }


    private static double getTotalCost(List<Producto> productos) {
        double cost = 0;
        for (Producto p : productos) {
            cost += p.getPrecioFinalProveedor();
        }
        return cost;
    }

    private static View.OnClickListener buyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            
        }
    };
}