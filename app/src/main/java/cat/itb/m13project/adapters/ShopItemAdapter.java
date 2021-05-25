package cat.itb.m13project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textview.MaterialTextView;
import com.squareup.picasso.Picasso;

import cat.itb.m13project.R;
import cat.itb.m13project.pojo.Producto;

import static cat.itb.m13project.ConstantVariables.CONTEXT;
import static cat.itb.m13project.Fragments.HomeFragment.homeProductos;


public class ShopItemAdapter extends RecyclerView.Adapter<ShopItemAdapter.ShopItemViewHolder> implements View.OnClickListener {

    private View.OnClickListener listener;

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
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
        Producto producto = homeProductos.get(position);
        holder.bind(producto);
    }

    @Override
    public int getItemCount() {
        return homeProductos.size();
    }

    class ShopItemViewHolder extends RecyclerView.ViewHolder {

        MaterialTextView productNameTextView;
        ImageView productImageView;
        MaterialTextView productPriceTextView;
        MaterialTextView productSpecsTextView;
        MaterialTextView showMoreSpecsTextView;
        SwitchMaterial showMoreSpecsSwitch;
        MaterialTextView moreSpecsTextView;

        public ShopItemViewHolder(@NonNull View productoView) {
            super(productoView);
            productNameTextView = productoView.findViewById(R.id.shop_item_name_text_view);
            productImageView = productoView.findViewById(R.id.shop_item_image_view);
            productPriceTextView = productoView.findViewById(R.id.shop_item_price_text_view);

            productSpecsTextView = productoView.findViewById(R.id.productSpecsTextView);
            showMoreSpecsTextView = productoView.findViewById(R.id.showMoreSpecsTextView);
            showMoreSpecsSwitch = productoView.findViewById(R.id.showMoreSpecsSwitch);
            moreSpecsTextView = productoView.findViewById(R.id.moreSpecsTextView);
        }

        public void bind(final Producto producto) {
            if (producto!= null ) {
                if (!(producto.getDescripcion() == null)
                         && !(producto.getCaracteristicas() == null)
                         && !(producto.getPrecioFinalProveedor() <= 0)
                ) {
                     productNameTextView.setText(producto.getDescripcion());
                    Picasso.with(CONTEXT)
                            .load(producto.getFotos().get(0))
                            .resize(69,69).into(productImageView);
                     productPriceTextView.setText(String.valueOf(producto.getPrecioFinalProveedor()));
                }
            }
        }
    }
}
