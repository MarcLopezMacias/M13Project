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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import cat.itb.m13project.Fragments.StockFragment;
import cat.itb.m13project.pojo.Producto;
import cat.itb.m13project.pojo.Usuario;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.MANAGE_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static cat.itb.m13project.ConstantVariables.ACCESORIOS;
import static cat.itb.m13project.ConstantVariables.ACTIVITY;
import static cat.itb.m13project.ConstantVariables.ALMACENAMIENTO_EXTERNO;
import static cat.itb.m13project.ConstantVariables.APPLE;
import static cat.itb.m13project.ConstantVariables.CABLES_Y_ADAPTADORES;
import static cat.itb.m13project.ConstantVariables.CAMARAS;
import static cat.itb.m13project.ConstantVariables.CHANNEL_ID;
import static cat.itb.m13project.ConstantVariables.CODIGO;
import static cat.itb.m13project.ConstantVariables.COMPONENTES;
import static cat.itb.m13project.ConstantVariables.CONSUMIBLES;
import static cat.itb.m13project.ConstantVariables.CONTEXT;
import static cat.itb.m13project.ConstantVariables.DOMOTICA;
import static cat.itb.m13project.ConstantVariables.ELECTRODOMESTICOS_PAE;
import static cat.itb.m13project.ConstantVariables.GAMING;
import static cat.itb.m13project.ConstantVariables.ILUMINACION;
import static cat.itb.m13project.ConstantVariables.IMPRESORAS_Y_ESCANERES;
import static cat.itb.m13project.ConstantVariables.LOCAL_FILE_PATH;
import static cat.itb.m13project.ConstantVariables.MONITORES_Y_TELEVISORES;
import static cat.itb.m13project.ConstantVariables.MOUSE_Y_TOUCHPAD;
import static cat.itb.m13project.ConstantVariables.MY_DEFAULT_AMOUNT;
import static cat.itb.m13project.ConstantVariables.NETWORKING;
import static cat.itb.m13project.ConstantVariables.NOT_TODAY;
import static cat.itb.m13project.ConstantVariables.OCIO_Y_TIEMPO_LIBRE;
import static cat.itb.m13project.ConstantVariables.ORDENADORES;
import static cat.itb.m13project.ConstantVariables.PORTATILES;
import static cat.itb.m13project.ConstantVariables.PROTECCION_COVID_19;
import static cat.itb.m13project.ConstantVariables.PROYECTORES_Y_ACCESORIOS;
import static cat.itb.m13project.ConstantVariables.REFRESH_DATABASE;
import static cat.itb.m13project.ConstantVariables.SAIS_REGLETAS_Y_RACKS;
import static cat.itb.m13project.ConstantVariables.SOFTWARE;
import static cat.itb.m13project.ConstantVariables.SONIDO_Y_MULTIMEDIA;
import static cat.itb.m13project.ConstantVariables.TABLET_Y_E_BOOK;
import static cat.itb.m13project.ConstantVariables.TECLADOS;
import static cat.itb.m13project.ConstantVariables.TELEFONIA_Y_MOVILIDAD;
import static cat.itb.m13project.ConstantVariables.TPV;
import static cat.itb.m13project.ConstantVariables.UPDATE;
import static cat.itb.m13project.Fragments.HomeFragment.homeProductos;

public class MainActivity extends AppCompatActivity {

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 69;
    public static FirebaseDatabase db;
    public static DatabaseReference dbUserRef;
    public static DatabaseReference dbProductoRef;
    public static Usuario loggedUser;
    public static List<Usuario> userList;
    private static String[] PERMISSIONS_STORAGE = {
            READ_EXTERNAL_STORAGE,
            WRITE_EXTERNAL_STORAGE,
            INTERNET,
            MANAGE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(R.style.AppTheme);

        if (CONTEXT == null) {
            CONTEXT = getApplicationContext();
        }

        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_STORAGE, 69);
        }

        ACTIVITY = this;

        db = FirebaseDatabase.getInstance();
        dbUserRef = db.getReference("Usuario");
        dbProductoRef = db.getReference("Producto");

        userList = new ArrayList<>();

        createNotificationChannel();

        withGreatPowerComesGreatResponsibility();
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

    //     CHANGE FRAGMENT
//        Fragment  newFragment = new HomeFragment();
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment, newFragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();

    private static void withGreatPowerComesGreatResponsibility() {
        Query filter = dbProductoRef.orderByChild(CODIGO);
        homeProductos.clear();
        filter.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                File f = new File(LOCAL_FILE_PATH);
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Producto producto = dataSnapshot.getValue(Producto.class);
                        if (!homeProductos.contains(producto)) {
                            homeProductos.add(producto);
                        }
                    }
                } else if (f.exists()) {
                    StockFragment.updateDatabase();
                } else {
                    addFakeProducts();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private static void addFakeProducts() {
        int contador = 0;
        List<Producto> productos = new ArrayList<>();
        Producto producto = new Producto();

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(ACCESORIOS);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
           
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(ALMACENAMIENTO_EXTERNO);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(APPLE);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(CABLES_Y_ADAPTADORES);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);

            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(CAMARAS);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(COMPONENTES);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(CONSUMIBLES);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(DOMOTICA);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(ELECTRODOMESTICOS_PAE);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(GAMING);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(ILUMINACION);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(IMPRESORAS_Y_ESCANERES);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(MONITORES_Y_TELEVISORES);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(MOUSE_Y_TOUCHPAD);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(NETWORKING);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(OCIO_Y_TIEMPO_LIBRE);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(ORDENADORES);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(PORTATILES);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(PROTECCION_COVID_19);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(PROYECTORES_Y_ACCESORIOS);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(SAIS_REGLETAS_Y_RACKS);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }


        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(SOFTWARE);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }


        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(SONIDO_Y_MULTIMEDIA);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }


        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(TABLET_Y_E_BOOK);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }


        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(TECLADOS);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }


        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(TELEFONIA_Y_MOVILIDAD);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas("Caracteristicas del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }


        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            StockFragment.makeValidProduct(producto);
            producto.setCodigo(String.valueOf(contador));
            contador++;
            producto.setBloque(TPV);
            producto.setDescripcion("Descripcion del producto perteneciente al Bloque: " + producto.getBloque() + " número: " + i);
            producto.setCaracteristicas(MessageFormat.format("Caracteristicas del producto perteneciente al Bloque: {0} {1}", producto.getBloque(), i));
            producto.setPrecio(i * 2.5);
            producto.setStock(i);

            productos.add(producto);
        }


        for (int i = 0; i < productos.size(); i++) {
            producto = productos.get(i);
            String key = dbProductoRef.push().getKey();
            producto.setKey(key);
            dbProductoRef.child(key).setValue(producto);
        }

        System.out.println("PRODUCTOS SIZE: " + productos.size());
    }
}