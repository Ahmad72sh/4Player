package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {


    public Main() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        final Font IRANSans = Font.loadFont(getClass().getResourceAsStream("resources/IRANSans.ttf"), 14);
        final Font IRANSansBlack = Font.loadFont(getClass().getResourceAsStream("resources/IRANSans_Black.ttf"), 14);
        final Font IRANSansBold = Font.loadFont(getClass().getResourceAsStream("resources/IRANSans_Bold.ttf"), 14);
        final Font IRANSansMedium = Font.loadFont(getClass().getResourceAsStream("resources/IRANSans_Medium.ttf"), 14);
        final Font IRANSansFaNum = Font.loadFont(getClass().getResourceAsStream("resources/IRANSans(FaNum).ttf"), 14);
        final Font IRANSansFaNumBlack = Font.loadFont(getClass().getResourceAsStream("resources/IRANSans(FaNum)_Black.ttf"), 14);
        final Font IRANSansFaNumMedium = Font.loadFont(getClass().getResourceAsStream("resources/IRANSans(FaNum)_Medium.ttf"), 14);
        final Font IRANSansMonoSpacedNum = Font.loadFont(getClass().getResourceAsStream("resources/IRANSansMonoSpacedNum.ttf"), 14);

        Parent root = FXMLLoader.load(getClass().getResource("/main/HomeScreen.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

//        this.stage.initStyle(StageStyle.UNDECORATED);
//        ResizeHelper.addResizeListener(stage);

        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setTitle("4Player Price Updater");
        stage.getIcons().add(new Image("main/resources/4P-Logo-Square.png"));

        stage.show();

    }

}