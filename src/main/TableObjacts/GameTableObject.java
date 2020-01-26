package main.TableObjacts;

import javafx.beans.property.*;
import javafx.scene.control.Hyperlink;

public class GameTableObject {

    public LongProperty GameID;
    public Hyperlink ItemName;
    public StringProperty SellerName;
    public StringProperty SellerRate;
    public StringProperty Sold;
    public StringProperty Price;
    public StringProperty Region;
    public StringProperty Language;
    public StringProperty Platform;
    public StringProperty Kind;
    public StringProperty Edition;
    public LongProperty EditionID;
    public BooleanProperty SetDefault = new SimpleBooleanProperty(false);



    public GameTableObject(Long gameId ,Hyperlink itemName, String sellerName,String sellerRate, String sold,
                           String price) {
        this.GameID = new SimpleLongProperty(gameId);
        this.ItemName = itemName;
        this.SellerName = new SimpleStringProperty (sellerName);
        this.SellerRate = new SimpleStringProperty (sellerRate);
        this.Sold = new SimpleStringProperty (sold);
        this.Price = new SimpleStringProperty (price);
        this.Region = new SimpleStringProperty ("");
        this.Language = new SimpleStringProperty ("");
        this.Platform = new SimpleStringProperty ("");
        this.Kind = new SimpleStringProperty ("");
        this.Edition = new SimpleStringProperty ("");
        this.EditionID = new SimpleLongProperty (0L);

    }


    public Long getGameID() {
        return GameID.get();
    }

    public LongProperty gameIDProperty() {
        return GameID;
    }

    public void setGameID(Long gameID) {
        this.GameID = new SimpleLongProperty(gameID);
    }

    public String getSellerName() {
        return SellerName.get();
    }

    public StringProperty sellerNameProperty() {
        return SellerName;
    }

    public void setSellerName(String sellerName) {
        this.SellerName.set(sellerName);
    }

    public String getSellerRate() {
        return SellerRate.get();
    }

    public StringProperty sellerRateProperty() {
        return SellerRate;
    }

    public void setSellerRate(String sellerRate) {
        this.SellerRate.set(sellerRate);
    }

    public String getSold() {
        return Sold.get();
    }

    public StringProperty soldProperty() {
        return Sold;
    }

    public void setSold(String sold) {
        this.Sold.set(sold);
    }

    public String getPrice() {
        return Price.get();
    }

    public StringProperty priceProperty() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price.set(price);
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
        this.Language= new SimpleStringProperty(language);
    }

    public String getPlatform() {
        return Platform.get();
    }

    public StringProperty platformProperty() {
        return Platform;
    }

    public void setPlatform(String Platform) {
        this.Platform= new SimpleStringProperty(Platform);
    }

    public String getKind() {
        return Kind.get();
    }

    public StringProperty kindProperty() {
        return Kind;
    }

    public void setKind(String kind) {
        this.Kind= new SimpleStringProperty(kind);
    }

    public String getEdition() {
        return Edition.get();
    }

    public StringProperty editionProperty() {
        return Edition;
    }

    public void setEdition(String edition) {
        this.Edition= new SimpleStringProperty(edition);
    }

    public Long getEditionID() {
        return EditionID.get();
    }

    public LongProperty editionIDProperty() {
        return EditionID;
    }

    public void setEditionID(Long editionID) {
        this.EditionID = new SimpleLongProperty(editionID);
    }

    public Hyperlink getItemName() {
        return ItemName;
    }

    public void setItemName(Hyperlink itemName) {
        ItemName = itemName;
    }

    public boolean isSetDefault() {
        return SetDefault.get();
    }

    public BooleanProperty setDefaultProperty() {
        return SetDefault;
    }

    public void setSetDefault(boolean setDefault) {
        this.SetDefault = new SimpleBooleanProperty(setDefault);
    }
}
