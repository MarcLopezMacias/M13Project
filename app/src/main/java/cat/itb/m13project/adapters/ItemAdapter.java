package cat.itb.m13project.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cat.itb.m13project.pojo.Producto;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.CartItemViewHolder> implements View.OnClickListener {


    @Override
    public void onClick(View v) {

    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CartItemViewHolder extends RecyclerView.ViewHolder {

        public CartItemViewHolder(@NonNull View productoView) {
            super(productoView);

        }

        public void bind(final Producto producto) {
        }
    }
}
