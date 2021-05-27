package cat.itb.m13project;

import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.io.File;

import cat.itb.m13project.Fragments.StockFragment;

import static cat.itb.m13project.ConstantVariables.CONTEXT;
import static cat.itb.m13project.ConstantVariables.LOCAL_FILE_PATH;
import static cat.itb.m13project.ConstantVariables.NOT_TODAY;
import static cat.itb.m13project.ConstantVariables.PROVIDER_STOCK_URL;
import static cat.itb.m13project.ConstantVariables.STOCK_FILE_NAME;
import static cat.itb.m13project.ConstantVariables.UPDATE;
import static cat.itb.m13project.ConstantVariables.UPDATING_STOCK;
import static cat.itb.m13project.Fragments.StockFragment.existsListener;
import static cat.itb.m13project.Fragments.StockFragment.showStockListener;
import static cat.itb.m13project.Fragments.StockFragment.updateListener;

public class PushActivity extends AppCompatActivity {

    long downloadId;

    MaterialButton existsButton;
    MaterialButton updateButton;
    MaterialButton showStockButton;
    BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        public void onReceive(Context ctxt, Intent intent) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (id == downloadId) {
                StockFragment.updateDatabase();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);

        existsButton = findViewById(R.id.existsButton);
        updateButton = findViewById(R.id.updateButton);
        showStockButton = findViewById(R.id.showStockButton);

        existsButton.setOnClickListener(existsListener);
        updateButton.setOnClickListener(updateListener);
        showStockButton.setOnClickListener(showStockListener);

        registerReceiver(onDownloadComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        NotificationManager notificationManager = (NotificationManager) getApplicationContext()
                .getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancelAll();

        if (getIntent().hasExtra(UPDATE)) {
            File f = new File(LOCAL_FILE_PATH);
            if (!f.exists()) {
                Toast.makeText(CONTEXT, UPDATING_STOCK, Toast.LENGTH_SHORT).show();

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(PROVIDER_STOCK_URL));
                request.setDescription(UPDATING_STOCK);
                request.setTitle(STOCK_FILE_NAME);

                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, STOCK_FILE_NAME);

                DownloadManager downloadManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
                downloadId = downloadManager.enqueue(request);
            }
        } else if (getIntent().hasExtra(NOT_TODAY)) {
            Toast.makeText(getApplicationContext(), "Bye", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(onDownloadComplete);
    }
}