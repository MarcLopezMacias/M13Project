package cat.itb.m13project;

import android.os.Environment;

import com.blankj.utilcode.util.PathUtils;

import static com.blankj.utilcode.util.StringUtils.getString;

public class ConstantVariables {

    public static final int USER_PASSWORD_LENGTH = 7;
    public static final String PROFILE = "Profile";
    public static final String CART = "Cart";

    public static final double IVA = 0.21;
    public static final String CURRENCY = "EUR";
    public static final int DEFAULT_AMOUNT = 1;

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


}
