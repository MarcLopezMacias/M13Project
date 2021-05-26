package cat.itb.m13project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

import cat.itb.m13project.R;
import cat.itb.m13project.pojo.Producto;

import static cat.itb.m13project.ConstantVariables.CURRENCY;
import static cat.itb.m13project.Fragments.HomeFragment.cartProducts;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ShopItemViewHolder> implements View.OnClickListener {

    private View.OnClickListener onClickListener;
    private View.OnLongClickListener onLongClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    @Override
    public void onClick(View v) {
        if (onClickListener != null) {
            onClickListener.onClick(v);
        }
        if (onLongClickListener != null) {
            onLongClickListener.onLongClick(v);
        }
    }

    @NonNull
    @Override
    public ShopItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_cart_item_view, parent, false);
        v.setOnClickListener(this);
        return new ShopItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopItemViewHolder holder, int position) {
        Producto producto = cartProducts.get(position);
        holder.bind(producto);
    }

    @Override
    public int getItemCount() {
        return cartProducts.size();
    }

    public static class ShopItemViewHolder extends RecyclerView.ViewHolder {

        TextView productoNameTextView;
        TextView productoPriceTextView;
        ImageView productoImageView;

        public ShopItemViewHolder(@NonNull View productoView) {
            super(productoView);
            productoNameTextView = productoView.findViewById(R.id.carrito_item_name_text_view);
            productoPriceTextView = productoView.findViewById(R.id.carrito_item_total_price_text_view);
            productoImageView = productoView.findViewById(R.id.carrito_item_image_view);
        }

        public void bind(final Producto producto) {
            productoNameTextView.setText(producto.getDescripcion());
            productoPriceTextView.setText(String.format(Locale.ENGLISH, "%.2f", producto.getPrecioFinalProveedor()).concat(" ").concat(CURRENCY));

        }
    }
}
