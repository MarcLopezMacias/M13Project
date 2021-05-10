package cat.itb.m13project;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;


import cat.itb.m13project.pojo.*;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    public static FirebaseDatabase db;
    public static DatabaseReference dbRef;

    public static Usuario loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(R.style.AppTheme);

        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("Usuario");

        if (savedInstanceState == null) {
            loggedUser = new Usuario();
            loggedUser.setName("Guest");
        }

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