package cat.itb.m13project;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import cat.itb.m13project.pojo.Producto;

import static cat.itb.m13project.ConstantVariables.ACCESORIOS;
import static cat.itb.m13project.ConstantVariables.ALMACENAMIENTO_EXTERNO;
import static cat.itb.m13project.ConstantVariables.APPLE;
import static cat.itb.m13project.ConstantVariables.CABLES_Y_ADAPTADORES;
import static cat.itb.m13project.ConstantVariables.CAMARAS;
import static cat.itb.m13project.ConstantVariables.COMPONENTES;
import static cat.itb.m13project.ConstantVariables.CONSUMIBLES;
import static cat.itb.m13project.ConstantVariables.DB_PRODUCTO_REF;
import static cat.itb.m13project.ConstantVariables.DOMOTICA;
import static cat.itb.m13project.ConstantVariables.ELECTRODOMESTICOS_PAE;
import static cat.itb.m13project.ConstantVariables.GAMING;
import static cat.itb.m13project.ConstantVariables.ILUMINACION;
import static cat.itb.m13project.ConstantVariables.IMPRESORAS_Y_ESCANERES;
import static cat.itb.m13project.ConstantVariables.MONITORES_Y_TELEVISORES;
import static cat.itb.m13project.ConstantVariables.MOUSE_Y_TOUCHPAD;
import static cat.itb.m13project.ConstantVariables.MY_DEFAULT_AMOUNT;
import static cat.itb.m13project.ConstantVariables.NETWORKING;
import static cat.itb.m13project.ConstantVariables.OCIO_Y_TIEMPO_LIBRE;
import static cat.itb.m13project.ConstantVariables.ORDENADORES;
import static cat.itb.m13project.ConstantVariables.PORTATILES;
import static cat.itb.m13project.ConstantVariables.PROTECCION_COVID_19;
import static cat.itb.m13project.ConstantVariables.PROYECTORES_Y_ACCESORIOS;
import static cat.itb.m13project.ConstantVariables.SAIS_REGLETAS_Y_RACKS;
import static cat.itb.m13project.ConstantVariables.SOFTWARE;
import static cat.itb.m13project.ConstantVariables.SONIDO_Y_MULTIMEDIA;
import static cat.itb.m13project.ConstantVariables.TABLET_Y_E_BOOK;
import static cat.itb.m13project.ConstantVariables.TECLADOS;
import static cat.itb.m13project.ConstantVariables.TELEFONIA_Y_MOVILIDAD;
import static cat.itb.m13project.ConstantVariables.TPV;
import static cat.itb.m13project.Fragments.StockFragment.makeValidProduct;

public class FakeProducts {

    public static void addFakeProducts() {
        int contador = 0;
        List<Producto> productos = new ArrayList<>();
        Producto producto = new Producto();

        for (int i = 0; i < MY_DEFAULT_AMOUNT; i++) {
            producto = new Producto();
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);

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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);

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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            producto = makeValidProduct(producto);
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
            makeValidProduct(producto);
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
            String key = DB_PRODUCTO_REF.push().getKey();
            producto.setKey(key);
            DB_PRODUCTO_REF.child(key).setValue(producto);
        }

        System.out.println("PRODUCTOS SIZE: " + productos.size());
    }
}
