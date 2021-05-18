package cat.itb.m13project.provider;

import android.os.StrictMode;
import android.util.Log;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cat.itb.m13project.pojo.Item;

import static cat.itb.m13project.ConstantVariables.LOCAL_FILE_PATH;
import static cat.itb.m13project.ConstantVariables.PROVIDER_STOCK_URL;

public class ProviderWebServices3 {

//    public static void main(String[] args) {
//
//        //defaultModule();
//
//        customMethod();
//
//    }

    public static boolean customMethod() {
        return downlaodFile()
//                && updateDatabase(source)
//                && deleteFile(source)
                ;
    }

    private static boolean downlaodFile() {
        boolean isOk;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            System.out.println(PROVIDER_STOCK_URL);
            System.out.println(LOCAL_FILE_PATH);

            URL url = new URL(PROVIDER_STOCK_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            Log.v("Start Query", "Start Query");
            conn.connect();
            Log.v("End Query", "End Query");
            //read the result from the server
            BufferedReader rdr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sbr = new StringBuilder();
            String line;
            while ((line = rdr.readLine()) != null) {
                sbr.append(line).append('\n');
            }
            isOk = true;
            Log.v(sbr.toString(), "Log Stream");
        } catch (Exception e) {
            isOk = false;
            e.printStackTrace();
        }
        return isOk;
    }

    private static boolean updateDatabase(String source) {
        boolean isOk = true;
        try {
            File file = new File(source);
            Serializer serializer = new Persister();
            Item item = serializer.read(Item.class, file);
            System.out.println(item);
        } catch (Exception e) {
            isOk = false;
            e.printStackTrace();
        }
        return isOk;
    }

    private static boolean deleteFile(String source) {
        File f = new File(source);
        return f.delete();
    }


}
