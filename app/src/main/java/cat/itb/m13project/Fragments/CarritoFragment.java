package cat.itb.m13project.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import cat.itb.m13project.R;
import cat.itb.m13project.adapters.CartItemAdapter;
import cat.itb.m13project.pojo.Producto;

import static cat.itb.m13project.ConstantVariables.APP_NAME;
import static cat.itb.m13project.ConstantVariables.CART;
import static cat.itb.m13project.ConstantVariables.CLIENT_KEY;
import static cat.itb.m13project.ConstantVariables.CONTEXT;
import static cat.itb.m13project.ConstantVariables.CURRENCY;
import static cat.itb.m13project.ConstantVariables.CURRENT_PRODUCT;
import static cat.itb.m13project.ConstantVariables.PAYPAL_REQUEST_CODE;
import static cat.itb.m13project.Fragments.HomeFragment.cartProducts;

public class CarritoFragment extends Fragment {

    private static PayPalConfiguration config = new PayPalConfiguration()
            // Start with mock environment.  When ready,
            // switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            // on below line we are passing a client id.
            .clientId(CLIENT_KEY);
    private MaterialTextView paymentTV;
    private RecyclerView recyclerView;
    private View.OnClickListener buyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Creating a paypal payment on below line.
            PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(getTotalCost(cartProducts))), CURRENCY, APP_NAME.concat("").concat(CART).concat(" ").concat(String.valueOf(Calendar.getInstance().getTime())),
                    PayPalPayment.PAYMENT_INTENT_SALE);

            // Creating Paypal Payment activity intent
            Intent intent = new Intent(CONTEXT, PaymentActivity.class);

            //putting the paypal configuration to the intent
            intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

            // Puting paypal payment to the intent
            intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

            // Starting the intent activity for result
            // the request code will be used on the method onActivityResult
            startActivityForResult(intent, PAYPAL_REQUEST_CODE);
        }
    };


    public CarritoFragment() {
        // Required empty public constructor
    }

    private static double getTotalCost(List<Producto> productos) {
        double cost = 0;
        for (Producto p : productos) {
            cost += p.getPrecioFinalProveedor();
        }
        return cost;
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
        CartItemAdapter adapter = new CartItemAdapter();
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

        MaterialTextView infoTextView = v.findViewById(R.id.cartInfoTextView);
        double totalValue = getTotalCost(cartProducts);
        infoTextView.setText(String.valueOf(cartProducts.size()).concat(" products. " + String.format(Locale.ENGLISH, "%.2f", totalValue).concat(" ").concat(CURRENCY)));

        paymentTV = v.findViewById(R.id.idTVStatus);

        MaterialButton buyButton = v.findViewById(R.id.buyButton);
        buyButton.setOnClickListener(buyListener);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // If the result is from paypal
        if (requestCode == PAYPAL_REQUEST_CODE) {

            // If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {

                // Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                // if confirmation is not null
                if (confirm != null) {
                    try {
                        // Getting the payment details
                        String paymentDetails = confirm.toJSONObject().toString(4);
                        // on below line we are extracting json response and displaying it in a text view.
                        JSONObject payObj = new JSONObject(paymentDetails);
                        String payID = payObj.getJSONObject("response").getString("id");
                        String state = payObj.getJSONObject("response").getString("state");
                        paymentTV.setText("".concat("Payment ".concat(state).concat("\n with payment id is ").concat(payID)));
                    } catch (JSONException e) {
                        // handling json exception on below line
                        Log.e("Error", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // on below line we are checking the payment status.
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                // on below line when the invalid paypal config is submitted.
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }
}