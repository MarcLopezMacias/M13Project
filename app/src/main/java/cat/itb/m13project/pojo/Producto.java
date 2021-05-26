package cat.itb.m13project.pojo;

import android.icu.text.SimpleDateFormat;
import android.net.Uri;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Root(name = "producto", strict = false)
public class Producto implements Serializable {

    String key;

    @Element(name = "num", required = false)
    int num;


    @Element(name = "codigo")
    String codigo;

    @Element(name = "ean", required = false)
    String ean;

    @Element(name = "pn", required = false)
    String pn;

    @Element(name = "stock", required = false)
    int stock;

    @Element(name = "stockMAD")
    int stockMad;

    @Element(name = "descripcion")
    String descripcion;

    @Element(name = "id_bloque", required = false)
    int idBloque;

    @Element(name = "bloque", required = false)
    String bloque;

    @Element(name = "id_grupo", required = false)
    int idGrupo;

    @Element(name = "grupo", required = false)
    String grupo;

    @Element(name = "id_familia")
    int idFamilia;

    @Element(name = "familia", required = false)
    String familia;

    @Element(name = "marca", required = false)
    String marca;

    @Element(name = "precio")
    double precio;

    @Element(name = "peso")
    double peso;

    @Element(name = "largo")
    double largo;

    @Element(name = "ancho")
    double ancho;

    @Element(name = "alto")
    double alto;

    @Element(name = "caracteristicas", required = false)
    String caracteristicas;

    @Element(name = "fecha_alta")
    String fechaAlta;

    @ElementList(inline = true, required = false)
    List<Uri> fotos;

    @Element(name = "canon")
    double canon;

    @Element(name = "precioconcanon")
    double precioConCanon;

    @Element(name = "fecha_generacion_tarifa")
    String fechaGeneracionTarifa;

    double precioFinal;

    public Producto() {
    }

    public Producto(String key, int num, String codigo, String ean, String pn, int stock, int stockMad, String descripcion, int idBloque, String grupo, int idFamilia, String familia, String marca, double precio, double peso, double largo, double ancho, double alto, String caracteristicas, String fechaAlta, List<Uri> fotos, double canon, double precioConCanon, String fechaGeneracionTarifa) {
        this.key = key;
        this.num = num;
        this.codigo = codigo;
        this.ean = ean;
        this.pn = pn;
        this.stock = stock;
        this.stockMad = stockMad;
        this.descripcion = descripcion;
        this.idBloque = idBloque;
        this.grupo = grupo;
        this.idFamilia = idFamilia;
        this.familia = familia;
        this.marca = marca;
        this.precio = precio;
        this.peso = peso;
        this.largo = largo;
        this.ancho = ancho;
        this.alto = alto;
        this.caracteristicas = caracteristicas;
        this.fechaAlta = fechaAlta;
        this.fotos = fotos;
        this.canon = canon;
        this.precioConCanon = precioConCanon;
        this.fechaGeneracionTarifa = fechaGeneracionTarifa;
    }

    public Producto(String key, int num, String codigo, String ean, String pn, int stock, int stockMad, String descripcion, int idBloque, String bloque, int idGrupo, String grupo, int idFamilia, String familia, String marca, double precio, double peso, double largo, double ancho, double alto, String caracteristicas, String fechaAlta, List<Uri> fotos, double canon, double precioConCanon, String fechaGeneracionTarifa, double precioFinal) {
        this.key = key;
        this.num = num;
        this.codigo = codigo;
        this.ean = ean;
        this.pn = pn;
        this.stock = stock;
        this.stockMad = stockMad;
        this.descripcion = descripcion;
        this.idBloque = idBloque;
        this.bloque = bloque;
        this.idGrupo = idGrupo;
        this.grupo = grupo;
        this.idFamilia = idFamilia;
        this.familia = familia;
        this.marca = marca;
        this.precio = precio;
        this.peso = peso;
        this.largo = largo;
        this.ancho = ancho;
        this.alto = alto;
        this.caracteristicas = caracteristicas;
        this.fechaAlta = fechaAlta;
        this.fotos = fotos;
        this.canon = canon;
        this.precioConCanon = precioConCanon;
        this.fechaGeneracionTarifa = fechaGeneracionTarifa;
        this.precioFinal = precioFinal;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockMad() {
        return stockMad;
    }

    public void setStockMad(int stockMad) {
        this.stockMad = stockMad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdBloque() {
        return idBloque;
    }

    public void setIdBloque(int idBloque) {
        this.idBloque = idBloque;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public int getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(int idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public List<Uri> getFotos() {
        return fotos;
    }

    public void setFotos(List<Uri> fotos) {
        this.fotos = fotos;
    }

    public double getCanon() {
        return canon;
    }

    public void setCanon(double canon) {
        this.canon = canon;
    }

    public double getPrecioConCanon() {
        return precioConCanon;
    }

    public void setPrecioConCanon(double precioConCanon) {
        this.precioConCanon = precioConCanon;
    }

    public String getFechaGeneracionTarifa() {
        return fechaGeneracionTarifa;
    }

    public void setFechaGeneracionTarifa(String fechaGeneracionTarifa) {
        this.fechaGeneracionTarifa = fechaGeneracionTarifa;
    }

    public double getPrecioFinalProveedor() {
        if (this.getCanon() != 0) {
            return this.getPrecioConCanon();
        } else {
            return this.getPrecio();
        }
    }

    public void setPrecioFinalProveedor(double precioFinalProveedor) {
        this.precioFinal = precioFinalProveedor;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + key + '\'' +
                ", num=" + num +
                ", codigo='" + codigo + '\'' +
                ", ean='" + ean + '\'' +
                ", pn='" + pn + '\'' +
                ", stock=" + stock +
                ", stockMad=" + stockMad +
                ", descripcion='" + descripcion + '\'' +
                ", idBloque=" + idBloque +
                ", bloque='" + bloque + '\'' +
                ", idGrupo=" + idGrupo +
                ", grupo=" + grupo +
                ", idFamilia=" + idFamilia +
                ", familia='" + familia + '\'' +
                ", marca='" + marca + '\'' +
                ", precio=" + precio +
                ", peso=" + peso +
                ", largo=" + largo +
                ", ancho=" + ancho +
                ", alto=" + alto +
                ", caracteristicas='" + caracteristicas + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", fotos=" + fotos +
                ", canon=" + canon +
                ", precioConCanon=" + precioConCanon +
                ", fechaGeneracionTarifa=" + fechaGeneracionTarifa +
                '}';
    }

    public String getBloque() {
        return bloque;
    }

    public void setBloque(String bloque) {
        this.bloque = bloque;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }
}