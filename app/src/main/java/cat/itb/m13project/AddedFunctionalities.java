package cat.itb.m13project;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.List;

import cat.itb.m13project.pojo.Producto;
import cat.itb.m13project.pojo.Productos;
import cat.itb.m13project.pojo.Usuario;

import static cat.itb.m13project.ConstantVariables.CODIGO;
import static cat.itb.m13project.ConstantVariables.CONTEXT;
import static cat.itb.m13project.ConstantVariables.DB_PRODUCTO_REF;
import static cat.itb.m13project.ConstantVariables.GUEST;
import static cat.itb.m13project.ConstantVariables.LOCAL_FILE_PATH;
import static cat.itb.m13project.ConstantVariables.LOGGED_USER;
import static cat.itb.m13project.Fragments.StockFragment.makeValidProduct;

public class AddedFunctionalities {

    public static boolean checkUser() {
        if (SaveSharedPreference.getUserName(CONTEXT).length() != 0) {
            if (LOGGED_USER == null) {
                Toast.makeText(CONTEXT, "Logging In...", Toast.LENGTH_LONG).show();
                Query query = FirebaseDatabase.getInstance().getReference("Usuario").orderByChild("email").equalTo(SaveSharedPreference.getUserName(CONTEXT));
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                LOGGED_USER = ds.getValue(Usuario.class);
                                Toast.makeText(CONTEXT, "Logged Succesfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
            return true;
        } else if (LOGGED_USER == null) {
            System.out.println("CREATING GUEST FROM MAIN ACTIVITY");
            createGuest();
            return false;
        } else {
            System.out.println("LOGGED IN AS GUEST");
            return false;
        }
    }

    // TEST
    public static void addProductsWithImages() {
        Productos productos;
        List<Producto> productosList;
        File f = new File(LOCAL_FILE_PATH);
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
    }

    public static void createGuest() {
        LOGGED_USER = new Usuario();
        LOGGED_USER.setName(GUEST);
        LOGGED_USER.setEmail(GUEST);
        System.out.println("WELCOME GUEST");
    }

}
