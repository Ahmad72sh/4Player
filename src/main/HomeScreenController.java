package main;

import Controller.DBOperations;
import Entity.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXToggleButton;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Region;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import main.TableObjacts.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@SuppressWarnings("Duplicates")
public class HomeScreenController implements Initializable {


    public TextField tfTitle;

    public DBOperations dbOperations = new DBOperations();


    public String editState, addState;
    public TextField tfPlatiURL;
    public TextField tfSteamDBURL;
    public StackPane spEditGame;
    public TextField tfEditName;
    public TextField tfEditPlati;
    public TextField tfEditSteamDb;
    public JFXToggleButton tbActiveGame;

    public Games editGame;
    public List<String> availableGameName = new ArrayList<>();

    public ObservableList<Games> games = FXCollections.observableArrayList();

    public ObservableList<GamesList> gamesList = FXCollections.observableArrayList();
    public ObservableList<SaleType> saleTypeList = FXCollections.observableArrayList();
    public ObservableList<String> filteredLangList = FXCollections.observableArrayList();
    public ObservableList<String> kindList = FXCollections.observableArrayList();
    public ObservableList<String> langList = FXCollections.observableArrayList();
    public ObservableList<String> platformList = FXCollections.observableArrayList();
    public ObservableList<String> regionList = FXCollections.observableArrayList();
    public VBox vBoxGamesDetails;
    public Label lblEditNameError;
    public Label lblEditPlatiError;
    public Label lblEditSteamError;
    public ScrollPane scrollPanePrices;
    public ListView lvLanguage;
    public ListView lvPlatform;
    public ListView lvGameList;
    public ListView lvKind;
    public ListView lvRegion;
    public ListView lvFilteredLanguage;
    public TextField tfAddContent;
    public StackPane spAddContent;
    public StackPane spEditContent;
    public TextField tfEditContent;
    public StackPane spSetting;
    public JFXToggleButton tbHideFilteredLanguage;
    public AnchorPane ancMain;
    public TextField tfPostId;
    public StackPane spAddGame;
    public StackPane spAddEdition;
    public TextField tfEditionTitle;
    public Label lblGameNameForEdition;
    public TextField tfEditionId;
    public TableView<EditionObject> tvEdition;
    public TableColumn<EditionObject, String> colEditionName;
    public TableColumn<EditionObject, String> colEditionId;
    public StackPane spUpdateSitePrices;
    public TextField tfDollarPrice;
    public JFXButton btnUpdateSitePrice;
    public JFXButton btnCancelUpdateSitePrice;
    public TableView<UpdateProduct> tvUnavailableItem;
    public TableColumn<UpdateProduct, String> col_U_GameName;
    public TableColumn<UpdateProduct, String> col_U_EditionName;
    public TableColumn<UpdateProduct, String> col_U_PriceDollar;
    public TableColumn<UpdateProduct, String> col_U_PriceToman;
    public TableView<UpdateProduct> tvAvailableItem;
    public TableColumn<UpdateProduct, String> col_A_GameName;
    public TableColumn<UpdateProduct, String> col_A_EditionName;
    public TableColumn<UpdateProduct, String> col_A_PriceDollar;
    public TableColumn<UpdateProduct, String> col_A_PriceToman;
    public StackPane spAddToAvailable;
    public TextField tfItemPrice;
    public JFXToggleButton tbPriceType;
    public Label lblPriceType;
    public UpdateProduct updateProductInstance = null;

    public void initialize(URL location, ResourceBundle resources) {

        updateFilteredLangList();
        updateKindList();
        updateLanguageList();
        updatePlatformList();
        updateRegionList();
        updateGamesList();

        tfDollarPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                for (UpdateProduct updateProduct : tvAvailableItem.getItems()) {
                    if (updateProduct.getPriceDollar() != null){
                        float price;
                        if (Double.parseDouble(updateProduct.getPriceDollar()) < 10) {
                            price = Math.round(Float.parseFloat(updateProduct.getPriceDollar()) * 1.2 * (Float.parseFloat(tfDollarPrice.getText()) / 1000));
                            price = price * 1000;
                        } else if (Double.parseDouble(updateProduct.getPriceDollar()) < 20) {
                            price = Math.round(Float.parseFloat(updateProduct.getPriceDollar()) * 1.15 * (Float.parseFloat(tfDollarPrice.getText()) / 1000));
                            price = price * 1000;
                        } else if (Double.parseDouble(updateProduct.getPriceDollar()) < 40) {
                            price = Math.round(Float.parseFloat(updateProduct.getPriceDollar()) * 1.1 * (Float.parseFloat(tfDollarPrice.getText()) / 1000));
                            price = price * 1000;
                        } else {
                            price = Math.round(Float.parseFloat(updateProduct.getPriceDollar()) * 1.07 * (Float.parseFloat(tfDollarPrice.getText()) / 1000));
                            price = price * 1000;
                        }
                        updateProduct.PriceToman.setValue(Float.toString(price).substring(0,Float.toString(price).indexOf(".")));
                    }
                }
            }
        });

        tbPriceType.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                lblPriceType.setText("Toman:");
                tfItemPrice.setPadding(new Insets(0,0,0,60));
                if (updateProductInstance!=null){
                    if (updateProductInstance.getPriceToman()!= null) {
                        tfItemPrice.setText(updateProductInstance.getPriceToman());
                    }
                }
            } else {
                lblPriceType.setText("Dollar:");
                tfItemPrice.setPadding(new Insets(0,0,0,50));
            }
        });

        forceTextFieldToBeNumericOnly(tfPostId);
        forceTextFieldToBeNumericOnly(tfEditionId);
        forceTextFieldToBeNumericOnly(tfDollarPrice);
        forceTextFieldToBeNumericOnly(tfItemPrice);

        initTableViews();

    }

    private void initTableViews() {

        //----------EditionName Table------------------
        tvEdition.setEditable(true);

        colEditionName.setCellValueFactory(param -> param.getValue().EditionTitle);
        colEditionName.setCellFactory(TextFieldTableCell.forTableColumn());
        colEditionName.setOnEditCommit(event -> new Thread(() -> {
            String editionName = tvEdition.getItems().get(event.getTablePosition().getRow()).getEditionTitle();
            Edition edition = dbOperations.getEditionByNameAndGameName(editionName, lblGameNameForEdition.getText());
            edition.setName(event.getNewValue());
            dbOperations.update(edition);
            Platform.runLater(() ->
                    tvEdition.getItems().get(event.getTablePosition().getRow()).setEditionTitle(event.getNewValue()));
        }).start());

        colEditionId.setCellValueFactory(param -> param.getValue().EditionID);
        colEditionId.setCellFactory(TextFieldTableCell.forTableColumn());
        colEditionId.setOnEditCommit(event -> new Thread(() -> {
            String editionName = tvEdition.getItems().get(event.getTablePosition().getRow()).getEditionTitle();
            Edition edition = dbOperations.getEditionByNameAndGameName(editionName, lblGameNameForEdition.getText());
            edition.setPostId(Long.parseLong(event.getNewValue()));
            dbOperations.update(edition);
            Platform.runLater(() ->
                    tvEdition.getItems().get(event.getTablePosition().getRow()).setEditionID(event.getNewValue()));
        }).start());

        //-------------availableProduct-----------------------
        col_A_EditionName.setCellValueFactory(param -> param.getValue().EditionName);
        col_A_GameName.setCellValueFactory(new PropertyValueFactory<>("GameName"));
        col_A_PriceDollar.setCellValueFactory(param -> param.getValue().PriceDollar);
        col_A_PriceToman.setCellValueFactory(param -> param.getValue().PriceToman);
        col_A_PriceToman.setCellFactory(TextFieldTableCell.forTableColumn());
        col_A_PriceToman.setOnEditCommit(event -> tvAvailableItem.getItems().get(event.getTablePosition().getRow()).setPriceToman(event.getNewValue()));

        //-------------availableProduct-----------------------
        col_U_EditionName.setCellValueFactory(param -> param.getValue().EditionName);
        col_U_GameName.setCellValueFactory(new PropertyValueFactory<>("GameName"));
        col_U_PriceDollar.setCellValueFactory(param -> param.getValue().PriceDollar);
        col_U_PriceToman.setCellValueFactory(param -> param.getValue().PriceToman);
        col_U_PriceToman.setCellFactory(TextFieldTableCell.forTableColumn());
        col_U_PriceToman.setOnEditCommit(event -> tvUnavailableItem.getItems().get(event.getTablePosition().getRow()).setPriceToman(event.getNewValue()));
    }


    private void updateGamesList() {
        games.clear();
        games.addAll(dbOperations.getAllGames());
        lvGameList.getItems().clear();
        for (Games g : games) {
            lvGameList.getItems().add(g.getName());
            availableGameName.add(g.getName());
        }
    }

    private void updateFilteredLangList() {
        filteredLangList.clear();
        filteredLangList.addAll(dbOperations.getAllFilteredLanguage());
        lvFilteredLanguage.getItems().clear();
        lvFilteredLanguage.getItems().addAll(filteredLangList);
    }

    private void updateKindList() {
        kindList.clear();
        kindList.addAll(dbOperations.getAllKind());
        lvKind.getItems().clear();
        lvKind.getItems().addAll(kindList);
    }

    private void updateLanguageList() {
        langList.clear();
        langList.addAll(dbOperations.getAllLanguage());
        lvLanguage.getItems().clear();
        lvLanguage.getItems().addAll(langList);
    }

    private void updatePlatformList() {
        platformList.clear();
        platformList.addAll(dbOperations.getAllPlatform());
        lvPlatform.getItems().clear();
        lvPlatform.getItems().addAll(platformList);
    }

    private void updateRegionList() {
        regionList.clear();
        regionList.addAll(dbOperations.getAllRegion());
        lvRegion.getItems().clear();
        lvRegion.getItems().addAll(regionList);
    }


    private void forceTextFieldToBeNumericOnly(TextField TextField) {
        TextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                TextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public void dialogBox(StackPane stackPaneParent, String title, String body) {

        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        JFXDialog myDialog = new JFXDialog(stackPaneParent, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);
        myDialog.transitionTypeProperty().set(JFXDialog.DialogTransition.NONE);
        jfxDialogLayout.setStyle("-fx-background-radius: 10");
        Text dialogTitle = new Text(title);
        dialogTitle.setStyle("-fx-font-weight: bold !important; -fx-font-size: 18");
        jfxDialogLayout.setHeading(dialogTitle);
        jfxDialogLayout.setBody(new Text(body));
        JFXButton button = new JFXButton("Cancel");
        button.setStyle("-fx-font-weight: bold !important;" +
                "-fx-text-fill: #0096c9;" +
                "-fx-pref-width: 70;");
        button.setOnAction(event -> myDialog.close());


        jfxDialogLayout.setActions(button);
//        myDialog.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        myDialog.show();
    }


    private Hyperlink creatHyperlink(String Name, String URL) {
        Hyperlink myHyperlink = new Hyperlink();
        myHyperlink.setText(Name);

        myHyperlink.setAccessibleHelp(URL);

        myHyperlink.setOnAction(e -> {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(new URI(URL));
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
        return myHyperlink;

    }


    public void getGamePrice(ActionEvent actionEvent) {

        new Thread(() -> {
            Platform.runLater(() -> vBoxGamesDetails.getChildren().clear());
            List<PriceLists> savedPricesLists = new ArrayList<>();
            savedPricesLists.clear();
            savedPricesLists = dbOperations.getAllPriceLists();
            gamesList.clear();
            saleTypeList.clear();
            saleTypeList.addAll(dbOperations.getAllSaleType());
            for (Games g : games) {
                gamesList.add(new GamesList(g.getName(), Integer.toString(g.getId()), g.getPlatiURL(), g.getSteamDBURL(), g.getPostId()));
//            }
//            for (GamesList gL : gamesList) {


                GamesList gL = new GamesList(g.getName(), Integer.toString(g.getId()), g.getPlatiURL(), g.getSteamDBURL(), g.getPostId());
//                List<EditionName> editions = dbOperations.getEditionByGameId(Integer.parseInt(gL.GameId.get()));
                List<Edition> editions = g.getEditionList();
                ObservableList<String> editionList = FXCollections.observableArrayList();
                for (Edition edition : editions) {
                    editionList.add(edition.getName());
                }

                TitledPane titledPane = new TitledPane();
                titledPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                titledPane.animatedProperty().setValue(false);
                titledPane.expandedProperty().setValue(false);
                AnchorPane ancGameDetail = new AnchorPane();
                ancGameDetail.setPrefHeight(Region.USE_COMPUTED_SIZE);

                VBox vBoxTypes = new VBox();
                vBoxTypes.spacingProperty().setValue(2);
                AnchorPane.setRightAnchor(vBoxTypes, 10d);
                AnchorPane.setLeftAnchor(vBoxTypes, 10d);
                AnchorPane.setTopAnchor(vBoxTypes, 4d);

                ancGameDetail.getChildren().add(vBoxTypes);
                titledPane.setContent(ancGameDetail);
                titledPane.setText(gL.GameName.getValue());
                Platform.runLater(() -> vBoxGamesDetails.getChildren().add(titledPane));

                FlowPane fpActiveSaleType = new FlowPane();
                fpActiveSaleType.setHgap(10);
                fpActiveSaleType.setVgap(10);

                Platform.runLater(() -> vBoxTypes.getChildren().add(fpActiveSaleType));


                Document doc;
                List<TableList> tableLists = new ArrayList<>();
                Element tableElements;
                String tableName;

                try {

                    doc = Jsoup.connect(gL.PlatiLink.get()).timeout(0).get();

                    Elements title = doc.select("div.games_products");

                    //Get All Type Name & All of Prices and Store In List
                    for (Element tie : title) {
                        for (int i = 0; i < tie.getElementsByClass("games-header").size(); i++) {
                            tableName = tie.getElementsByClass("games-header").get(i).text();
                            tableElements = doc.select("tbody").get(i);
                            tableLists.add(new TableList(tableName, tableElements));
                        }
                    }


                    //Extract All Data From Stored List
                    for (TableList tableList : tableLists) {

                        //new SaleType Tag
                        boolean newSaleType = true;
                        TitledPane tpTypes = new TitledPane();

                        for (SaleType saleTypeName : saleTypeList) {

                            if (saleTypeName.getName().equals(tableList.getName().substring(0, tableList.getName().indexOf("(")) + " - " + gL.GameName.get())) {

                                tpTypes.setVisible(saleTypeName.isActive());
                                newSaleType = false;
                                CheckBox checkBox = new CheckBox();
                                checkBox.setText(saleTypeName.getName().substring(0, saleTypeName.getName().lastIndexOf("-")));
                                checkBox.setSelected(saleTypeName.isActive());
                                checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {

                                    Platform.runLater(() -> {
                                        saleTypeName.setActive(newValue);
                                        tpTypes.setVisible(newValue);
                                    });
                                    new Thread(() -> dbOperations.update(saleTypeName)).start();


                                });
                                Platform.runLater(() -> fpActiveSaleType.getChildren().add(checkBox));


                            }
                        }
                        if (newSaleType) {
                            SaleType sT = new SaleType();
                            sT.setName(tableList.getName().substring(0, tableList.getName().indexOf("(")) + " - " + gL.GameName.get());
                            sT.setActive(true);

                            new Thread(() -> {
                                try {
                                    dbOperations.add(sT);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }).start();

                            CheckBox checkBox = new CheckBox();
                            checkBox.setText(sT.getName().substring(0, sT.getName().lastIndexOf("-")));
                            checkBox.setSelected(sT.isActive());
                            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {

                                Platform.runLater(() -> {
                                    sT.setActive(newValue);
                                    tpTypes.setVisible(newValue);
                                });
                                new Thread(() -> dbOperations.update(sT)).start();

                            });
                            Platform.runLater(() -> fpActiveSaleType.getChildren().add(checkBox));
                        }


                        //Create Title Pane for Type
                        tpTypes.managedProperty().bind(tpTypes.visibleProperty());
                        tpTypes.setPrefHeight(Region.USE_COMPUTED_SIZE);
                        tpTypes.setText(tableList.getName());
                        tpTypes.animatedProperty().setValue(false);
                        tpTypes.expandedProperty().setValue(false);

                        //Create AnchorPane for TitlePane Content
                        AnchorPane ancTypePrice = new AnchorPane();
                        ancTypePrice.setPrefHeight(Region.USE_COMPUTED_SIZE);
                        tpTypes.setContent(ancTypePrice);

                        //Create Table for Show Detail
                        TableView<GameTableObject> tableObjectTableView = new TableView<>();
                        TableColumn<GameTableObject, String> colItemName = new TableColumn<>("Item Name");
                        TableColumn<GameTableObject, String> colSellerName = new TableColumn<>("Seller Name");
                        TableColumn<GameTableObject, String> colSellerRate = new TableColumn<>("Seller Rate");
                        TableColumn<GameTableObject, String> colPrice = new TableColumn<>("Price");
                        TableColumn<GameTableObject, String> colRegion = new TableColumn<>("Region");
                        TableColumn<GameTableObject, String> colLanguage = new TableColumn<>("Language");
                        TableColumn<GameTableObject, String> colPlatform = new TableColumn<>("Platform");
                        TableColumn<GameTableObject, String> colKind = new TableColumn<>("Kind");
                        TableColumn<GameTableObject, String> colEdition = new TableColumn<>("EditionName");
                        TableColumn<GameTableObject, Boolean> colDefaultForSale = new TableColumn<>("Default For Sale");

                        colItemName.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
                        colItemName.setPrefWidth(400);

                        colSellerName.setCellValueFactory(param -> param.getValue().SellerName);
                        colSellerName.setMaxWidth(150);
                        colSellerName.setMinWidth(150);
                        colSellerName.setId("colSellerName");

                        colSellerRate.setCellValueFactory(param -> param.getValue().SellerRate);
                        colSellerRate.setMaxWidth(80);
                        colSellerRate.setMinWidth(80);
                        colSellerRate.setId("colSellerRate");

                        colPrice.setCellValueFactory(param -> param.getValue().Price);
                        colPrice.setMaxWidth(70);
                        colPrice.setMinWidth(70);
                        colPrice.setId("colPrice");

                        regionList.addListener((ListChangeListener<String>) c -> colRegion.setCellFactory(ComboBoxTableCell.forTableColumn(regionList)));
                        colRegion.setCellValueFactory(param -> param.getValue().Region);
                        colRegion.setCellFactory(ComboBoxTableCell.forTableColumn(regionList));
                        colRegion.setOnEditCommit(event -> tableObjectTableView.getItems().get(event.getTablePosition().getRow()).setRegion(event.getNewValue()));
                        colRegion.setMaxWidth(120);
                        colRegion.setMinWidth(120);
                        colRegion.setId("colRegion");

                        langList.addListener((ListChangeListener<String>) c -> colLanguage.setCellFactory(ComboBoxTableCell.forTableColumn(langList)));
                        colLanguage.setCellValueFactory(param -> param.getValue().Language);
                        colLanguage.setCellFactory(ComboBoxTableCell.forTableColumn(langList));
                        colLanguage.setOnEditCommit(event -> tableObjectTableView.getItems().get(event.getTablePosition().getRow()).setLanguage(event.getNewValue()));
                        colLanguage.setMaxWidth(120);
                        colLanguage.setMinWidth(120);
                        colLanguage.setId("colLanguage");

                        platformList.addListener((ListChangeListener<String>) c -> colPlatform.setCellFactory(ComboBoxTableCell.forTableColumn(platformList)));
                        colPlatform.setCellValueFactory(param -> param.getValue().Platform);
                        colPlatform.setMinWidth(100);
                        colPlatform.setMaxWidth(100);
                        colPlatform.setCellFactory(ComboBoxTableCell.forTableColumn(platformList));
                        colPlatform.setOnEditCommit(event -> tableObjectTableView.getItems().get(event.getTablePosition().getRow()).setPlatform(event.getNewValue()));
                        colPlatform.setId("colPlatform");

                        kindList.addListener((ListChangeListener<String>) c -> colKind.setCellFactory(ComboBoxTableCell.forTableColumn(kindList)));
                        colKind.setCellValueFactory(param -> param.getValue().Kind);
                        colKind.setCellFactory(ComboBoxTableCell.forTableColumn(kindList));
                        colKind.setOnEditCommit(event -> tableObjectTableView.getItems().get(event.getTablePosition().getRow()).setKind(event.getNewValue()));
                        colKind.setMaxWidth(100);
                        colKind.setMinWidth(100);
                        colKind.setId("colKind");


                        editionList.addListener((ListChangeListener<String>) c -> colEdition.setCellFactory(ComboBoxTableCell.forTableColumn(editionList)));
                        colEdition.setCellValueFactory(param -> param.getValue().Edition);
                        colEdition.setCellFactory(ComboBoxTableCell.forTableColumn(editionList));
                        colEdition.setOnEditCommit(event -> {
                            tableObjectTableView.getItems().get(event.getTablePosition().getRow()).setEdition(event.getNewValue());
                            tableObjectTableView.getItems().get(event.getTablePosition().getRow())
                                    .setEditionID(editions.stream().filter(edition -> edition.getName().equals(event.getNewValue())).findFirst().get().getPostId());
                            System.out.println(tableObjectTableView.getItems().get(event.getTablePosition().getRow()).getEditionID());
                        });
                        colEdition.setMaxWidth(120);
                        colEdition.setMinWidth(120);
                        colEdition.setId("colEdition");

                        colDefaultForSale.setCellValueFactory(new PropertyValueFactory<>("SetDefault"));
                        colDefaultForSale.setCellFactory(CheckBoxTableCell.forTableColumn(param ->
                                tableObjectTableView.getItems().get(param).setDefaultProperty()));
                        colDefaultForSale.setMaxWidth(130);
                        colDefaultForSale.setMinWidth(130);
                        colDefaultForSale.setId("colDefaultForSale");

                        tableObjectTableView.getColumns().addAll(colItemName, colSellerName, colSellerRate, colPrice,
                                colRegion, colLanguage, colPlatform, colKind, colEdition,
                                colDefaultForSale);
                        tableObjectTableView.setFixedCellSize(40);
                        tableObjectTableView.prefHeightProperty().bind(tableObjectTableView.fixedCellSizeProperty().multiply(Bindings.size(tableObjectTableView.getItems()).add(1.21)));
                        tableObjectTableView.minHeightProperty().bind(tableObjectTableView.prefHeightProperty());
                        tableObjectTableView.maxHeightProperty().bind(tableObjectTableView.prefHeightProperty());
                        tableObjectTableView.setTableMenuButtonVisible(true);
                        tableObjectTableView.setEditable(true);
                        tableObjectTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                        ancTypePrice.getChildren().addAll(tableObjectTableView);
                        AnchorPane.setTopAnchor(tableObjectTableView, -12d);
                        AnchorPane.setBottomAnchor(tableObjectTableView, -13d);
                        AnchorPane.setLeftAnchor(tableObjectTableView, -13d);
                        AnchorPane.setRightAnchor(tableObjectTableView, -13d);

                        tableObjectTableView.getSelectionModel().getSelectedItems().addListener((ListChangeListener<GameTableObject>) c -> {

                            for (GameTableObject s : tableObjectTableView.getItems()) {
                                s.ItemName.setStyle("-fx-text-fill: #0096c9");

                            }

                            for (GameTableObject s : c.getList()) {
                                s.ItemName.setStyle("-fx-text-fill: white");
                            }

                        });

                        ObservableList<GameTableObject> gameTableObjects = FXCollections.observableArrayList();
                        ObservableList<GameTableObject> gameTableObjectsWithoutFLang = FXCollections.observableArrayList();

                        Element tie = tableList.Table;
                        for (int i = 0; i < tie.getElementsByClass("product-merchant").size(); i++) {

                            String itemName = tie.getElementsByClass("product-title").get(i).text();
                            String ItemURL = "https://www.plati.market" + tie.getElementsByClass("product-title").get(i).getAllElements().get(1).getAllElements().get(1).attributes().get("href");
                            String seller = tie.getElementsByClass("product-merchant").get(i).text();
                            String sellerRate = seller.substring(seller.lastIndexOf(" "));
                            String sellerName = seller.substring(0, seller.lastIndexOf(" "));
                            String soldCont = tie.getElementsByClass("product-sold").get(i).text();
                            String price = tie.getElementsByClass("product-price-inner")
                                    .get(i).text().substring(0, tie.getElementsByClass("product-price-inner")
                                            .get(i).text().indexOf("$") - 1).replace(",", ".");

                            PriceLists pl = (savedPricesLists.stream().filter(priceLists -> priceLists.getPlatiItemURL().equals(ItemURL)).findFirst().isPresent()) ?
                                    savedPricesLists.stream().filter(priceLists -> priceLists.getPlatiItemURL().equals(ItemURL)).findFirst().get() : null;
                            if (pl != null) {

                                GameTableObject gameTableObject = new GameTableObject(gL.getGamePostId(), creatHyperlink(itemName, ItemURL), sellerName, sellerRate, soldCont, price);
                                gameTableObject.setRegion(pl.getRegion());
                                gameTableObject.setLanguage(pl.getLanguage());
                                gameTableObject.setPlatform(pl.getPlatform());
                                gameTableObject.setKind(pl.getKind());
                                if (pl.getEditionName() != null) gameTableObject.setEdition(pl.getEditionName());
                                if (pl.getEditionID() != null) gameTableObject.setEditionID(pl.getEditionID());
                                gameTableObject.setSetDefault(pl.isDefaultForSale());


                                if (filteredLangList.contains(pl.getLanguage())) {
                                    gameTableObjects.add(gameTableObject);
                                } else {
                                    gameTableObjectsWithoutFLang.add(gameTableObject);
                                    gameTableObjects.add(gameTableObject);
                                }


                            } else {
                                GameTableObject gameTableObject = new GameTableObject(gL.getGamePostId(), creatHyperlink(itemName, ItemURL), sellerName, sellerRate, soldCont, price);

                                //Automatic Set Editions
                                for (Edition ed : editions.stream().sorted(Comparator.comparing(edition -> edition.getName())).collect(Collectors.toList())) {
                                    if (tableList.getName().contains(ed.getName())) {
                                        gameTableObject.setEdition(ed.getName());
                                        gameTableObject.setEditionID(ed.getPostId());
                                    }
                                }

                                //Automatic set Platform
                                for (String p : platformList) {
                                    if (tableList.getName().contains(p)) {
                                        gameTableObject.setPlatform(p);
                                    }
                                }


                                //Automatic set Kind
                                for (String k : kindList) {
                                    if (tableList.getName().contains(k)) {
                                        gameTableObject.setKind(k);
                                    }
                                }

                                gameTableObject.setSetDefault(false);
                                gameTableObjectsWithoutFLang.add(gameTableObject);
                                gameTableObjects.add(gameTableObject);
                            }

                        }

                        if (tbHideFilteredLanguage.isSelected()) {
                            tableObjectTableView.getItems().addAll(gameTableObjectsWithoutFLang);
                        } else {
                            tableObjectTableView.getItems().addAll(gameTableObjects);
                        }

                        tbHideFilteredLanguage.selectedProperty().addListener((observable, oldValue, newValue) -> {
                            if (newValue) {
                                Platform.runLater(() -> {
                                    tableObjectTableView.getItems().clear();
                                    tableObjectTableView.getItems().addAll(gameTableObjectsWithoutFLang);
                                });

                            } else {
                                Platform.runLater(() -> {
                                    tableObjectTableView.getItems().clear();
                                    tableObjectTableView.getItems().addAll(gameTableObjects);
                                });

                            }
                        });


                        Platform.runLater(() -> vBoxTypes.getChildren().add(tpTypes));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }).start();

    }

    public void addNewGame(ActionEvent actionEvent) {

        spAddGame.setVisible(true);

    }

    public void editGame(ActionEvent actionEvent) {
        if (!lvGameList.getSelectionModel().isEmpty()) {
            spEditGame.setVisible(true);
            editGame = games.stream().filter(games1 -> games1.getName().equals(lvGameList.getSelectionModel().getSelectedItem().toString())).findFirst().get();

            availableGameName.remove(editGame.getName());

            tfEditName.setText(editGame.getName());
            tfEditPlati.setText(editGame.getPlatiURL());
            tfSteamDBURL.setText(editGame.getSteamDBURL());
            tbActiveGame.selectedProperty().setValue(editGame.isActive());
        } else {
            dialogBox(spSetting, "Error !!!", "Choose an item first.");
        }

    }

    public void deleteGame(ActionEvent actionEvent) {

        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        JFXDialog myDialog = new JFXDialog(spSetting, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);
        myDialog.transitionTypeProperty().set(JFXDialog.DialogTransition.NONE);
        Text dialogTitle = new Text("Delete Game!");
        dialogTitle.setStyle("-fx-font-weight: bold !important; -fx-font-size: 18");
        jfxDialogLayout.setHeading(dialogTitle);
        jfxDialogLayout.setBody(new Text("Are you sure to delete \"" + lvGameList.getSelectionModel().getSelectedItem().toString() + "\" ?"));
        JFXButton button = new JFXButton("Cancel");
        button.setStyle("-fx-font-weight: bold !important;" +
                "-fx-text-fill: #0096c9;" +
                "-fx-pref-width: 70;");
        button.setOnAction(event -> myDialog.close());
        JFXButton btnDelete = new JFXButton("Delete");
        btnDelete.setStyle("-fx-font-weight: bold !important;" +
                "-fx-text-fill: #FF4800;" +
                "-fx-pref-width: 70;");
        btnDelete.setOnAction(event -> {
            new Thread(() -> {

                Games g = games.stream().filter(games1 -> games1.getName().equals(lvGameList.getSelectionModel().getSelectedItem().toString())).findAny().get();
                dbOperations.deleteGame(g.getId());
                Platform.runLater(() -> {
                    updateGamesList();
                });

            }).start();
            myDialog.close();

        });

        jfxDialogLayout.setActions(button, btnDelete);
        myDialog.show();

    }

    public void OkEdit(ActionEvent actionEvent) {

        String oldName = editGame.getName();
        String oldPlati = editGame.getPlatiURL();
        String oldSteam = editGame.getSteamDBURL();

        boolean name = false;
        boolean url = false;

        if (!tfEditName.getText().isEmpty() && !availableGameName.stream().filter(o -> o.toLowerCase().equals(tfEditName.getText().toLowerCase())).findFirst().isPresent() && !oldName.equals(tfEditName.getText())) {
            editGame.setName(tfEditName.getText());
            lblEditNameError.setText("");
            name = true;
        } else if (tfEditName.getText().isEmpty()) {
            lblEditNameError.setText("نام بازی را وارد کنید");
            name = false;
        } else if (availableGameName.stream().filter(o -> o.toLowerCase().equals(tfEditName.getText().toLowerCase())).findFirst().isPresent()) {
            lblEditNameError.setText("نام بازی تکراری است");
            name = false;
        }

        if (!tfEditPlati.getText().isEmpty() && !tfEditSteamDb.getText().isEmpty() && (!oldPlati.equals(tfEditPlati.getText()) || !oldSteam.equals(tfEditSteamDb.getText()))) {
            editGame.setSteamDBURL(tfEditSteamDb.getText());
            editGame.setPlatiURL(tfEditPlati.getText());
            lblEditPlatiError.setText("");
            lblEditSteamError.setText("");
            url = true;
        } else if (tfEditPlati.getText().isEmpty() && tfEditSteamDb.getText().isEmpty()) {
            lblEditPlatiError.setText("لینک Plati را وارد کنید");
            lblEditSteamError.setText("لینک SteamDB را وارد کنید");
            url = false;
        }

        if (name || url) {
            new Thread(() -> {
                dbOperations.update(editGame);
                editGame = null;
                Platform.runLater(() -> updateGamesList());
            }).start();
            spEditGame.setVisible(false);
            tfEditName.setText("");
            tfEditPlati.setText("");
            tfSteamDBURL.setText("");
            tfSteamDBURL.setText("");
        } else {
            cancelEdit(null);
        }

    }

    public void cancelEdit(ActionEvent actionEvent) {
        spEditGame.setVisible(false);
        tfEditName.setText("");
        tfEditPlati.setText("");
        tfSteamDBURL.setText("");
        tfSteamDBURL.setText("");
    }

    public void updateAllPrices(ActionEvent actionEvent) {
        new Thread(() -> {
            List<PriceLists> priceLists = new ArrayList<>();
            List<DefaultPrices> defaultPrices = new ArrayList<>();
            for (Node n : vBoxGamesDetails.getChildren()) {

                TitledPane tp = (TitledPane) n;
                AnchorPane anc = (AnchorPane) tp.getContent();
                VBox vBox1 = (VBox) anc.getChildren().get(0);

//                for (Node n2 : vBox1.getChildren()) {
                for (int i = 1; i < vBox1.getChildren().size(); i++) {

                    TitledPane tp2 = (TitledPane) vBox1.getChildren().get(i);
                    AnchorPane anc2 = (AnchorPane) tp2.getContent();

                    TableView<GameTableObject> tv = (TableView<GameTableObject>) anc2.getChildren().get(0);

                    for (GameTableObject g : tv.getItems()) {

                        PriceLists pL = new PriceLists();

                        pL.setName(tp.getText());
                        pL.setPlatiItemURL(g.ItemName.getAccessibleHelp());
                        pL.setPrice(g.Price.get());
                        pL.setRegion(g.getRegion());
                        pL.setLanguage(g.getLanguage());
                        pL.setPlatform(g.getPlatform());
                        pL.setKind(g.getKind());
                        if (g.getEditionID() != 0) pL.setEditionID(g.getEditionID());
                        pL.setDefaultForSale(g.isSetDefault());
                        pL.setEditionName(g.getEdition());

                        priceLists.add(pL);

                        if (g.isSetDefault()) {
                            DefaultPrices dP = new DefaultPrices();

                            dP.setGameID(g.getGameID());
                            dP.setPrice(g.getPrice());
                            dP.setRegion(g.getRegion());
                            dP.setLanguage(g.getLanguage());
                            dP.setPlatform(g.getPlatform());
                            dP.setKind(g.getKind());
                            if (g.getEditionID() != 0) dP.setEditionID(g.getEditionID());
                            dP.setEditionName(g.getEdition());
                            dP.setGameName(tp.getText());

                            defaultPrices.add(dP);
                        }


                    }

                }

            }

            dbOperations.removeAllPriceLists();

            try {
                dbOperations.add(priceLists);
            } catch (Exception e) {
                e.printStackTrace();
            }

            dbOperations.removeAllDefaultPrices();

            try {
                dbOperations.addDefaultPricesList(defaultPrices);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();


    }

    public void exitSettings(ActionEvent actionEvent) {

        spSetting.setVisible(false);
        ancMain.setVisible(true);


    }

    public void openSetting(ActionEvent actionEvent) {

        ancMain.setVisible(false);
        spSetting.setVisible(true);

    }

    public void addLanguage(ActionEvent actionEvent) {
        addState = "Language";
        spAddContent.setVisible(true);
    }

    public void editLanguage(ActionEvent actionEvent) {
        if (!lvLanguage.getSelectionModel().isEmpty()) {
            editState = "Language";
            spEditContent.setVisible(true);
            tfEditContent.setText(lvLanguage.getSelectionModel().getSelectedItem().toString());
        } else {
            dialogBox(spSetting, "Error !!!", "Choose an item first.");
        }
    }

    public void deleteLanguage(ActionEvent actionEvent) {
        if (!lvLanguage.getSelectionModel().isEmpty()) {
            JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
            JFXDialog myDialog = new JFXDialog(spSetting, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);
            myDialog.transitionTypeProperty().set(JFXDialog.DialogTransition.NONE);
            Text dialogTitle = new Text("Delete Language!");
            dialogTitle.setStyle("-fx-font-weight: bold !important; -fx-font-size: 18");
            jfxDialogLayout.setHeading(dialogTitle);
            jfxDialogLayout.setBody(new Text("Are you sure to delete \"" + lvLanguage.getSelectionModel().getSelectedItem().toString() + "\" ?"));
            JFXButton button = new JFXButton("Cancel");
            button.setStyle("-fx-font-weight: bold !important;" +
                    "-fx-text-fill: #0096c9;" +
                    "-fx-pref-width: 70;");
            button.setOnAction(event -> myDialog.close());
            JFXButton btnDelete = new JFXButton("Delete");
            btnDelete.setStyle("-fx-font-weight: bold !important;" +
                    "-fx-text-fill: #FF4800;" +
                    "-fx-pref-width: 70;");
            btnDelete.setOnAction(event -> new Thread(() -> {
                Platform.runLater(() -> myDialog.close());
                Language g = dbOperations.getLanguageByName(lvLanguage.getSelectionModel().getSelectedItem().toString());
                dbOperations.deleteLanguage(g.getId());
                Platform.runLater(() -> updateLanguageList());
            }).start());
            jfxDialogLayout.setActions(button, btnDelete);
            myDialog.show();

        } else {
            dialogBox(spSetting, "Error !!!", "Choose an item first.");
        }
    }

    public void addPlatform(ActionEvent actionEvent) {
        addState = "Platform";
        spAddContent.setVisible(true);
    }

    public void editPlatform(ActionEvent actionEvent) {
        if (!lvPlatform.getSelectionModel().isEmpty()) {
            editState = "Platform";
            spEditContent.setVisible(true);
            tfEditContent.setText(lvPlatform.getSelectionModel().getSelectedItem().toString());
        } else {
            dialogBox(spSetting, "Error !!!", "Choose an item first.");
        }
    }

    public void deletePlatform(ActionEvent actionEvent) {
        if (!lvPlatform.getSelectionModel().isEmpty()) {
            JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
            JFXDialog myDialog = new JFXDialog(spSetting, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);
            myDialog.transitionTypeProperty().set(JFXDialog.DialogTransition.NONE);
            Text dialogTitle = new Text("Delete Platform!");
            dialogTitle.setStyle("-fx-font-weight: bold !important; -fx-font-size: 18");
            jfxDialogLayout.setHeading(dialogTitle);
            jfxDialogLayout.setBody(new Text("Are you sure to delete \"" + lvPlatform.getSelectionModel().getSelectedItem().toString() + "\" ?"));
            JFXButton button = new JFXButton("Cancel");
            button.setStyle("-fx-font-weight: bold !important;" +
                    "-fx-text-fill: #0096c9;" +
                    "-fx-pref-width: 70;");
            button.setOnAction(event -> myDialog.close());
            JFXButton btnDelete = new JFXButton("Delete");
            btnDelete.setStyle("-fx-font-weight: bold !important;" +
                    "-fx-text-fill: #FF4800;" +
                    "-fx-pref-width: 70;");
            btnDelete.setOnAction(event -> new Thread(() -> {
                Platform.runLater(() -> myDialog.close());
                Entity.Platform g = dbOperations.getPlatformByName(lvPlatform.getSelectionModel().getSelectedItem().toString());
                dbOperations.deletePlatform(g.getId());
                Platform.runLater(() -> updatePlatformList());
            }).start());
            jfxDialogLayout.setActions(button, btnDelete);
            myDialog.show();

        } else {
            dialogBox(spSetting, "Error !!!", "Choose an item first.");
        }
    }

    public void addKind(ActionEvent actionEvent) {
        addState = "Kind";
        spAddContent.setVisible(true);
    }

    public void editKind(ActionEvent actionEvent) {
        if (!lvKind.getSelectionModel().isEmpty()) {
            editState = "Kind";
            spEditContent.setVisible(true);
            tfEditContent.setText(lvKind.getSelectionModel().getSelectedItem().toString());
        } else {
            dialogBox(spSetting, "Error !!!", "Choose an item first.");
        }
    }

    public void deleteKind(ActionEvent actionEvent) {
        if (!lvKind.getSelectionModel().isEmpty()) {
            JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
            JFXDialog myDialog = new JFXDialog(spSetting, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);
            myDialog.transitionTypeProperty().set(JFXDialog.DialogTransition.NONE);
            Text dialogTitle = new Text("Delete Kind!");
            dialogTitle.setStyle("-fx-font-weight: bold !important;; -fx-font-size: 18");
            jfxDialogLayout.setHeading(dialogTitle);
            jfxDialogLayout.setBody(new Text("Are you sure to delete \"" + lvKind.getSelectionModel().getSelectedItem().toString() + "\" ?"));
            JFXButton button = new JFXButton("Cancel");
            button.setStyle("-fx-font-weight: bold !important;" +
                    "-fx-text-fill: #0096c9;" +
                    "-fx-pref-width: 70;");
            button.setOnAction(event -> myDialog.close());
            JFXButton btnDelete = new JFXButton("Delete");
            btnDelete.setStyle("-fx-font-weight: bold !important;" +
                    "-fx-text-fill: #FF4800;" +
                    "-fx-pref-width: 70;");
            btnDelete.setOnAction(event -> {
                new Thread(() -> {
                    Platform.runLater(() -> myDialog.close());
                    Kind g = dbOperations.getKindByName(lvKind.getSelectionModel().getSelectedItem().toString());
                    dbOperations.deleteKind(g.getId());
                    Platform.runLater(() -> updateKindList());
                }).start();
            });
            jfxDialogLayout.setActions(button, btnDelete);
            myDialog.show();

        } else {
            dialogBox(spSetting, "Error !!!", "Choose an item first.");
        }
    }

    public void addRegion(ActionEvent actionEvent) {
        addState = "Region";
        spAddContent.setVisible(true);
    }

    public void editRegion(ActionEvent actionEvent) {
        if (!lvRegion.getSelectionModel().isEmpty()) {
            editState = "Region";
            spEditContent.setVisible(true);
            tfEditContent.setText(lvRegion.getSelectionModel().getSelectedItem().toString());
        } else {
            dialogBox(spSetting, "Error !!!", "Choose an item first.");
        }
    }

    public void deleteRegion(ActionEvent actionEvent) {
        if (!lvRegion.getSelectionModel().isEmpty()) {
            JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
            JFXDialog myDialog = new JFXDialog(spSetting, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);
            myDialog.transitionTypeProperty().set(JFXDialog.DialogTransition.NONE);
            Text dialogTitle = new Text("Delete Region!");
            dialogTitle.setStyle("-fx-font-weight: bold !important; -fx-font-size: 18");
            jfxDialogLayout.setHeading(dialogTitle);
            jfxDialogLayout.setBody(new Text("Are you sure to delete \"" + lvRegion.getSelectionModel().getSelectedItem().toString() + "\" ?"));
            JFXButton button = new JFXButton("Cancel");
            button.setStyle("-fx-font-weight: bold !important;" +
                    "-fx-text-fill: #0096c9;" +
                    "-fx-pref-width: 70;");
            button.setOnAction(event -> myDialog.close());
            JFXButton btnDelete = new JFXButton("Delete");
            btnDelete.setStyle("-fx-font-weight: bold !important;" +
                    "-fx-text-fill: #FF4800;" +
                    "-fx-pref-width: 70;");
            btnDelete.setOnAction(event -> new Thread(() -> {
                Platform.runLater(() -> myDialog.close());
                Entity.Region g = dbOperations.getRegionByName(lvRegion.getSelectionModel().getSelectedItem().toString());
                dbOperations.deleteRegion(g.getId());
                Platform.runLater(() -> updateRegionList());
            }).start());
            jfxDialogLayout.setActions(button, btnDelete);
            myDialog.show();

        } else {
            dialogBox(spSetting, "Error !!!", "Choose an item first.");
        }
    }

    public void addFilteredLanguage(ActionEvent actionEvent) {
        addState = "FilteredLanguage";
        spAddContent.setVisible(true);
    }

    public void editFilteredLanguage(ActionEvent actionEvent) {
        if (!lvFilteredLanguage.getSelectionModel().isEmpty()) {
            editState = "FilteredLanguage";
            spEditContent.setVisible(true);
            tfEditContent.setText(lvFilteredLanguage.getSelectionModel().getSelectedItem().toString());
        } else {
            dialogBox(spSetting, "Error !!!", "Choose an item first.");
        }
    }

    public void deleteFilteredLanguage(ActionEvent actionEvent) {
        if (!lvFilteredLanguage.getSelectionModel().isEmpty()) {
            JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
            JFXDialog myDialog = new JFXDialog(spSetting, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);
            myDialog.transitionTypeProperty().set(JFXDialog.DialogTransition.NONE);
            Text dialogTitle = new Text("Delete Filtered Language!");
            dialogTitle.setStyle("-fx-font-weight: bold !important; -fx-font-size: 18");
            jfxDialogLayout.setHeading(dialogTitle);
            jfxDialogLayout.setBody(new Text("Are you sure to delete \"" + lvFilteredLanguage.getSelectionModel().getSelectedItem().toString() + "\" ?"));
            JFXButton button = new JFXButton("Cancel");
            button.setStyle("-fx-font-weight: bold !important;" +
                    "-fx-text-fill: #0096c9;" +
                    "-fx-pref-width: 70;");
            button.setOnAction(event -> myDialog.close());
            JFXButton btnDelete = new JFXButton("Delete");
            btnDelete.setStyle("-fx-font-weight: bold !important;" +
                    "-fx-text-fill: #FF4800;" +
                    "-fx-pref-width: 70;");
            btnDelete.setOnAction(event -> new Thread(() -> {
                Platform.runLater(() -> myDialog.close());
                FilteredLanguage g = dbOperations.getFilteredLanguageByName(lvFilteredLanguage.getSelectionModel().getSelectedItem().toString());
                dbOperations.deleteFilteredLanguage(g.getId());
                Platform.runLater(() -> updateFilteredLangList());
            }).start());
            jfxDialogLayout.setActions(button, btnDelete);
            myDialog.show();

        } else {
            dialogBox(spSetting, "Error !!!", "Choose an item first.");
        }
    }

    public void addNewContent(ActionEvent actionEvent) {

        new Thread(() -> {

            Platform.runLater(() -> spAddContent.setVisible(false));
            switch (addState) {
                case "FilteredLanguage":
                    FilteredLanguage filteredLanguage = new FilteredLanguage();
                    filteredLanguage.setName(tfAddContent.getText());
                    try {
                        dbOperations.add(filteredLanguage);
                        Platform.runLater(() -> lvFilteredLanguage.getItems().add(tfAddContent.getText()));
                        filteredLangList.add(tfAddContent.getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case "Kind":
                    Kind kind = new Kind();
                    kind.setName(tfAddContent.getText());
                    try {
                        dbOperations.add(kind);
                        Platform.runLater(() -> lvKind.getItems().add(tfAddContent.getText()));
                        kindList.add(tfAddContent.getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case "Language":
                    Language language = new Language();
                    language.setName(tfAddContent.getText());
                    try {
                        dbOperations.add(language);
                        Platform.runLater(() -> lvLanguage.getItems().add(tfAddContent.getText()));
                        langList.add(tfAddContent.getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case "Platform":
                    Entity.Platform platform = new Entity.Platform();
                    platform.setName(tfAddContent.getText());
                    try {
                        dbOperations.add(platform);
                        Platform.runLater(() -> lvPlatform.getItems().add(tfAddContent.getText()));
                        platformList.add(tfAddContent.getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case "Region":

                    Entity.Region region = new Entity.Region();
                    region.setName(tfAddContent.getText());
                    try {
                        dbOperations.add(region);
                        Platform.runLater(() -> lvRegion.getItems().add(tfAddContent.getText()));
                        regionList.add(tfAddContent.getText());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
            }

            Platform.runLater(() -> tfAddContent.setText(""));
        }).start();


    }

    public void cancelAddContent(ActionEvent actionEvent) {

        Platform.runLater(() -> {
            spAddContent.setVisible(false);
            tfAddContent.setText("");
        });

    }

    public void editContent(ActionEvent actionEvent) {

        new Thread(() -> {
            Platform.runLater(() -> spEditContent.setVisible(false));
            switch (editState) {
                case "FilteredLanguage":
                    FilteredLanguage filteredLanguage = dbOperations.getFilteredLanguageByName(lvFilteredLanguage.getSelectionModel().getSelectedItem().toString());
                    filteredLanguage.setName(tfEditContent.getText());
                    try {
                        dbOperations.update(filteredLanguage);
                        Platform.runLater(this::updateFilteredLangList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case "Kind":
                    Kind kind = dbOperations.getKindByName(lvKind.getSelectionModel().getSelectedItem().toString());
                    kind.setName(tfEditContent.getText());
                    try {
                        dbOperations.update(kind);
                        Platform.runLater(this::updateKindList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case "Language":
                    Language language = dbOperations.getLanguageByName(lvLanguage.getSelectionModel().getSelectedItem().toString());
                    language.setName(tfEditContent.getText());
                    try {
                        dbOperations.update(language);
                        Platform.runLater(this::updateLanguageList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case "Platform":
                    Entity.Platform platform = dbOperations.getPlatformByName(lvPlatform.getSelectionModel().getSelectedItem().toString());
                    platform.setName(tfEditContent.getText());
                    try {
                        dbOperations.update(platform);
                        Platform.runLater(this::updatePlatformList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case "Region":
                    Entity.Region region = dbOperations.getRegionByName(lvRegion.getSelectionModel().getSelectedItem().toString());
                    region.setName(tfEditContent.getText());
                    try {
                        dbOperations.update(region);
                        Platform.runLater(this::updateRegionList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
            }

            Platform.runLater(() -> tfAddContent.setText(""));
        }).start();

    }

    public void cancelEditContent(ActionEvent actionEvent) {

        Platform.runLater(() -> {
            spEditContent.setVisible(false);
            tfEditContent.setText("");
        });

    }

    public void addGame(ActionEvent actionEvent) {

        tfTitle.setPromptText((tfTitle.getText().isEmpty()) ? "Enter Game Name" : "");
        tfPostId.setPromptText(tfPostId.getText().isEmpty() ? "Enter Post ID" : "");
        if (tfPlatiURL.getText().isEmpty() && tfSteamDBURL.getText().isEmpty()) {
            tfSteamDBURL.setPromptText("Enter SteamDB URL");
            tfPlatiURL.setPromptText("Enter Plati URL");
        } else {
            tfPlatiURL.setPromptText("");
            tfSteamDBURL.setPromptText("");
        }

        if (!tfTitle.getText().isEmpty() && !tfPostId.getText().isEmpty() && (!tfPlatiURL.getText().isEmpty() || !tfSteamDBURL.getText().isEmpty())) {
            tfTitle.setPromptText("");
            tfPostId.setPromptText("");
            tfPlatiURL.setPromptText("");
            tfSteamDBURL.setPromptText("");

            new Thread(() -> {
                Games game = new Games();
                game.setName(tfTitle.getText());
                game.setPostId(new Long(tfPostId.getText()));
                game.setPlatiURL(tfPlatiURL.getText());
                game.setSteamDBURL(tfSteamDBURL.getText());
                try {
                    dbOperations.add(game);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                Platform.runLater(() -> {
                    updateGamesList();
                    tfTitle.setText("");
                    tfPlatiURL.setText("");
                    tfSteamDBURL.setText("");
                    spAddGame.setVisible(false);
                });

            }).start();


        }
    }

    public void cancelAddGame(ActionEvent actionEvent) {

        Platform.runLater(() -> {
            spAddGame.setVisible(false);
            tfTitle.setText("");
            tfPostId.setText("");
            tfPlatiURL.setText("");
            tfSteamDBURL.setText("");
        });

    }

    public void deleteEdition(ActionEvent actionEvent) {

        if (!tvEdition.getSelectionModel().isEmpty()) {

            JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
            JFXDialog myDialog = new JFXDialog(spAddEdition, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);
            myDialog.transitionTypeProperty().set(JFXDialog.DialogTransition.NONE);
            Text dialogTitle = new Text("Delete EditionName!");
            dialogTitle.setStyle("-fx-font-weight: bold !important; -fx-font-size: 18;");
            jfxDialogLayout.setHeading(dialogTitle);
            jfxDialogLayout.setBody(new Text("Are you sure to delete \"" + tvEdition.getSelectionModel().getSelectedItem().getEditionTitle() + "\" ?"));
            JFXButton button = new JFXButton("Cancel");
            button.setStyle("-fx-font-weight: bold !important;" +
                    "-fx-text-fill: #0096c9;" +
                    "-fx-pref-width: 70;");
            button.setOnAction(event -> myDialog.close());
            JFXButton btnDelete = new JFXButton("Delete");
            btnDelete.setStyle("-fx-font-weight: bold !important;" +
                    "-fx-text-fill: #FF4800;" +
                    "-fx-pref-width: 70;");
            btnDelete.setOnAction(event -> new Thread(() -> {
                Platform.runLater(() -> myDialog.close());
                Edition edition = dbOperations.getEditionByNameAndGameName(
                        tvEdition.getSelectionModel().getSelectedItem().getEditionTitle(), lblGameNameForEdition.getText());
                dbOperations.deleteEdition(edition.getId());
                Platform.runLater(() -> tvEdition.getItems().removeIf(editionObject -> editionObject.getEditionTitle().equals(edition.getName())));
            }).start());
            jfxDialogLayout.setActions(button, btnDelete);
            myDialog.show();

        } else {
            dialogBox(spAddEdition, "Error !!!", "Choose an item first.");
        }

    }

    public void cancelAddEdition(ActionEvent actionEvent) {

        Platform.runLater(() -> {
            spAddEdition.setVisible(false);
            lblGameNameForEdition.setText("");
            tfEditionId.setText("");
            tfEditionTitle.setText("");
            tvEdition.getItems().clear();
        });


    }

    public void AddEdition(ActionEvent actionEvent) {

        Platform.runLater(() -> {
            tfEditionTitle.setPromptText((tfEditionTitle.getText().isEmpty()) ? "Enter EditionName Title" : "");
            tfEditionId.setPromptText((tfEditionId.getText().isEmpty()) ? "Enter EditionName ID" : "");

            if (!tfEditionTitle.getText().isEmpty() && !tfEditionId.getText().isEmpty()) {
                new Thread(() -> {
                    try {
                        Edition edition = new Edition();
                        edition.setName(tfEditionTitle.getText());
                        edition.setPostId(new Long(tfEditionId.getText()));
                        edition.setGame(dbOperations.getGameByName(lblGameNameForEdition.getText()));
                        dbOperations.add(edition);
                        Platform.runLater(() -> {
                            tvEdition.getItems().add(new EditionObject(tfEditionTitle.getText(), tfEditionId.getText()));
                            tfEditionTitle.setText("");
                            tfEditionId.setText("");
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }

        });

    }

    public void AddOrEditEdition(ActionEvent actionEvent) {

        if (!lvGameList.getSelectionModel().isEmpty()) {
            new Thread(() -> {
                Platform.runLater(() -> {
                    spAddEdition.setVisible(true);
                    lblGameNameForEdition.setText(lvGameList.getSelectionModel().getSelectedItem().toString());
                });
                for (Edition ed : dbOperations.getEditionByGameName(lvGameList.getSelectionModel().getSelectedItem().toString())) {
                    Platform.runLater(() -> tvEdition.getItems().add(new EditionObject(ed.getName(), ed.getPostId().toString())));
                }
            }).start();

        } else {
            dialogBox(spSetting, "Error !!!", "Choose an item first.");
        }


    }

    public void updateAllSitePrices(ActionEvent actionEvent) {

        //TODO: تنظیم آیتم های ناموجود و موجود در جدول هایشان
        List<Games> games = dbOperations.getAllGames();
        List<DefaultPrices> dP = dbOperations.getAllDefaultPrices();

        tvAvailableItem.getItems().clear();
        tvUnavailableItem.getItems().clear();
        for (Games game : games) {
            // اگر بازی در لیست قیمت های موجود قرار داشت
            if (dP.stream().anyMatch(defaultPrices -> defaultPrices.getGameID().equals(game.getPostId()))) {
                // اگر بازی در لیست قیمت بود ولی دارای ادیشن نبود
                if (game.getEditionList().size() == 0) {
                    DefaultPrices myDp = dP.stream().filter(defaultPrices -> defaultPrices.getGameID().equals(game.getPostId())).findAny().get();
                    UpdateProduct updateProduct = new UpdateProduct(myDp.getGameID(), creatHyperlink(game.getName(),
                            game.getPlatiURL()), myDp.getPrice(), null, myDp.getRegion(), myDp.getLanguage(),
                            myDp.getPlatform(), myDp.getKind(), myDp.getEditionName(), (myDp.getEditionID() != null) ? myDp.getEditionID() : 0L);

                    // تنظیم قیمت به تومان در صورتی که تکست فیلد قیمت دلار خالی نباشد
                    if (!tfDollarPrice.getText().isEmpty()) {
                        float price;
                        if (Double.parseDouble(myDp.getPrice()) < 10) {
                            price = Math.round(Float.parseFloat(myDp.getPrice()) * 1.2 * (Float.parseFloat(tfDollarPrice.getText()) / 1000));
                            price = price * 1000;
                        } else if (Double.parseDouble(myDp.getPrice()) < 20) {
                            price = Math.round(Float.parseFloat(myDp.getPrice()) * 1.15 * (Float.parseFloat(tfDollarPrice.getText()) / 1000));
                            price = price * 1000;
                        } else if (Double.parseDouble(myDp.getPrice()) < 40) {
                            price = Math.round(Float.parseFloat(myDp.getPrice()) * 1.1 * (Float.parseFloat(tfDollarPrice.getText()) / 1000));
                            price = price * 1000;
                        } else {
                            price = Math.round(Float.parseFloat(myDp.getPrice()) * 1.07 * (Float.parseFloat(tfDollarPrice.getText()) / 1000));
                            price = price * 1000;
                        }
                        updateProduct.setPriceToman(Float.toString(price).substring(0,Float.toString(price).indexOf(".")));
                    }

                    tvAvailableItem.getItems().add(updateProduct);
                // اگر بازی در لیست قیمت بود و دارای ادیشن بود
                } else {
                    for (Edition edition : game.getEditionList()) {
                        if (dP.stream().anyMatch(defaultPrices -> String.valueOf(defaultPrices.getEditionID()).equals(String.valueOf(edition.getPostId())))) {
                            DefaultPrices myDp = dP.stream().filter(defaultPrices -> String.valueOf(defaultPrices.getEditionID()).equals(String.valueOf(edition.getPostId()))).findAny().get();
                            UpdateProduct updateProduct = new UpdateProduct(myDp.getGameID(), creatHyperlink(game.getName(),
                                    game.getPlatiURL()), myDp.getPrice(), null, myDp.getRegion(), myDp.getLanguage(),
                                    myDp.getPlatform(), myDp.getKind(), myDp.getEditionName(), myDp.getEditionID());

                            // تنظیم قیمت به تومان در صورتی که تکست فیلد قیمت دلار خالی نباشد
                            if (!tfDollarPrice.getText().isEmpty()) {
                                float price;
                                if (Double.parseDouble(myDp.getPrice()) < 10) {
                                    price = Math.round(Float.parseFloat(myDp.getPrice()) * 1.2 * (Float.parseFloat(tfDollarPrice.getText()) / 1000));
                                    price = price * 1000;
                                } else if (Double.parseDouble(myDp.getPrice()) < 20) {
                                    price = Math.round(Float.parseFloat(myDp.getPrice()) * 1.15 * (Float.parseFloat(tfDollarPrice.getText()) / 1000));
                                    price = price * 1000;
                                } else if (Double.parseDouble(myDp.getPrice()) < 40) {
                                    price = Math.round(Float.parseFloat(myDp.getPrice()) * 1.1 * (Float.parseFloat(tfDollarPrice.getText()) / 1000));
                                    price = price * 1000;
                                } else {
                                    price = Math.round(Float.parseFloat(myDp.getPrice()) * 1.07 * (Float.parseFloat(tfDollarPrice.getText()) / 1000));
                                    price = price * 1000;
                                }
                                updateProduct.setPriceToman(Float.toString(price).substring(0,Float.toString(price).indexOf(".")));
                            }

                            tvAvailableItem.getItems().add(updateProduct);
                        // اگر یک یا چند ادیشن بازی در لیست قیمت های موجود نبود
                        } else {
                            UpdateProduct updateProduct = new UpdateProduct(game.getPostId(), creatHyperlink(game.getName(),
                                    game.getPlatiURL()), null, null, null, null,
                                    null, null, edition.getName(), edition.getPostId());

                            tvUnavailableItem.getItems().add(updateProduct);
                        }
                    }
                }

            // اگر بازی در لیست قیمت ها نبود
            } else {

                //اگر بازی که در لیست قیمت ها نیست ادیشن نداشت
                if (game.getEditionList().size() == 0) {
                    UpdateProduct updateProduct = new UpdateProduct(game.getPostId(), creatHyperlink(game.getName(),
                            game.getPlatiURL()), null, null, null, null,
                            null, null, null, null);
                    tvUnavailableItem.getItems().add(updateProduct);

                // اگر بازی کع در لیست قیمت ها نیست ادیشن داشت
                } else {
                    for (Edition edition : game.getEditionList()) {

                        UpdateProduct updateProduct = new UpdateProduct(game.getPostId(), creatHyperlink(game.getName(),
                                game.getPlatiURL()), null, null, null, null,
                                null, null, edition.getName(), edition.getPostId());

                        tvUnavailableItem.getItems().add(updateProduct);
                    }

                }
            }
        }
    }

    public void cancelUpdateSitePrices(ActionEvent actionEvent) {

        spUpdateSitePrices.setVisible(false);
        tfDollarPrice.setText("");
        tfDollarPrice.setPromptText("");
        tvAvailableItem.getItems().clear();
        tvUnavailableItem.getItems().clear();

    }

    public void updateSitePrices(ActionEvent actionEvent) {
        spUpdateSitePrices.setVisible(true);
    }

    public void moveToAvailableList(ActionEvent actionEvent) {
        spAddToAvailable.setVisible(true);
    }

    public void moveToUnavailableList(ActionEvent actionEvent) {
        if (!tvAvailableItem.getSelectionModel().isEmpty()){
            tvUnavailableItem.getItems().add(tvAvailableItem.getSelectionModel().getSelectedItem());
            tvAvailableItem.getItems().remove(tvAvailableItem.getSelectionModel().getSelectedItem());
        }else {
            dialogBox(spUpdateSitePrices,"Error","Select an item first.");
        }
    }

    public void addToAvailable(ActionEvent actionEvent) {
        if (!tfItemPrice.getText().isEmpty()){
            updateProductInstance = tvUnavailableItem.getSelectionModel().getSelectedItem();
            if (tbPriceType.isSelected()){
                updateProductInstance.setPriceToman(tfItemPrice.getText());
            } else {
                updateProductInstance.setPriceDollar(tfItemPrice.getText());
                if (!tfDollarPrice.getText().isEmpty()){
                    float price;
                    if (Double.parseDouble(tfItemPrice.getText()) < 10) {
                        price = Math.round(Float.parseFloat(tfItemPrice.getText()) * 1.2 * (Float.parseFloat(tfDollarPrice.getText()) / 1000));
                        price = price * 1000;
                    } else if (Double.parseDouble(tfItemPrice.getText()) < 20) {
                        price = Math.round(Float.parseFloat(tfItemPrice.getText()) * 1.15 * (Float.parseFloat(tfDollarPrice.getText()) / 1000));
                        price = price * 1000;
                    } else if (Double.parseDouble(tfItemPrice.getText()) < 40) {
                        price = Math.round(Float.parseFloat(tfItemPrice.getText()) * 1.1 * (Float.parseFloat(tfDollarPrice.getText()) / 1000));
                        price = price * 1000;
                    } else {
                        price = Math.round(Float.parseFloat(tfItemPrice.getText()) * 1.07 * (Float.parseFloat(tfDollarPrice.getText()) / 1000));
                        price = price * 1000;
                    }
                    updateProductInstance.setPriceToman(Float.toString(price).substring(0,Float.toString(price).indexOf(".")));
                }
            }

            tvUnavailableItem.getItems().remove(updateProductInstance);
            tvAvailableItem.getItems().add(updateProductInstance);
            tfItemPrice.setPromptText("");
            spAddToAvailable.setVisible(false);
            tfItemPrice.setText("");

        } else {
            tfItemPrice.setPromptText("Enter item prices");
        }
    }

    public void cancelAddToAvailable(ActionEvent actionEvent) {
        tfItemPrice.setPromptText("");
        spAddToAvailable.setVisible(false);
        tfItemPrice.setText("");
    }

    public void FinalUpdateSitePrice(ActionEvent actionEvent) {
        new Thread(()->{
            Platform.runLater(()-> tfDollarPrice.setPromptText((tfDollarPrice.getText().isEmpty()) ? "Enter Dollar Price" : ""));


            if (!tfDollarPrice.getText().isEmpty()) {

                Platform.runLater(()->{
                    btnUpdateSitePrice.setDisable(true);
                    btnCancelUpdateSitePrice.setDisable(true);
                });

                List <WpPostMeta> myChangeList = new ArrayList<>();
                List<WpPostMeta> updateList = new ArrayList<>();
                List<UpdateProduct> updateProductList = tvAvailableItem.getItems();
                System.out.println("defaultPricesList\n"+updateProductList);


                for (UpdateProduct dP : updateProductList) {
                    List<WpPostMeta> wpPostMetas = dbOperations.getWpPostMetaByPostIDAndMetaKey(dP.getGameID(), "_price","_regular_price");
                    if (!String.valueOf(dP.getEditionID()).isEmpty()) {
                        System.out.println("Have Edition!");
                        wpPostMetas.addAll(dbOperations.getWpPostMetaByPostIDAndMetaKey(dP.getEditionID(), "_price","_regular_price"));


                        for (WpPostMeta postMeta : wpPostMetas) {

                            if (postMeta.getPostId() == dP.getEditionID()) {

                                myChangeList.add(postMeta);

                                // برای گرفتن _price و _regular_price ادیشن در لیست myChangeList
                                if (myChangeList.size()==2){
                                    //گرفتن _price بازی که برای این ادیشن تعریف شده
                                    WpPostMeta pM;
                                    pM = wpPostMetas.stream().filter(wpPostMeta -> wpPostMeta.getPostId()==dP.getGameID() && wpPostMeta.getMetaValue().equals(postMeta.getMetaValue())).findFirst().get();
                                    myChangeList.add(pM);
                                    System.out.println(dP.getPriceToman());
                                    for(WpPostMeta wp : myChangeList){
                                        wp.setMetaValue(dP.getPriceToman());
                                    }
                                    updateList.addAll(myChangeList);
                                    myChangeList.clear();
                                }
                            }
                        }

                    } else {
                        for (WpPostMeta postMeta : wpPostMetas) {
                            if (postMeta.getPostId()== dP.getGameID()) {
                                myChangeList.add(postMeta);

                                // برای گرفتن _price و _regular_price بازی در لیست myChangeList
                                if (myChangeList.size()==2){

                                    System.out.println(dP.getPriceToman());
                                    for(WpPostMeta wp : myChangeList){
                                        wp.setMetaValue(dP.getPriceToman());
                                    }
                                    updateList.addAll(myChangeList);
                                    myChangeList.clear();

                                }
                            }
                        }
                    }
                }

                dbOperations.update(updateList);

                Platform.runLater(()->{
                    spUpdateSitePrices.setVisible(false);
                    btnUpdateSitePrice.setDisable(false);
                    btnCancelUpdateSitePrice.setDisable(false);
                    tfDollarPrice.setText("");
                });

            }
        }).start();
    }
}

