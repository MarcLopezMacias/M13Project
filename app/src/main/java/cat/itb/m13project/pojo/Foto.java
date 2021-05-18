package cat.itb.m13project.pojo;

import org.simpleframework.xml.Element;

import java.io.Serializable;

public class Foto implements Serializable {

//    @Root(name = "breakfast_menu")
//    public class BrakfastMenu {
//        @ElementList(inline = true)
//        protected List<Item.Food> food;
//
//        public List<Item.Food> getConfigurations() {
//            if (food == null) {
//                food = new ArrayList<Item.Food>();
//            }
//            return this.food;
//        }
//
//        public void setConfigurations(List<Item.Food> configuration) {
//            this.food = configuration;
//        }
//    }

    @Element(name = "foto")
    String foto;

    public Foto() {
    }

    public Foto(String foto) {
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
