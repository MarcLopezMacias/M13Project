package cat.itb.m13project.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textview.MaterialTextView;

import cat.itb.m13project.R;
import cat.itb.m13project.pojo.Producto;


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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopItemViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
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

            productNameTextView.setText(producto.getDescripcion());
            productImageSwitcher.setImageURI(producto.getFotos().get(0));
            productDescriptionTextView.setText(producto.getCaracteristicas());
            String specs;

        }
    }
}
