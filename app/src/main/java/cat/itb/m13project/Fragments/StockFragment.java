package cat.itb.m13project.Fragments;

import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;

import cat.itb.m13project.R;
import cat.itb.m13project.pojo.Producto;
import cat.itb.m13project.pojo.Productos;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static cat.itb.m13project.ConstantVariables.LOCAL_FILE_PATH;
import static cat.itb.m13project.ConstantVariables.PROVIDER_STOCK_URL;
import static cat.itb.m13project.ConstantVariables.STOCK_FILE_NAME;
import static cat.itb.m13project.ConstantVariables.UPDATING_STOCK;

public class StockFragment extends Fragment {

    MaterialButton downloadButton;
    MaterialButton updateButton;
    MaterialButton existsButton;

    View.OnClickListener downloadListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // DOWNLOAD FILE
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
                Toast.makeText(getContext(), "FILE DELETED: " + f.delete() + ". At " + f.getPath(), Toast.LENGTH_SHORT).show();
            }
        }
    };
    View.OnClickListener updateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // UPDATE DATABASE
            File f = new File(LOCAL_FILE_PATH);
            if (ContextCompat.checkSelfPermission(getContext(), READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{READ_EXTERNAL_STORAGE}, 69);
            } else {
                if (f.exists()) {
                    whatCanIDo(f);
                    Toast.makeText(getContext(), f.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                    Serializer ser = new Persister();
                    Productos orderObject = null;
                    try {
                        orderObject = ser.read(Productos.class, f);
                        System.out.println(orderObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    System.out.println(orderObject);
                } else {
                    Toast.makeText(getContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), Environment.DIRECTORY_DOWNLOADS + "/" + STOCK_FILE_NAME, Toast.LENGTH_SHORT).show();


                }
            }
        }
    };

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
        existsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File f = new File(LOCAL_FILE_PATH);
                Toast.makeText(getContext(), "FILE EXISTS; " + f.exists() + " at " + f.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    private static void whatCanIDo(File f) {
        System.out.println("CAN WRITE: " + f.canWrite());
        System.out.println("CAN READ: " + f.canRead());
        System.out.println("CAN EXECUTE: " + f.canExecute());

    }
}