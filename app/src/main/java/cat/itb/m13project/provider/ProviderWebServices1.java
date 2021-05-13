package cat.itb.m13project.provider;

import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class ProviderWebServices1 extends Fragment {

    static String email = "marc.lopezma.7e3@itb.cat", password = "123456789";
    static URL url;

    static {
        try {
            url = new URL("https://desyman.com/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public ProviderWebServices1() throws MalformedURLException {
    }

    // Create GetText Metod
    public static void providerLogin()  throws UnsupportedEncodingException
    {

        // Create data variable for sent values to server

        String data = URLEncoder.encode("email", "UTF-8") + "="
                + URLEncoder.encode(email, "UTF-8");

        data += "&" + URLEncoder.encode("pass", "UTF-8")
                + "=" + URLEncoder.encode(password, "UTF-8");

        String text = "";
        BufferedReader reader=null;

        // Send data
        try
        {

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }


            text = sb.toString();
        }
        catch(Exception ex)
        {

        }
        finally
        {
            try
            {

                reader.close();
            }

            catch(Exception ex) {}
        }

        System.out.println("RESULT: " + text);

    }

}

