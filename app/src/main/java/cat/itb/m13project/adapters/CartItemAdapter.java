package cat.itb.m13project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import cat.itb.m13project.R;
import cat.itb.m13project.pojo.Producto;

import static cat.itb.m13project.ConstantVariables.CART_PRODUCTS;
import static cat.itb.m13project.ConstantVariables.CURRENCY;
import static com.blankj.utilcode.util.ResourceUtils.getDrawable;

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
        Producto producto = CART_PRODUCTS.get(position);
        holder.bind(producto);
    }

    @Override
    public int getItemCount() {
        return CART_PRODUCTS.size();
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
            if (producto.getFotos() != null) {
                System.out.println("Binding pics: " + producto.getFotos().getFotos().get(0));
                Picasso.get().load(String.valueOf(producto.getFotos().getFotos().get(0)));
            } else {
                switch (producto.getBloque().toLowerCase()) {
                    case "accesorios":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.accesorios));
                        break;
                    case "almacenamiento externo":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.almacenamiento_externo));
                        break;
                    case "apple":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.apple));
                        break;
                    case "cables & adaptadores":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.cables_y_adaptadores));
                        break;
                    case "camaras":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.camaras));
                        break;
                    case "componentes":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.componentes));
                        break;
                    case "consumibles":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.consumibles));
                        break;
                    case "domotica":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.domotica));
                        break;
                    case "electrodomesticos pae":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.electrodomesticos_pae));
                        break;
                    case "gaming":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.gaming));
                        break;
                    case "iluminacion":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.iluminacion));
                        break;
                    case "impresoras & scanners":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.impresoras_y_scanners));
                        break;
                    case "monitores / televisores":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.monitores_televisores));
                        break;
                    case "mouse & touchpad":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.mouse_y_touchpad));
                        break;
                    case "networking":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.networking));
                        break;
                    case "ocio y tiempo libre":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.ocio_y_tiempo_libre));
                        break;
                    case "ordenadores":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.ordenadores));
                        break;
                    case "portatiles":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.portatiles));
                        break;
                    case "proteccion covid":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.proteccion_covid));
                        break;
                    case "proyector & accesorios":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.proyectores_y_accesorios));
                        break;
                    case "sais & regletas":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.sais_regletas_y_racks));
                        break;
                    case "software":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.software));
                        break;
                    case "sonido & multimedia":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.sonido_y_multimedia));
                        break;
                    case "tablet & ebook":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.tablet_y_ebook));
                        break;
                    case "teclados":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.teclados));
                        break;
                    case "telefonia y movilidad":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.telefonia_y_movilidad));
                        break;
                    case "tpv":
                        productoImageView.setImageDrawable(getDrawable(R.drawable.tpv));
                        break;
                    default:
                        productoImageView.setImageDrawable(getDrawable(R.drawable.ic_baseline_broken_image_24));
                        break;
                }
            }
        }
    }
}
