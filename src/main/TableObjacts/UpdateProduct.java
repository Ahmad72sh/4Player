package main.TableObjacts;

import javafx.beans.property.*;
import javafx.scene.control.Hyperlink;

public class UpdateProduct {

    public LongProperty GameID;
    public Hyperlink GameName;
    public StringProperty PriceDollar;
    public StringProperty PriceToman;
    public StringProperty Region;
    public StringProperty Language;
    public StringProperty Platform;
    public StringProperty Kind;
    public StringProperty EditionName;
    public LongProperty EditionID;

    public UpdateProduct(Long gameID, Hyperlink gameName, String priceDollar, String priceToman, String region,
                         String language, String platform, String kind, String editionName, Long editionID) {
        GameID = new SimpleLongProperty(gameID);
        GameName = gameName;
        PriceDollar = new SimpleStringProperty(priceDollar);
        PriceToman = new SimpleStringProperty(priceToman);
        Region = new SimpleStringProperty(region);
        Language = new SimpleStringProperty(language);
        Platform = new SimpleStringProperty(platform);
        Kind = new SimpleStringProperty(kind);
        EditionName = new SimpleStringProperty(editionName);
        EditionID = new SimpleLongProperty(editionID);
    }

    public long getGameID() {
        return GameID.get();
    }

    public LongProperty gameIDProperty() {
        return GameID;
    }

    public void setGameID(long gameID) {
        this.GameID = new SimpleLongProperty(gameID);
    }

    public Hyperlink getGameName() {
        return GameName;
    }


    public void setGameName(Hyperlink gameName) {
        this.GameName = gameName;
    }


    public String getPriceDollar() {
        return PriceDollar.get();
    }

    public StringProperty priceDollarProperty() {
        return PriceDollar;
    }

    public void setPriceDollar(String priceDollar) {
        this.PriceDollar = new SimpleStringProperty(priceDollar);
    }

    public String getPriceToman() {
        return PriceToman.get();
    }

    public StringProperty priceTomanProperty() {
        return PriceToman;
    }

    public void setPriceToman(String priceToman) {
        this.PriceToman = new SimpleStringProperty(priceToman);
    }

    public String getRegion() {
        return Region.get();
    }

    public StringProperty regionProperty() {
        return Region;
    }

    public void setRegion(String region) {
        this.Region = new SimpleStringProperty(region);
    }

    public String getLanguage() {
        return Language.get();
    }

    public StringProperty languageProperty() {
        return Language;
    }

    public void setLanguage(String language) {
        this.Language = new SimpleStringProperty(language);
    }

    public String getPlatform() {
        return Platform.get();
    }

    public StringProperty platformProperty() {
        return Platform;
    }

    public void setPlatform(String platform) {
        this.Platform = new SimpleStringProperty(platform);
    }

    public String getKind() {
        return Kind.get();
    }

    public StringProperty kindProperty() {
        return Kind;
    }

    public void setKind(String kind) {
        this.Kind = new SimpleStringProperty(kind);
    }

    public String getEditionName() {
        return EditionName.get();
    }

    public StringProperty editionNameProperty() {
        return EditionName;
    }

    public void setEditionName(String editionName) {
        this.EditionName = new SimpleStringProperty(editionName);
    }

    public long getEditionID() {
        return EditionID.get();
    }

    public LongProperty editionIDProperty() {
        return EditionID;
    }

    public void setEditionID(long editionID) {
        this.EditionID = new SimpleLongProperty(editionID);
    }

}
