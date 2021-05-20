package cat.itb.m13project.Fragments;

import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.os.StrictMode;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cat.itb.m13project.R;
import cat.itb.m13project.pojo.Producto;
import cat.itb.m13project.pojo.Productos;

import static cat.itb.m13project.ConstantVariables.LOCAL_FILE_PATH;
import static cat.itb.m13project.ConstantVariables.PROVIDER_STOCK_URL;
import static cat.itb.m13project.ConstantVariables.STOCK_FILE_NAME;
import static cat.itb.m13project.ConstantVariables.UPDATING_STOCK;
import static cat.itb.m13project.MainActivity.dbProductoRef;

public class StockFragment extends Fragment {

    MaterialButton downloadButton;
    MaterialButton updateButton;
    MaterialButton existsButton;
    MaterialButton showStockButton;

    public static Productos productos = null;
    List<Producto> productosList = new ArrayList<>();

    ProgressBar loadingProgressBar;

    public StockFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_stock, container, false);

        downloadButton = v.findViewById(R.id.downloadButton);
        downloadButton.setOnClickListener(downloadListener);
        updateButton = v.findViewById(R.id.updateButton);
        updateButton.setOnClickListener(updateListener);
        existsButton = v.findViewById(R.id.existsButton);
        existsButton.setOnClickListener(existsListener);

        showStockButton = v.findViewById(R.id.showStockButton);
        showStockButton.setOnClickListener(showStockListener);

        loadingProgressBar = v.findViewById(R.id.loading);

        return v;
    }

    View.OnClickListener downloadListener = v -> {
        // DOWNLOAD FILE
        loadingProgressBar.setVisibility(View.VISIBLE);
        File f = new File(LOCAL_FILE_PATH);
        if (!f.exists()) {
            Toast.makeText(getContext(), UPDATING_STOCK, Toast.LENGTH_SHORT).show();
            System.out.println(PROVIDER_STOCK_URL);
            System.out.println(Environment.DIRECTORY_DOWNLOADS + "/" + STOCK_FILE_NAME);

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(PROVIDER_STOCK_URL));
            request.setDescription(UPDATING_STOCK);
            request.setTitle(STOCK_FILE_NAME);

            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, STOCK_FILE_NAME);

            DownloadManager manager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
            manager.enqueue(request);
        } else {
            Toast.makeText(getContext(), "FILE ALREADY EXISTS", Toast.LENGTH_SHORT).show();
        }
        loadingProgressBar.setVisibility(View.INVISIBLE);
    };

    View.OnClickListener updateListener = v -> {
        loadingProgressBar.setVisibility(View.VISIBLE);
        // UPDATE DATABASE
        Toast.makeText(getContext(), "UPDATING DATABASE", Toast.LENGTH_SHORT).show();
        File f = new File(LOCAL_FILE_PATH);
        if (f.exists()) {
            Toast.makeText(getContext(), "EXISTS at: " + f.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            try {
                Serializer ser = new Persister();
                productos = ser.read(Productos.class, f);
                productosList = productos.getProductos();

                for (int i = 0; i < productosList.size(); i++) {
                    System.out.println(i);
                    Producto producto = productosList.get(i);
                    Query query = dbProductoRef.orderByChild("codigo").equalTo(producto.getCodigo());
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    Producto producto1 = ds.getValue(Producto.class);
                                    producto.setKey(producto1.getKey());
                                    if (!(producto1.getStock() == producto.getStock())) {
                                        dbProductoRef.child(producto.getKey()).setValue(producto);
                                    }
                                }
                            } else {
                                String key = dbProductoRef.push().getKey();
                                producto.setKey(key);
                                dbProductoRef.child(key).setValue(producto);
                            }
                            Toast.makeText(getContext(), getString(R.string.process_terminated), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });                            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(), Environment.DIRECTORY_DOWNLOADS + "/" + STOCK_FILE_NAME, Toast.LENGTH_SHORT).show();
        }
        loadingProgressBar.setVisibility(View.INVISIBLE);
    };

    View.OnClickListener showStockListener = v -> {
        loadingProgressBar.setVisibility(View.VISIBLE);
        Query query = dbProductoRef.orderByChild("fecha_alta");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productosList.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        productosList.add(ds.getValue(Producto.class));
                    }
                }
                System.out.println(productosList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        loadingProgressBar.setVisibility(View.INVISIBLE);
    };

    View.OnClickListener existsListener = v -> {
        loadingProgressBar.setVisibility(View.VISIBLE);
        File f = new File(LOCAL_FILE_PATH);
        Toast.makeText(getContext(), "FILE EXISTS; " + f.exists() + " at " + f.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        loadingProgressBar.setVisibility(View.INVISIBLE);
    };
}