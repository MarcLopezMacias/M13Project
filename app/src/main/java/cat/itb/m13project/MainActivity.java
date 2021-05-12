package cat.itb.m13project;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cat.itb.m13project.data.base.ProviderWebServices1;
import cat.itb.m13project.pojo.Usuario;

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

        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("Usuario");

        if (savedInstanceState == null) {
            loggedUser = new Usuario();
            loggedUser.setName(getString(R.string.guest));
            loggedUser.setEmail(getString(R.string.guest));
        }

        userList = new ArrayList<>();

//        try {
//            System.out.println("TRYING");
//            ProviderWebServices1.providerLogin();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

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