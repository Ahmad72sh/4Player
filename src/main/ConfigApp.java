package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigApp {

    public static Properties appProp = new Properties();

    public void saveProperty(String blockURL, String imageURL, String slabURL) {

        try {

            appProp.setProperty("blockURL", blockURL);
            appProp.setProperty("imageURL", imageURL);
            appProp.setProperty("slabURL", slabURL);
            appProp.store(new FileOutputStream("mapaScannerExtractorSettings.txt"), null);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveLastPath (String lastPath){
        try {
            appProp.setProperty("lastSavePath",lastPath);
            appProp.store(new FileOutputStream("mapaScannerExtractorSettings.txt"),null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBlockURL() {

        String blockURL = "";
        try {

            appProp.load(new FileInputStream("mapaScannerExtractorSettings.txt"));
            blockURL = appProp.getProperty("blockURL");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return blockURL;
    }

    public String getImageURL() {

        String imageURL = "";
        try {

            appProp.load(new FileInputStream("mapaScannerExtractorSettings.txt"));
            imageURL = appProp.getProperty("imageURL");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageURL;
    }

    public String getSlabURL() {

        String slabURL = "";
        try {

            appProp.load(new FileInputStream("mapaScannerExtractorSettings.txt"));
            slabURL = appProp.getProperty("slabURL");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return slabURL;
    }

    public String getSavePath() {

        String slabURL = "";
        try {

            appProp.load(new FileInputStream("mapaScannerExtractorSettings.txt"));
            slabURL = appProp.getProperty("lastSavePath");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return slabURL;
    }

}