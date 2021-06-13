package cat.itb.m13project;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;

import com.amplitude.api.AmplitudeClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import cat.itb.m13project.pojo.Cart;
import cat.itb.m13project.pojo.Producto;
import cat.itb.m13project.pojo.Usuario;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.MANAGE_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.blankj.utilcode.util.StringUtils.getString;

public class ConstantVariables {

    public static final int REQUEST_EXTERNAL_STORAGE = 69;
    public static final String SANDBOX_ACCOUNT = "spanish@business.com";
    public static final String CLIENT_KEY = "AUCACjUMbAHh_8sboKLPynyXChsCSQYDUxYev7ixGmsr94ct52fEq7SIin7fvAB4RwmkZ7rII-4XAEjL";
    public static final int PAYPAL_REQUEST_CODE = 123;
    public static final int USER_PASSWORD_LENGTH = 7;
    public static final String PROFILE = "Profile";
    public static final String CART = "Cart";
    public static final double IVA = 0.21;
    public static final String CURRENCY = "EUR";
    public static final int DEFAULT_AMOUNT = 1;
    public static final int MY_DEFAULT_AMOUNT = 69;
    public static final String ROOT_URL = "https://app.desyman.com/web/tarifas/";
    public static final String FINAL_URL = "?q=WVdOMGFXOXVQWFJoY21sbVlYTW1ZejB4TURrMU5RPT0";
    public static final String REAL_FINAL_URL = "?q=WVdOMGFXOXVQWFJoY21sbVlYTW1ZejB4TURrMU5RPT0";
    public static final String XML_URL = "xml/";
    public static final String CSV_URL = "csv/";
    public static final String CSV_20_URL = "csv20/";
    public static final String CSV_05_URL = "csvreducido/";
    public static final String EXCEL_URL = "excel/";
    public static final String STOCK_FILE_NAME = "stock.xml";
    public static final String ROOT_DOWNLOAD = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    public static final String LOCAL_FILE_PATH = ROOT_DOWNLOAD + "/" + STOCK_FILE_NAME;
    public static final String UPDATING_STOCK = "Updating Stock";
    public static final String PROVIDER_STOCK_URL = ROOT_URL + XML_URL + FINAL_URL;
    public static final String APP_NAME = getString(R.string.app_name);
    public static final String ERROR = getString(R.string.error);
    public static final String REFRESH_DATABASE = "Don't forget to update the database!";
    public static final String GUEST = getString(R.string.guest);

    // CATEGORIES
    public static final String ACCESORIOS = getString(R.string.accesorios).toUpperCase();
    public static final String ALMACENAMIENTO_EXTERNO = getString(R.string.almacenamiento_externo).toUpperCase();
    public static final String APPLE = getString(R.string.apple).toUpperCase();
    public static final String CABLES_Y_ADAPTADORES = "CABLES & ADAPTADORES";
    public static final String CAMARAS = "CAMARAS";
    public static final String COMPONENTES = getString(R.string.componentes).toUpperCase();
    public static final String CONSUMIBLES = getString(R.string.consumibles).toUpperCase();
    public static final String DOMOTICA = getString(R.string.dom_tica).toUpperCase();
    public static final String ELECTRODOMESTICOS_PAE = getString(R.string.electrodom_esticos_pae).toUpperCase();
    public static final String GAMING = getString(R.string.gaming).toUpperCase();
    public static final String ILUMINACION = getString(R.string.iluminaci_n).toUpperCase();
    public static final String IMPRESORAS_Y_ESCANERES = getString(R.string.impresoras_y_esc_neres).toUpperCase();
    public static final String MONITORES_Y_TELEVISORES = getString(R.string.monitores_y_televisores).toUpperCase();
    public static final String MOUSE_Y_TOUCHPAD = "MOUSE & TOUCHPAD";
    public static final String NETWORKING = getString(R.string.networking).toUpperCase();
    public static final String OCIO_Y_TIEMPO_LIBRE = getString(R.string.ocio_y_tiempo_libre).toUpperCase();
    public static final String ORDENADORES = getString(R.string.ordenadores).toUpperCase();
    public static final String PORTATILES = "PORTATILES";
    public static final String PROTECCION_COVID_19 = getString(R.string.protecci_n_covid_19).toUpperCase();
    public static final String PROYECTORES_Y_ACCESORIOS = "PROYECTOR & ACCESORIOS";
    public static final String SAIS_REGLETAS_Y_RACKS = getString(R.string.sais_regletas_y_racks).toUpperCase();
    public static final String SOFTWARE = getString(R.string.software).toUpperCase();
    public static final String SONIDO_Y_MULTIMEDIA = "SONIDO & MULTIMEDIA";
    public static final String TABLET_Y_E_BOOK = getString(R.string.tablet_y_e_book).toUpperCase();
    public static final String TECLADOS = "TECLADOS";
    public static final String TELEFONIA_Y_MOVILIDAD = getString(R.string.telefonia_y_movilidad).toUpperCase();
    public static final String TPV = getString(R.string.tpv).toUpperCase();

    // OTHER STUFF
    public static final String DEFAULT = "DEFAULT";
    public static final String CODIGO = "codigo";
    public static final String FOTOS = "fotos";
    public static final String FECHA_ALTA = "fecha_alta";
    public static final String BLOQUE = "bloque";
    public static final String CURRENT_PRODUCT = "currentProduct";
    public static final String PRECIO_FINAL_PROVEEDOR = "precioFinalProveedor";
    public static final String STOCK_VALUE = "stock";
    public static final String UPDATE = "UPDATE";
    public static final String NOT_TODAY = "NOT TODAY";
    public static final String CHANNEL_ID = "ISI COMPUTER Channel";
    public static final String DELETING_ALL_PRODUCTS = "DELETING ALL PRODUCTS";
    public static final String[] PERMISSIONS_STORAGE = {
            READ_EXTERNAL_STORAGE,
            WRITE_EXTERNAL_STORAGE,
            INTERNET,
            MANAGE_EXTERNAL_STORAGE,
            READ_PHONE_STATE
    };

    // NOT TOO CONSTANT
    public static FirebaseDatabase DB;
    public static DatabaseReference DB_USER_REF;
    public static DatabaseReference DB_PRODUCTO_REF;
    public static List<Usuario> USER_LIST;
    public static Usuario LOGGED_USER;
    public static Cart CARRITO;
    public static Query QUERY;
    public static List<Producto> HOME_PRODUCTS = new ArrayList<>();
    public static List<Producto> CART_PRODUCTS = new ArrayList<>();
    public static Context CONTEXT;
    public static Activity ACTIVITY;
    public static Producto CURRENT_PRODUCT_HELPER;
    public static AmplitudeClient AMPLITUDE_CLIENT;

}
