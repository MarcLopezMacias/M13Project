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
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.util.PathUtils;
import com.google.android.material.button.MaterialButton;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;

import cat.itb.m13project.R;
import cat.itb.m13project.pojo.Cart;
import cat.itb.m13project.pojo.Item;
import cat.itb.m13project.pojo.Usuario;

import static cat.itb.m13project.ConstantVariables.APP_NAME;
import static cat.itb.m13project.ConstantVariables.LOCAL_FILE_PATH;
import static cat.itb.m13project.ConstantVariables.PROVIDER_STOCK_URL;
import static cat.itb.m13project.ConstantVariables.STOCK_FILE_NAME;
import static cat.itb.m13project.ConstantVariables.UPDATING_STOCK;
import static cat.itb.m13project.MainActivity.loggedUser;

public class WelcomeFragment extends Fragment {

    TextView titleTextView;
    MaterialButton loginButton;
    MaterialButton registerButton;
    MaterialButton forgotPasswordButton;

    MaterialButton downloadButton;
    MaterialButton updateButton;

    public static Cart carrito;

    public WelcomeFragment() {
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
        View v = inflater.inflate(R.layout.fragment_welcome, container, false);
        titleTextView = v.findViewById(R.id.title);

        carrito = new Cart();

        loginButton = v.findViewById(R.id.loginButton);
        registerButton = v.findViewById(R.id.registerButton);
        forgotPasswordButton = v.findViewById(R.id.forgotPasswordButton);

        downloadButton = v.findViewById(R.id.downloadButton);
        downloadButton.setOnClickListener(downloadListener);
        updateButton = v.findViewById(R.id.updateButton);
        updateButton.setOnClickListener(updateListener);

        if (savedInstanceState == null) {
            loggedUser = new Usuario();
            loggedUser.setName(getString(R.string.guest));
            loggedUser.setEmail(getString(R.string.guest));
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new LoginFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new RegisterFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return v;
    }

    View.OnClickListener downloadListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // DOWNLOAD FILE
            if (!new File(LOCAL_FILE_PATH).exists()) {
                Toast.makeText(getContext(), UPDATING_STOCK, Toast.LENGTH_SHORT).show();
                System.out.println(PROVIDER_STOCK_URL);
                System.out.println(LOCAL_FILE_PATH);

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
            }
        }
    };

    View.OnClickListener updateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // UPDATE DATABASE
            File f = new File(PathUtils.getExternalDownloadsPath() + "/" + STOCK_FILE_NAME);

            Serializer ser = new Persister();
            Item orderObject = null;
            try {
                orderObject = ser.read(Item.class, f);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(orderObject);
        }
    };
}