package cat.itb.m13project.provider;

import android.os.Environment;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static cat.itb.m13project.ConstantVariables.XML_URL;

public class ProviderWebServices3 {

    public static void main(String[] args) {
        try {
            // GO TO LOGIN PAGE
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("https://desyman.com/");
            HttpResponse response = null;
            List<NameValuePair> postFields = new ArrayList<NameValuePair>(2);

            // SET LOGIN DATA
            postFields.add(new BasicNameValuePair("username", "myusername"));
            postFields.add(new BasicNameValuePair("password", "mypassword"));
            post.setEntity(new UrlEncodedFormEntity(postFields, HTTP.UTF_8));

            // EXECUTE
            response = client.execute(post);

            // GO GET THAT FILE
            HttpGet get = new HttpGet(XML_URL);
            response = client.execute(get);

            HttpEntity entity = response.getEntity();
            InputStream in = entity.getContent();

            // Save the file to SD
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            path.mkdirs();
            File file = new File(path, "stock.xml");
            FileOutputStream fos = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = in.read(buffer)) > 0) {
                fos.write(buffer, 0, len1);
            }

            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
