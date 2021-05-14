package cat.itb.m13project.pojo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Root(name = "producto")
public class Item implements Serializable {

    String id;

    @Element(name = "num")
    int num;

    @Element(name = "codigo")
    String codigo;

    @Element(name = "ean")
    String ean;

    @Element(name = "pn")
    String pn;

    @Element(name = "stock")
    int stock;

    @Element(name = "stockMAD")
    int stockMad;

    @Element(name = "descripcion")
    String descripcion;

    @Element(name = "id_bloque")
    int idBloque;

    @Element(name = "bloque")
    String bloque;

    @Element(name = "id_grupo")
    int idGrupo;

    @Element(name = "grupo")
    int grupo;

    @Element(name = "id_familia")
    int idFamilia;

    @Element(name = "familia")
    String familia;

    @Element(name = "marca")
    String marca;

    @Element(name = "precio")
    double precio;

    @Element(name = "peso")
    double peso;

    @Element(name = "largo")
    int largo;

    @Element(name = "ancho")
    int ancho;

    @Element(name = "alto")
    int alto;

    @Element(name = "caracteristicas")
    String caracteristicas;

    @Element(name = "fecha_alta")
    Date fechaAlta;

    @Element(name = "fotos")
    List<Foto> fotos;

    @Element(name = "canon")
    double canon;

    @Element(name = "preciocanon")
    double precioConCanon;

    @Element(name = "fecha_generacion_tarifa")
    Date fechaGeneracionTarifa;


    public Item() {
    }

    public Item(String id, int num, String codigo, String ean, String pn, int stock, int stockMad, String descripcion, int idBloque, int grupo, int idFamilia, String familia, String marca, double precio, double peso, int largo, int ancho, int alto, String caracteristicas, Date fechaAlta, List<Foto> fotos, double canon, double precioConCanon, Date fechaGeneracionTarifa) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
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

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
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

    public Date getFechaGeneracionTarifa() {
        return fechaGeneracionTarifa;
    }

    public void setFechaGeneracionTarifa(Date fechaGeneracionTarifa) {
        this.fechaGeneracionTarifa = fechaGeneracionTarifa;
    }

    public double getPrecioFinalProveedor() {
        if (this.getCanon() != 0) {
            return this.getPrecioConCanon();
        } else {
            return this.getPrecio();
        }
    }
}