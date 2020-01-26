package main.TableObjacts;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GamesList {
    public StringProperty GameName;
    public StringProperty GameId;
    public StringProperty PlatiLink;
    public StringProperty SteamDbLink;
    public LongProperty GamePostId;


    public GamesList(String gameName, String gameId, String platiLink, String steamDbLink , Long gamePostId){
        GameName = new SimpleStringProperty(gameName);
        GameId = new SimpleStringProperty(gameId);
        PlatiLink = new SimpleStringProperty(platiLink);
        SteamDbLink = new SimpleStringProperty(steamDbLink);
        GamePostId = new SimpleLongProperty(gamePostId);

    }

    public String getGameName() {
        return GameName.get();
    }

    public StringProperty gameNameProperty() {
        return GameName;
    }

    public void setGameName(String gameName) {
        this.GameName.set(gameName);
    }

    public String getPlatiLink() {
        return PlatiLink.get();
    }

    public StringProperty platiLinkProperty() {
        return PlatiLink;
    }

    public void setPlatiLink(String platiLink) {
        this.PlatiLink.set(platiLink);
    }

    public String getSteamDbLink() {
        return SteamDbLink.get();
    }

    public StringProperty steamDbLinkProperty() {
        return SteamDbLink;
    }

    public void setSteamDbLink(String steamDbLink) {
        this.SteamDbLink.set(steamDbLink);
    }

    public Long getGamePostId() {
        return GamePostId.get();
    }

    public LongProperty gamePostIdProperty() {
        return GamePostId;
    }

    public void setGamePostId(Long gamePostId) {
        this.GamePostId= new SimpleLongProperty(gamePostId);
    }

}
