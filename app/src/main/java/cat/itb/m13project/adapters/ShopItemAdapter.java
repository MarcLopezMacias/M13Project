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

import java.util.Locale;

import cat.itb.m13project.R;
import cat.itb.m13project.pojo.Producto;

import static cat.itb.m13project.ConstantVariables.CURRENCY;
import static cat.itb.m13project.ConstantVariables.DEFAULT_AMOUNT;
import static cat.itb.m13project.ConstantVariables.HOME_PRODUCTS;
import static cat.itb.m13project.ConstantVariables.MY_DEFAULT_AMOUNT;
import static com.blankj.utilcode.util.ResourceUtils.getDrawable;


public class ShopItemAdapter extends RecyclerView.Adapter<ShopItemAdapter.ShopItemViewHolder> implements View.OnClickListener {

    private View.OnClickListener listener;

    private static String trim(String toTrim) {

        if (toTrim.length() > MY_DEFAULT_AMOUNT) {
            for (int i = 0; i < MY_DEFAULT_AMOUNT + DEFAULT_AMOUNT; i++) {
                if (toTrim.indexOf(" ", i) > MY_DEFAULT_AMOUNT && toTrim.indexOf(" ", i) != toTrim.lastIndexOf(" ")) {
                    toTrim = toTrim.substring(0, toTrim.indexOf(" ", i));
                    return toTrim.concat("...");
                }
            }
        } else {
            return toTrim;
        }
        return toTrim;
    }

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
        Producto producto = HOME_PRODUCTS.get(position);
        holder.bind(producto);
    }

    @Override
    public int getItemCount() {
        return HOME_PRODUCTS.size();
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
            productNameTextView.setText(trim(producto.getDescripcion()));
            if (producto.getFotos() != null) {
                System.out.println("Binding pics: " + producto.getFotos().getFotos().get(0));
                Picasso.get().load(String.valueOf(producto.getFotos().getFotos().get(0)));
            } else {
                switch (producto.getBloque().toLowerCase()) {
                    case "accesorios":
                        productImageView.setImageDrawable(getDrawable(R.drawable.accesorios));
                        break;
                    case "almacenamiento externo":
                        productImageView.setImageDrawable(getDrawable(R.drawable.almacenamiento_externo));
                        break;
                    case "apple":
                        productImageView.setImageDrawable(getDrawable(R.drawable.apple));
                        break;
                    case "cables & adaptadores":
                        productImageView.setImageDrawable(getDrawable(R.drawable.cables_y_adaptadores));
                        break;
                    case "camaras":
                        productImageView.setImageDrawable(getDrawable(R.drawable.camaras));
                        break;
                    case "componentes":
                        productImageView.setImageDrawable(getDrawable(R.drawable.componentes));
                        break;
                    case "consumibles":
                        productImageView.setImageDrawable(getDrawable(R.drawable.consumibles));
                        break;
                    case "domotica":
                        productImageView.setImageDrawable(getDrawable(R.drawable.domotica));
                        break;
                    case "electrodomesticos pae":
                        productImageView.setImageDrawable(getDrawable(R.drawable.electrodomesticos_pae));
                        break;
                    case "gaming":
                        productImageView.setImageDrawable(getDrawable(R.drawable.gaming));
                        break;
                    case "iluminacion":
                        productImageView.setImageDrawable(getDrawable(R.drawable.iluminacion));
                        break;
                    case "impresoras & scanners":
                        productImageView.setImageDrawable(getDrawable(R.drawable.impresoras_y_scanners));
                        break;
                    case "monitores / televisores":
                        productImageView.setImageDrawable(getDrawable(R.drawable.monitores_televisores));
                        break;
                    case "mouse & touchpad":
                        productImageView.setImageDrawable(getDrawable(R.drawable.mouse_y_touchpad));
                        break;
                    case "networking":
                        productImageView.setImageDrawable(getDrawable(R.drawable.networking));
                        break;
                    case "ocio y tiempo libre":
                        productImageView.setImageDrawable(getDrawable(R.drawable.ocio_y_tiempo_libre));
                        break;
                    case "ordenadores":
                        productImageView.setImageDrawable(getDrawable(R.drawable.ordenadores));
                        break;
                    case "portatiles":
                        productImageView.setImageDrawable(getDrawable(R.drawable.portatiles));
                        break;
                    case "proteccion covid":
                        productImageView.setImageDrawable(getDrawable(R.drawable.proteccion_covid));
                        break;
                    case "proyector & accesorios":
                        productImageView.setImageDrawable(getDrawable(R.drawable.proyectores_y_accesorios));
                        break;
                    case "sais & regletas":
                        productImageView.setImageDrawable(getDrawable(R.drawable.sais_regletas_y_racks));
                        break;
                    case "software":
                        productImageView.setImageDrawable(getDrawable(R.drawable.software));
                        break;
                    case "sonido & multimedia":
                        productImageView.setImageDrawable(getDrawable(R.drawable.sonido_y_multimedia));
                        break;
                    case "tablet & ebook":
                        productImageView.setImageDrawable(getDrawable(R.drawable.tablet_y_ebook));
                        break;
                    case "teclados":
                        productImageView.setImageDrawable(getDrawable(R.drawable.teclados));
                        break;
                    case "telefonia y movilidad":
                        productImageView.setImageDrawable(getDrawable(R.drawable.telefonia_y_movilidad));
                        break;
                    case "tpv":
                        productImageView.setImageDrawable(getDrawable(R.drawable.tpv));
                        break;
                    default:
                        productImageView.setImageDrawable(getDrawable(R.drawable.ic_baseline_broken_image_24));
                        break;
                }
            }
            productPriceTextView.setText(String.format(Locale.ENGLISH, "%.2f", producto.getPrecioFinalProveedor()).concat(" ").concat(CURRENCY));

//            if (producto != null) {
//                if (!(producto.getDescripcion() == null)
//                        && !(producto.getCaracteristicas() == null)
//                        && !(producto.getPrecioFinalProveedor() <= 0)
//                        && !(producto.getDescripcion().isEmpty())
//                        && !String.valueOf(producto.getPrecioFinalProveedor()).isEmpty()
//                ) {
//                    productNameTextView.setText(producto.getDescripcion());
//                    System.out.println("Binding pics: " + producto.getFotos());
//                    if (producto.getFotos() != null) {
//                        Picasso.with(CONTEXT)
//                                .load(String.valueOf(producto.getFotos().get(0)))
//                                .resize(69, 69).into(productImageView);
//                    }
//                    productPriceTextView.setText(String.format(Locale.ENGLISH, "%.2f", producto.getPrecioFinalProveedor()).concat(" ").concat(CURRENCY));
//                }
//            }
        }
    }
}
