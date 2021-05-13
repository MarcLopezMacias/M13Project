package cat.itb.m13project.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.CarritoProductoViewHolder> implements View.OnClickListener {


    @Override
    public void onClick(View v) {

    }

    @NonNull
    @Override
    public CarritoProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoProductoViewHolder holder, int position) {
    }

    @Override
    public int getProductoCount() {
        return 0;
    }

    class CarritoProductoViewHolder extends RecyclerView.ViewHolder {

        public CarritoProductoViewHolder(@NonNull View productoView) {
            super(productoView);

        }

        public void bind(final Producto producto) {
        }
    }
}
