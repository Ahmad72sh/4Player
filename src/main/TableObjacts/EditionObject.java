package main.TableObjacts;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EditionObject {

    public StringProperty EditionTitle;
    public StringProperty EditionID;

    public EditionObject (String editionTitle, String editionID){
        EditionTitle = new SimpleStringProperty(editionTitle);
        EditionID = new SimpleStringProperty(editionID);

    }

    public String getEditionTitle() {
        return EditionTitle.get();
    }

    public StringProperty editionTitleProperty() {
        return EditionTitle;
    }

    public void setEditionTitle(String editionTitle) {
        this.EditionTitle.set(editionTitle);
    }

    public String getEditionID() {
        return EditionID.get();
    }

    public StringProperty editionIDProperty() {
        return EditionID;
    }

    public void setEditionID(String editionID) {
        this.EditionID.set(editionID);
    }
}
