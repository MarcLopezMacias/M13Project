package cat.itb.m13project.provider;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.blankj.utilcode.util.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cat.itb.m13project.R;

import static cat.itb.m13project.ConstantVariables.LOCAL_FILE_PATH;
import static cat.itb.m13project.ConstantVariables.PROVIDER_STOCK_URL;
import static cat.itb.m13project.ConstantVariables.UPDATING_STOCK;

public class DownloadFileFromURL extends AsyncTask<String, Integer, String> {

    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder build;
    int id = 123;
    private Context context;

    public DownloadFileFromURL(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {
        super.onPreExecute();

        System.out.println("PRE EXECUTING");
        mNotifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        build = new NotificationCompat.Builder(context);
        build.setContentTitle("Download")
                .setContentText("Download in progress")
                .setChannelId(id + "")
                .setAutoCancel(false)
                .setDefaults(0)
                .setSmallIcon(R.drawable.ic_baseline_cloud_download_24);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(id + "",
                    "Social Media Downloader",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("no sound");
            channel.setSound(null, null);
            channel.enableLights(false);
            channel.setLightColor(Color.BLUE);
            channel.enableVibration(false);
            mNotifyManager.createNotificationChannel(channel);

        }
        build.setProgress(100, 0, false);
        mNotifyManager.notify(id, build.build());
        Toast.makeText(context, UPDATING_STOCK, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... f_url) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL url = new URL(PROVIDER_STOCK_URL);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();

            File file = new File(LOCAL_FILE_PATH);
            if (!file.exists()) {
                System.out.println("FILE CREATED: " + file.createNewFile());
            }

            FileOutputStream fileOutput = new FileOutputStream(file);

            InputStream inputStream = urlConnection.getInputStream();

            int totalSize = urlConnection.getContentLength();

            //variable to store total downloaded bytes
            int downloadedSize = 0;

            //create a buffer...
            byte[] buffer = new byte[1024];
            int bufferLength = 0; //used to store a temporary size of the buffer

            //now, read through the input buffer and write the contents to the file
            while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
                //add the data in the buffer to the file in the file output stream (the file on the sd card
                fileOutput.write(buffer, 0, bufferLength);
                //add up the size so we know how much is downloaded
                downloadedSize += bufferLength;

            }
            //close the output stream when done
            fileOutput.close();
            //catch some possible errors...
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}