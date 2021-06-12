package cat.itb.m13project.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplitude.api.Amplitude;
import com.amplitude.api.AmplitudeClient;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.paypal.checkout.approve.Approval;
import com.paypal.checkout.approve.OnApprove;
import com.paypal.checkout.cancel.OnCancel;
import com.paypal.checkout.createorder.CreateOrder;
import com.paypal.checkout.createorder.CreateOrderActions;
import com.paypal.checkout.createorder.CurrencyCode;
import com.paypal.checkout.createorder.OrderIntent;
import com.paypal.checkout.createorder.UserAction;
import com.paypal.checkout.error.ErrorInfo;
import com.paypal.checkout.error.OnError;
import com.paypal.checkout.order.Amount;
import com.paypal.checkout.order.AppContext;
import com.paypal.checkout.order.CaptureOrderResult;
import com.paypal.checkout.order.OnCaptureComplete;
import com.paypal.checkout.order.Order;
import com.paypal.checkout.order.PurchaseUnit;
import com.paypal.checkout.paymentbutton.PaymentButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cat.itb.m13project.R;
import cat.itb.m13project.adapters.CartItemAdapter;
import cat.itb.m13project.pojo.Producto;

import static cat.itb.m13project.ConstantVariables.CARRITO;
import static cat.itb.m13project.ConstantVariables.CART_PRODUCTS;
import static cat.itb.m13project.ConstantVariables.CONTEXT;
import static cat.itb.m13project.ConstantVariables.CURRENCY;
import static cat.itb.m13project.ConstantVariables.CURRENT_PRODUCT_HELPER;
import static cat.itb.m13project.ConstantVariables.GUEST;
import static cat.itb.m13project.ConstantVariables.LOGGED_USER;

public class CarritoFragment extends Fragment {

    private PaymentButton payPalButton;
    private RecyclerView recyclerView;
    private View.OnClickListener buyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (LOGGED_USER.getEmail() == null) {
                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_welcomeFragment);
            } else if (LOGGED_USER.getEmail().equals(GUEST) || LOGGED_USER.getName().equals(GUEST)) {
                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_welcomeFragment);
            } else {
                payPalButton.setVisibility(View.VISIBLE);
                payPalButton.setup(
                        new CreateOrder() {
                            @Override
                            public void create(@NonNull CreateOrderActions createOrderActions) {
                                ArrayList purchaseUnits = new ArrayList<>();
                                purchaseUnits.add(
                                        new PurchaseUnit.Builder()
                                                .amount(
                                                        new Amount.Builder()
                                                                .currencyCode(CurrencyCode.EUR)
                                                                .value(String.valueOf(getTotalCost(CARRITO.getCarrito())))
                                                                .build()
                                                )
                                                .build()
                                );
                                Order order = new Order(
                                        OrderIntent.CAPTURE,
                                        new AppContext.Builder()
                                                .userAction(UserAction.PAY_NOW)
                                                .build(),
                                        purchaseUnits
                                );
                                createOrderActions.create(order, (CreateOrderActions.OnOrderCreated) null);
                            }
                        },

                        new OnApprove() {
                            @Override
                            public void onApprove(@NonNull Approval approval) {
                                approval.getOrderActions().capture(new OnCaptureComplete() {
                                    @Override
                                    public void onCaptureComplete(@NonNull CaptureOrderResult result) {
                                        String msg = String.format("CaptureOrderResult: %s", result);
                                        Log.i("CaptureOrder", msg);
                                        Toast.makeText(CONTEXT, msg, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        },

                        new OnCancel() {
                            @Override
                            public void onCancel() {
                                String msg = "Buyer cancelled the PayPal experience.";
                                Log.d("OnCancel", msg);
                                Toast.makeText(CONTEXT, msg, Toast.LENGTH_SHORT).show();
                            }
                        },

                        new OnError() {
                            @Override
                            public void onError(@NonNull ErrorInfo errorInfo) {
                                String msg = String.format("Error: %s", errorInfo);
                                Log.d("OnError", msg);
                                System.out.println("ERROR ON PAYPAL TRANSACTION: " + msg);
                                Toast.makeText(CONTEXT, msg, Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
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
                CURRENT_PRODUCT_HELPER = CART_PRODUCTS.get(recyclerView.getChildAdapterPosition(v));
                Navigation.findNavController(v).navigate(R.id.action_carritoFragment_to_shopItemFragment2);
            }
        });
        adapter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Producto p = CART_PRODUCTS.get(recyclerView.getChildAdapterPosition(v));
                CART_PRODUCTS.remove(p);
                Toast.makeText(CONTEXT, p + " deleted", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        recyclerView.setAdapter(adapter);

        MaterialTextView infoTextView = v.findViewById(R.id.cartInfoTextView);
        double totalValue = getTotalCost(CART_PRODUCTS);
        infoTextView.setText(String.valueOf(CART_PRODUCTS.size()).concat(" products. " + String.format(Locale.ENGLISH, "%.2f", totalValue).concat(" ").concat(CURRENCY)));

        payPalButton = v.findViewById(R.id.payPalButton);
        payPalButton.setVisibility(View.INVISIBLE);

        MaterialButton buyButtton;
        buyButtton = v.findViewById(R.id.buyButton);
        buyButtton.setOnClickListener(buyListener);

        return v;
    }
}