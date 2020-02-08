package Controller;

import Entity.*;
import Service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class DBOperations {

    ApplicationContext ctx = new AnnotationConfigApplicationContext(Dao.JPAConfig.class);
    GameService gameService = ctx.getBean(GameService.class);
    PriceListsService priceListsService = ctx.getBean(PriceListsService.class);
    DefaultPricesService defaultPricesService = ctx.getBean(DefaultPricesService.class);
    WpPostmetaService wpPostmetaService = ctx.getBean(WpPostmetaService.class);
    SaleTypeService saleTypeService = ctx.getBean(SaleTypeService.class);
    EditionService editionService = ctx.getBean(EditionService.class);
    FilteredLanguageService filteredLanguageService = ctx.getBean(FilteredLanguageService.class);
    KindService kindService = ctx.getBean(KindService.class);
    LanguageService languageService = ctx.getBean(LanguageService.class);
    PlatformService platformService = ctx.getBean(PlatformService.class);
    RegionService regionService = ctx.getBean(RegionService.class);


    //----------------------------Games---------------------------------------

    public List<Games> getAllGames() {
        return gameService.getAll();
    }

    public Games add(Games game) throws Exception {

        game.setActive(true);
        return gameService.Add(game);
    }

        public void deleteGame(Integer id) {
        gameService.Remove(id);
    }

        public void update(Games game) { gameService.Update(game); }

        public Games getGameByName (String name) {
        return gameService.getByName(name);
        }



    //----------------------------PriceLists---------------------------------------

    public PriceLists add(PriceLists priceLists) throws Exception { return priceListsService.Add(priceLists); }

    public PriceLists add(List<PriceLists> priceLists) throws Exception { return priceListsService.Add(priceLists); }

    public List<PriceLists> getAllPriceLists() {
        return priceListsService.getAll();
    }

    public void removeAllPriceLists (){priceListsService.removeAll();}



    //----------------------------DefaultPrices---------------------------------------

    public List<DefaultPrices> getAllDefaultPrices() {
        return defaultPricesService.getAll();
    }

    public void removeAllDefaultPrices (){defaultPricesService.removeAll();}

    public DefaultPrices add(DefaultPrices defaultPrices) throws Exception { return defaultPricesService.Add(defaultPrices); }

    public DefaultPrices addDefaultPricesList(List<DefaultPrices> defaultPrices) throws Exception { return defaultPricesService.Add(defaultPrices); }



    //----------------------------wpPostMeta---------------------------------------

    public List<WpPostMeta> getWpPostmetaByMetaKey (String metaKey) {
        return wpPostmetaService.getByMetaKey(metaKey);
    }

    public List<WpPostMeta> getWpPostMetaByPostIDAndMetaKey(Long post_id, String metaKey) {
        return wpPostmetaService.getByPostIDAndMetaKey(post_id, metaKey);
    }

    public List<WpPostMeta> getWpPostMetaByPostIDAndMetaKey(Long post_id, String metaKey1, String metaKey2) {
        return wpPostmetaService.getByPostIDAndMetaKey(post_id, metaKey1, metaKey2);
    }

    public List<WpPostMeta> getWpPostMetaByPostIDAndMetaKey(Long post_id, String metaKey1, String metaKey2, String metaKey3) {
        return wpPostmetaService.getByPostIDAndMetaKey(post_id, metaKey1, metaKey2, metaKey3);
    }

    public WpPostMeta update(List<WpPostMeta> wpPostMetas) { return wpPostmetaService.Update(wpPostMetas); }




    //----------------------------SaleType---------------------------------------

    public SaleType add(SaleType saleType) throws Exception {

        return saleTypeService.Add(saleType);
    }

    public void deleteSaleTypeByName (String Name) {
        saleTypeService.deleteByName(Name);
    }

    public List<SaleType> getAllSaleType () {
        return saleTypeService.getAll();
    }

    public SaleType update (SaleType saleType) {
        return saleTypeService.Update(saleType);
    }



    //----------------------------EditionName---------------------------------------

    public ObservableList<String> getEditionNameByGameId(Integer gameId) {
        ObservableList<String> List = FXCollections.observableArrayList();
        for(Edition edition:editionService.getByGameId(gameId)){
            List.add(edition.getName());
        }
        return List;
    }

    public List<Edition> getAllEdition (){
        return editionService.getAll();
    }

    public List<Edition> getEditionByGameId(Integer gameId) {

        return editionService.getByGameId(gameId);

    }

    public Edition add(Edition edition) throws Exception {

        return editionService.Add(edition);
    }

    public void deleteEdition(Integer id) {
        editionService.Remove(id);
    }

    public void update(Edition edition) { editionService.Update(edition); }

    public Edition getEditionByName (String name) { return editionService.getByName(name); }

    public List <Edition> getEditionByGameName (String name){
        return editionService.getByGameId(gameService.getByName(name).getId());
    }

    public Edition getEditionByNameAndGameName (String EditionName, String GameName){
        return editionService.getByNameAndGameID(EditionName,gameService.getByName(GameName).getId());
    }

    //----------------------------FilteredLanguage---------------------------------------

    public ObservableList<String> getAllFilteredLanguage() {

        ObservableList<String> List = FXCollections.observableArrayList();
        for(FilteredLanguage filteredLanguage:filteredLanguageService.getAll()){
            List.add(filteredLanguage.getName());
        }
        return List;
    }

    public FilteredLanguage add(FilteredLanguage filteredLanguage) throws Exception {

        return filteredLanguageService.Add(filteredLanguage);
    }

    public void deleteFilteredLanguage(Integer id) {
        filteredLanguageService.Remove(id);
    }

    public void update(FilteredLanguage filteredLanguage) { filteredLanguageService.Update(filteredLanguage); }

    public FilteredLanguage getFilteredLanguageByName(String name) { return filteredLanguageService.getByName(name); }

    //----------------------------Kind---------------------------------------

    public ObservableList<String> getAllKind() {

        ObservableList<String> List = FXCollections.observableArrayList();
        for(Kind kind:kindService.getAll()){
            List.add(kind.getName());
        }
        return List;
    }

    public Kind add(Kind kind) throws Exception {

        return kindService.Add(kind);
    }

    public void deleteKind(Integer id) {
        kindService.Remove(id);
    }

    public void update(Kind kind) { kindService.Update(kind); }

    public Kind getKindByName (String name) { return kindService.getByName(name); }

    //----------------------------Language---------------------------------------

    public ObservableList<String> getAllLanguage() {

        ObservableList<String> List = FXCollections.observableArrayList();
        for(Language language:languageService.getAll()){
            List.add(language.getName());
        }
        return List;

    }

    public Language add(Language language) throws Exception {

        return languageService.Add(language);
    }

    public void deleteLanguage(Integer id) {
        languageService.Remove(id);
    }

    public void update(Language language) { languageService.Update(language); }

    public Language getLanguageByName (String name) { return languageService.getByName(name); }

    //----------------------------Platform---------------------------------------

    public ObservableList<String> getAllPlatform() {

        ObservableList<String> List = FXCollections.observableArrayList();
        for(Platform platform:platformService.getAll()){
            List.add(platform.getName());
        }
        return List;
    }

    public Platform add(Platform platform) throws Exception {

        return platformService.Add(platform);
    }

    public void deletePlatform(Integer id) {
        platformService.Remove(id);
    }

    public void update(Platform platform) { platformService.Update(platform); }

    public Platform getPlatformByName (String name) { return platformService.getByName(name); }



    //----------------------------Region---------------------------------------

    public List<String> getAllRegion() {
        ObservableList<String> List = FXCollections.observableArrayList();
        for(Region region:regionService.getAll()){
            List.add(region.getName());
        }
        return List;
    }

    public Region add(Region region) throws Exception {

        return regionService.Add(region);
    }

    public void deleteRegion(Integer id) {
        regionService.Remove(id);
    }

    public void update(Region region) { regionService.Update(region); }

    public Region getRegionByName (String name) { return regionService.getByName(name); }


}
