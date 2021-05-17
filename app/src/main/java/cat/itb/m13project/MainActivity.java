package cat.itb.m13project;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import cat.itb.m13project.pojo.Usuario;
import cat.itb.m13project.provider.DownloadFileFromURL;
import cat.itb.m13project.provider.ProviderWebServices3;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static cat.itb.m13project.ConstantVariables.LOCAL_FILE_PATH;
import static cat.itb.m13project.ConstantVariables.PROVIDER_STOCK_URL;

public class MainActivity extends AppCompatActivity {

    public static FirebaseDatabase db;
    public static DatabaseReference dbRef;

    public static Usuario loggedUser;
    public static List<Usuario> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(R.style.AppTheme);

        if (ContextCompat.checkSelfPermission(MainActivity.this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { WRITE_EXTERNAL_STORAGE }, 69);
        } else if (ContextCompat.checkSelfPermission(MainActivity.this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { WRITE_EXTERNAL_STORAGE }, 6969);
        } else if (ContextCompat.checkSelfPermission(MainActivity.this, INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { WRITE_EXTERNAL_STORAGE }, 696969);
        }

        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("Usuario");

        userList = new ArrayList<>();


        System.out.println(PROVIDER_STOCK_URL);
        System.out.println(LOCAL_FILE_PATH);
//        new DownloadFileFromURL(this);
//        ProviderWebServices3.customMethod();
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

//     CHANGE FRAGMENT
//        Fragment  newFragment = new HomeFragment();
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment, newFragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();


}