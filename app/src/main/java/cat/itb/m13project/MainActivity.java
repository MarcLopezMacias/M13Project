package cat.itb.m13project;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.amplitude.api.Amplitude;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.paypal.checkout.PayPalCheckout;
import com.paypal.checkout.config.CheckoutConfig;
import com.paypal.checkout.config.Environment;
import com.paypal.checkout.config.SettingsConfig;
import com.paypal.checkout.createorder.CurrencyCode;
import com.paypal.checkout.createorder.UserAction;

import java.io.File;

import cat.itb.m13project.Fragments.StockFragment;
import cat.itb.m13project.pojo.Producto;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static cat.itb.m13project.BuildConfig.APPLICATION_ID;
import static cat.itb.m13project.ConstantVariables.ACTIVITY;
import static cat.itb.m13project.ConstantVariables.AMPLITUDE_CLIENT;
import static cat.itb.m13project.ConstantVariables.CHANNEL_ID;
import static cat.itb.m13project.ConstantVariables.CLIENT_KEY;
import static cat.itb.m13project.ConstantVariables.CODIGO;
import static cat.itb.m13project.ConstantVariables.CONTEXT;
import static cat.itb.m13project.ConstantVariables.DB_PRODUCTO_REF;
import static cat.itb.m13project.ConstantVariables.DEFAULT_AMOUNT;
import static cat.itb.m13project.ConstantVariables.HOME_PRODUCTS;
import static cat.itb.m13project.ConstantVariables.LOCAL_FILE_PATH;
import static cat.itb.m13project.ConstantVariables.NOT_TODAY;
import static cat.itb.m13project.ConstantVariables.PERMISSIONS_STORAGE;
import static cat.itb.m13project.ConstantVariables.REFRESH_DATABASE;
import static cat.itb.m13project.ConstantVariables.UPDATE;
import static cat.itb.m13project.FakeProducts.addFakeProducts;

public class MainActivity extends AppCompatActivity {

    private static void withGreatPowerComesGreatResponsibility() {
        System.out.println(APPLICATION_ID);
        Query filter = DB_PRODUCTO_REF.orderByChild(CODIGO).limitToFirst(DEFAULT_AMOUNT);
        filter.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Producto producto = dataSnapshot.getValue(Producto.class);
                        if (!HOME_PRODUCTS.contains(producto)) {
                            HOME_PRODUCTS.add(producto);
                        }
                    }
                } else {
                    File f = new File(LOCAL_FILE_PATH);
                    if (f.exists()) {
                        StockFragment.updateDatabase();
                    }
                    addFakeProducts();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private static void readPhotos() {
        File f = new File(LOCAL_FILE_PATH);
        if (f.exists()) {
            AddedFunctionalities.showPics();
        }
    }

    private void setupPayPalConfig() {
        CheckoutConfig config = new CheckoutConfig(
                getApplication(),
                CLIENT_KEY,
                Environment.LIVE,
                APPLICATION_ID.concat("://paypalpay"),
                CurrencyCode.EUR,
                UserAction.PAY_NOW,
                new SettingsConfig(
                        true,
                        false
                )
        );
        PayPalCheckout.setConfig(config);

        AMPLITUDE_CLIENT = Amplitude.getInstance()
                .initialize(getApplicationContext(), CLIENT_KEY)
                .enableForegroundTracking(getApplication());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(R.style.AppTheme);

        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_STORAGE, 69);
        }

        ACTIVITY = this;
        CONTEXT = getApplicationContext();

        createNotificationChannel();

        setupPayPalConfig();
        withGreatPowerComesGreatResponsibility();
        readPhotos();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStop() {
        super.onStop();
        startNotifications();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void startNotifications() {
        NotificationManager manager = (NotificationManager) this.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        Intent i = new Intent(this, PushActivity.class);
        i.putExtra(UPDATE, true);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, i, PendingIntent.FLAG_ONE_SHOT);

        Intent i2 = new Intent(this, PushActivity.class);
        i2.putExtra(NOT_TODAY, false);
        i2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(MainActivity.this, 1, i2, PendingIntent.FLAG_ONE_SHOT);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                MainActivity.this, CHANNEL_ID
        );
        builder.setContentTitle(UPDATE);
        builder.setContentText(REFRESH_DATABASE);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setSound(uri);
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);

        builder.addAction(R.drawable.ic_launcher_foreground, UPDATE, pendingIntent);
        builder.addAction(R.drawable.ic_launcher_foreground, NOT_TODAY, pendingIntent2);

        manager.notify(1, builder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}