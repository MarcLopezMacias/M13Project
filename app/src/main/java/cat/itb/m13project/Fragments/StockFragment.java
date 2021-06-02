package cat.itb.m13project.Fragments;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
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

import static cat.itb.m13project.ConstantVariables.ACTIVITY;
import static cat.itb.m13project.ConstantVariables.CODIGO;
import static cat.itb.m13project.ConstantVariables.CONTEXT;
import static cat.itb.m13project.ConstantVariables.DB_PRODUCTO_REF;
import static cat.itb.m13project.ConstantVariables.DEFAULT;
import static cat.itb.m13project.ConstantVariables.DELETING_ALL_PRODUCTS;
import static cat.itb.m13project.ConstantVariables.FECHA_ALTA;
import static cat.itb.m13project.ConstantVariables.LOCAL_FILE_PATH;
import static cat.itb.m13project.ConstantVariables.PROVIDER_STOCK_URL;
import static cat.itb.m13project.ConstantVariables.STOCK_FILE_NAME;
import static cat.itb.m13project.ConstantVariables.UPDATING_STOCK;

public class StockFragment extends Fragment {

    public static Productos productos = null;
    public static View.OnClickListener existsListener = v -> {
        File f = new File(LOCAL_FILE_PATH);
        if (f.exists()) {
            Toast.makeText(CONTEXT, "FILE EXISTS; at " + f.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CONTEXT, "FILE DOESNT EXIST", Toast.LENGTH_SHORT).show();
        }
    };
    static ProgressBar loadingProgressBar;
    static List<Producto> productosList = new ArrayList<>();
    public static View.OnClickListener updateListener = v -> {
        // UPDATE DATABASE
        updateDatabase();
    };
    public static View.OnClickListener showStockListener = v -> {
        Query query = DB_PRODUCTO_REF.orderByChild(FECHA_ALTA);
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
    };
    MaterialButton updateButton;
    MaterialButton existsButton;
    MaterialButton showStockButton;
    MaterialButton deleteProductsButton;
    MaterialButton downloadButton;
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
            f.delete();
            Toast.makeText(getContext(), "FILE ALREADY EXISTS", Toast.LENGTH_SHORT).show();
        }
        loadingProgressBar.setVisibility(View.INVISIBLE);
    };
    View.OnClickListener deleteProductsListener = v -> {
        Query query = DB_PRODUCTO_REF.orderByChild(CODIGO);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        Producto producto1 = ds.getValue(Producto.class);
                        DB_PRODUCTO_REF.child(producto1.getKey()).removeValue();


                    }
                } else {
                    Toast.makeText(CONTEXT, "NO PRODUCTS TO DELETE", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        Toast.makeText(CONTEXT, DELETING_ALL_PRODUCTS, Toast.LENGTH_SHORT).show();
    };

    public StockFragment() {
        // Required empty public constructor
    }

    public static Producto makeValidProduct(Producto producto) {
        if (producto.getFotos() == null) {
            System.out.println("PHOTOS IS NULL");
        }
        if (producto.getCodigo() == null || producto.getCodigo().isEmpty()) {
            producto.setCodigo(DEFAULT);
        }
        if (producto.getBloque() == null || producto.getBloque().isEmpty()) {
            producto.setBloque(DEFAULT);
        }
        if (producto.getDescripcion() == null || producto.getDescripcion().isEmpty()) {
            producto.setDescripcion(DEFAULT);
        }
        if (producto.getStock() == 0) {
            System.out.println("BAD STOCK");
        }
        return producto;
    }

    public static void updateDatabase() {
        Toast.makeText(CONTEXT, "UPDATING DATABASE", Toast.LENGTH_SHORT).show();
        File f = new File(LOCAL_FILE_PATH);
        if (f.exists()) {
            Toast.makeText(CONTEXT, "FILE EXISTS at: " + f.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            try {
                Serializer ser = new Persister();
                productos = ser.read(Productos.class, f);
                productosList = productos.getProductos();
                for (int i = 0; i < productosList.size(); i++) {
                    System.out.println(i);
                    Producto producto = productosList.get(i);
                    Query query = DB_PRODUCTO_REF.orderByChild(CODIGO).equalTo(producto.getCodigo());
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    Producto producto1 = ds.getValue(Producto.class);
                                    producto.setKey(producto1.getKey());
                                    makeValidProduct(producto1);
                                    System.out.println("PRODUCT VALID");
                                    if (!(producto1.getStock() == producto.getStock())) {
                                        DB_PRODUCTO_REF.child(producto.getKey()).setValue(producto);
                                    }

                                }
                            } else {
                                makeValidProduct(producto);
                                String key = DB_PRODUCTO_REF.push().getKey();
                                producto.setKey(key);
                                DB_PRODUCTO_REF.child(key).setValue(producto);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(CONTEXT, UPDATING_STOCK, Toast.LENGTH_SHORT).show();

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(PROVIDER_STOCK_URL));
            request.setDescription(UPDATING_STOCK);
            request.setTitle(STOCK_FILE_NAME);

            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, STOCK_FILE_NAME);

            DownloadManager manager = (DownloadManager) ACTIVITY.getSystemService(Context.DOWNLOAD_SERVICE);
            manager.enqueue(request);
        }
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

        CONTEXT = getContext();
        ACTIVITY = getActivity();

        updateButton = v.findViewById(R.id.updateButton);
        updateButton.setOnClickListener(updateListener);
        existsButton = v.findViewById(R.id.existsButton);
        existsButton.setOnClickListener(existsListener);
        deleteProductsButton = v.findViewById(R.id.deleteProductsButton);
        deleteProductsButton.setOnClickListener(deleteProductsListener);
        downloadButton = v.findViewById(R.id.downloadButton);
        downloadButton.setOnClickListener(downloadListener);

        showStockButton = v.findViewById(R.id.showStockButton);
        showStockButton.setOnClickListener(showStockListener);

        loadingProgressBar = v.findViewById(R.id.loading);

        return v;
    }
}