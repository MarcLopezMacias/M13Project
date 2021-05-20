package cat.itb.m13project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textview.MaterialTextView;

import cat.itb.m13project.R;
import cat.itb.m13project.pojo.Producto;

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
        ImageSwitcher productImageSwitcher;
        MaterialTextView productDescriptionTextView;
        MaterialTextView productSpecsTextView;
        MaterialTextView showMoreSpecsTextView;
        SwitchMaterial showMoreSpecsSwitch;
        MaterialTextView moreSpecsTextView;

        public ShopItemViewHolder(@NonNull View productoView) {
            super(productoView);
            productNameTextView = productoView.findViewById(R.id.productNameTextView);
            productImageSwitcher = productoView.findViewById(R.id.productImageSwitcher);
            productDescriptionTextView = productoView.findViewById(R.id.productDescriptionTextView);

            productSpecsTextView = productoView.findViewById(R.id.productSpecsTextView);
            showMoreSpecsTextView = productoView.findViewById(R.id.showMoreSpecsTextView);
            showMoreSpecsSwitch = productoView.findViewById(R.id.showMoreSpecsSwitch);
            moreSpecsTextView = productoView.findViewById(R.id.moreSpecsTextView);
        }

        public void bind(final Producto producto) {
            if (producto!= null ) {
                if (!(producto.getDescripcion() == null) && !(producto.getCaracteristicas() == null) && !(producto.getPrecioFinalProveedor() <= 0)) {
                    productNameTextView.setText(producto.getDescripcion());
                    productImageSwitcher.setImageURI(producto.getFotos().get(0));
                    productDescriptionTextView.setText(producto.getCaracteristicas());
                }
            }
        }
    }
}
