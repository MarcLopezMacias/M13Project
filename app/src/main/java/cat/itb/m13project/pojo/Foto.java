package cat.itb.m13project.pojo;

import org.simpleframework.xml.Root;

@Root(name = "foto")
public class Foto {

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

    String link;

    public Foto() {
    }

    public Foto(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
