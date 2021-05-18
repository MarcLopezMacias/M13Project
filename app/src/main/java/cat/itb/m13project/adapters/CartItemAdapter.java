package cat.itb.m13project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cat.itb.m13project.R;
import cat.itb.m13project.pojo.Producto;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ShopItemViewHolder> implements View.OnClickListener {

    private View.OnClickListener shopProductoListener;

    @Override
    public void onClick(View v) {
        if (shopProductoListener != null) {
            shopProductoListener.onClick(v);
        }
    }

    @NonNull
    @Override
    public ShopItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_shop_item_view, parent, false);
        v.setOnClickListener(this);
        return new ShopItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopItemViewHolder holder, int position) {
        //holder.bind();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ShopItemViewHolder extends RecyclerView.ViewHolder {

        TextView productoNameTextView;
        TextView productoPriceTextView;
        ImageView productoImageView;

        public ShopItemViewHolder(@NonNull View productoView) {
            super(productoView);
            productoNameTextView = productoView.findViewById(R.id.shop_item_name_text_view);
            productoPriceTextView = productoView.findViewById(R.id.shop_item_price_text_view);
            productoImageView = productoView.findViewById(R.id.carrito_item_image_view);
        }

        public void bind(final Producto producto) {
            productoNameTextView.setText(producto.getDescripcion());
            productoPriceTextView.setText(String.valueOf(producto.getPrecioFinalProveedor()));
        }
    }
}
