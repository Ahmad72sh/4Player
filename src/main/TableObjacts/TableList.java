package main.TableObjacts;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;
import org.jsoup.nodes.Element;


public class TableList {

    public StringProperty Name;
    public Element Table;

    public TableList(String name, Element tableElement){
        this.Name = new SimpleStringProperty(name);
        this.Table = tableElement;
    }

    public String getName() {
        return Name.get();
    }

    public StringProperty nameProperty() {
        return Name;
    }

    public void setName(String name) {
        this.Name.set(name);
    }


}
