package cat.itb.m13project;

import android.content.Context;
import android.os.Environment;

import static com.blankj.utilcode.util.StringUtils.getString;

public class ConstantVariables {

    public static final String CLIENT_KEY = "AY4Lii5lOmSPYds58D3P_aueUOQrJi2a5rreDV3VEQWx78qZ1hHGYWo4PmaFQ3OCG7-nzAZe7GOe_jSf";
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

    // CATEGORIES
    public static final String ACCESORIOS = getString(R.string.accesorios).toUpperCase();
    public static final String CAPTURADORAS = getString(R.string.capturadoras).toUpperCase();
    public static final String CARGADORES = getString(R.string.cargadores).toUpperCase();
    public static final String ERGONOMIA = getString(R.string.ergonom_a).toUpperCase();
    public static final String GRABADORA_EXTERNA = getString(R.string.grabadora_externa).toUpperCase();
    public static final String HUB_USB = getString(R.string.hub_usb).toUpperCase();
    public static final String KVM_SPLITTER = getString(R.string.kvm_splitter).toUpperCase();
    public static final String LIMPIEZA = getString(R.string.limpieza).toUpperCase();
    public static final String PILAS_BATERIAS_Y_CARGADORES = getString(R.string.pilas_bater_as_y_cargadores).toUpperCase();
    public static final String POWERBANK = getString(R.string.powerbank).toUpperCase();
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
    public static Context CONTEXT;
    public static Context ACTIVITY;


}
