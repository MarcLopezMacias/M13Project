package cat.itb.m13project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cat.itb.m13project.R;

public class CarritoProductoAdapter extends RecyclerView.Adapter<CarritoProductoAdapter.ShopProductoViewHolder> implements View.OnClickListener {

    private View.OnClickListener shopProductoListener;

    @Override
    public void onClick(View v) {
        if (shopProductoListener != null) {
            shopProductoListener.onClick(v);
        }
    }

    @NonNull
    @Override
    public ShopProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_shop_producto_view, parent, false);
        v.setOnClickListener(this);
        return new ShopProductoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopProductoViewHolder holder, int position) {
        //holder.bind();
    }

    @Override
    public int getProductoCount() {
        return 0;
    }

    public static class ShopProductoViewHolder extends RecyclerView.ViewHolder {

        TextView productoNameTextView;
        TextView productoPriceTextView;
        ImageView productoImageView;

        public ShopProductoViewHolder(@NonNull View productoView) {
            super(productoView);
            productoNameTextView = productoView.findViewById(R.id.shop_producto_name_text_view);
            productoPriceTextView = productoView.findViewById(R.id.shop_producto_price_text_view);
            productoImageView = productoView.findViewById(R.id.shop_producto_image_view);
        }

        public void bind(final Producto producto) {
            productoNameTextView.setText(producto.getName());
            productoPriceTextView.setText(String.valueOf(producto.getPrice()));
        }
    }
}
