package cat.itb.m13project.Fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textview.MaterialTextView;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import cat.itb.m13project.AddedFunctionalities;
import cat.itb.m13project.R;

import static cat.itb.m13project.ConstantVariables.CART_PRODUCTS;
import static cat.itb.m13project.ConstantVariables.CONTEXT;
import static cat.itb.m13project.ConstantVariables.CURRENCY;
import static cat.itb.m13project.ConstantVariables.CURRENT_PRODUCT_HELPER;
import static cat.itb.m13project.ConstantVariables.ERROR;
import static com.blankj.utilcode.util.ResourceUtils.getDrawable;


public class ShopItemFragment extends Fragment {

    static MaterialTextView productPriceTextView;
    static MaterialTextView quantityTextView;
    private static int quantity;
    private static View.OnClickListener subtractListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (quantity <= 1) {
                Toast.makeText(CONTEXT, ERROR, Toast.LENGTH_SHORT).show();
                quantity = 1;
            } else {
                quantity--;
            }
            setValues();
        }
    };
    private static View.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            quantity++;
            setValues();
        }
    };
    MaterialTextView productNameTextView;
    ImageView productImageView;
    MaterialTextView moreSpecsTextTextView;
    SwitchMaterial showMoreSpecsSwitch;
    MaterialTextView moreSpecsTextView;
    MaterialButton addToCartButton;
    MaterialButton subtractButton;
    MaterialButton addButton;

    public ShopItemFragment() {
        // Required empty public constructor
    }

    private static void setValues() {
        quantityTextView.setText(String.valueOf(quantity));
        double precio = quantity * CURRENT_PRODUCT_HELPER.getPrecioFinalProveedor();
        productPriceTextView.setText(String.format(Locale.ENGLISH, "%.2f", precio).concat(" ").concat(CURRENCY));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_shop_item, container, false);

        productNameTextView = v.findViewById(R.id.productNameTextView);
        productImageView = v.findViewById(R.id.productImageSwitcher);
        moreSpecsTextTextView = v.findViewById(R.id.showMoreSpecsTextView);
        showMoreSpecsSwitch = v.findViewById(R.id.showMoreSpecsSwitch);
        moreSpecsTextView = v.findViewById(R.id.moreSpecsTextView);
        addToCartButton = v.findViewById(R.id.addToCartButton);
        productPriceTextView = v.findViewById(R.id.productPriceTextView);

        subtractButton = v.findViewById(R.id.subtractButton);
        quantityTextView = v.findViewById(R.id.countTextView);
        addButton = v.findViewById(R.id.addButton);

        subtractButton.setOnClickListener(subtractListener);
        addButton.setOnClickListener(addListener);

        CONTEXT = getContext();

        productNameTextView.setText(CURRENT_PRODUCT_HELPER.getDescripcion());
        if (CURRENT_PRODUCT_HELPER.getFotos() != null) {
            Bitmap bm = AddedFunctionalities.cargarBitmap(String.valueOf(CURRENT_PRODUCT_HELPER.getFotos().getFotos().get(0)));
            productImageView.setImageBitmap(bm);
        } else {
            productImageView.setImageResource(R.drawable.ic_baseline_broken_image_24);
        }
        moreSpecsTextTextView.setText(R.string.more_specs);
        moreSpecsTextView.setVisibility(View.INVISIBLE);
        showMoreSpecsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    moreSpecsTextView.setText(CURRENT_PRODUCT_HELPER.getCaracteristicas());
                    moreSpecsTextView.setVisibility(View.VISIBLE);
                } else {
                    moreSpecsTextView.setText("");
                    moreSpecsTextView.setVisibility(View.INVISIBLE);
                }
            }
        });

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < quantity; i++) {
                    CART_PRODUCTS.add(CURRENT_PRODUCT_HELPER);
                }
                Toast.makeText(CONTEXT, quantity + " added to Cart", Toast.LENGTH_SHORT).show();
            }
        });
        quantity = 1;
        setValues();

        if (CURRENT_PRODUCT_HELPER.getFotos() != null) {
            System.out.println("Binding pics: " + CURRENT_PRODUCT_HELPER.getFotos().getFotos().get(0));
            Picasso.get().load(String.valueOf(CURRENT_PRODUCT_HELPER.getFotos().getFotos().get(0)));
        } else {
            switch (CURRENT_PRODUCT_HELPER.getBloque().toLowerCase()) {
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

        return v;
    }
}