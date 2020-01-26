package Controller;

import org.hibernate.Session;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.PersistenceContext;

public class ConsoleTest {
    private static ServiceRegistry serviceRegistry;
    private static Session session;
    @PersistenceContext
//    public static EntityManager em;

    public static void main(String[] args) throws Exception {


        DBOperations DBOperations = new DBOperations();



//        WpWcProductMetaLookup metaLookup = new WpWcProductMetaLookup();
//        metaLookup.setMaxPrice(new BigDecimal(99999));
//        metaLookup.setSku("TEST");
//        metaLookup.setProductId(new Long(2));
//
//
//        DBOperations.add(metaLookup);

//        List<TableList> tableLists = new ArrayList<>();
//        Element prices;
//        String tableName;
//        Document doc;
//        try {
//            doc = Jsoup.connect("https://www.plati.market/games/tom-clancy-s-the-division-2/773").get();
//
//            Elements title = doc.select("div.games_products");
//
//            for (Element tie : title) {
//                for (int i = 0; i < tie.getElementsByClass("games-header").size(); i++) {
//                    tableName = tie.getElementsByClass("games-header").get(i).text();
//                    prices = doc.select("tbody").get(i);
//                    tableLists.add(new TableList(tableName, prices));
//                }
//            }
//
//            for (TableList tableList : tableLists) {
//
//                System.out.println(tableList.Name.get() + " :");
//
//                ObservableList<GamesList> priceLists = FXCollections.observableArrayList();
//
//                double priceNum;
//                Element tie = tableList.Table;
//                for (int i = 0; i < tie.getElementsByClass("product-merchant").size(); i++) {
//                    String productTitle = tie.getElementsByClass("product-title").get(i).text();
//                    String sellerName = tie.getElementsByClass("product-merchant").get(i).text();
//                    String price = tie.getElementsByClass("product-price-inner").get(i).text().substring(0, tie.getElementsByClass("product-price-inner").get(i).text().indexOf("$") - 1);
//                    String soldCont = tie.getElementsByClass("product-sold").get(i).text();
//
//                    priceNum = (price.contains(","))? Double.parseDouble(price.substring(0, price.indexOf(",")) + "." + price.substring(price.indexOf(",") + 1)):Double.parseDouble(price);
//
////                    priceLists.add(new GamesList(sellerName, soldCont, price.replace(",",".")));
//                    System.out.println("https://www.plati.market/"+tie.getElementsByClass("product-title").get(i).getAllElements().get(1).getAllElements().get(1).attributes().get("href"));
//
//                    System.out.println(sellerName + "\t\t" + soldCont + "\t\t" + price.replace(",","."));
//                }
//
//                System.out.println("END!!!!!!!!!!\n\n");
//                for (GamesList p : priceLists) {
////                    p.Price.setValue(p.Price.get().replace(",", "."));
//                }
//
////                priceLists.sort(Comparator.comparing(GamesList::getPrice));
//
////                System.out.println(priceLists.toString());
//
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


}


//    }


//}
