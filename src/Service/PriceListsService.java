package Service;

import Entity.PriceLists;

import java.util.List;

public interface PriceListsService {

    PriceLists Add(PriceLists T) throws Exception;

    PriceLists Add(List<PriceLists> T) throws Exception;

    PriceLists Update(PriceLists T);

    void Remove(int id);

//    List<PriceLists> getFavSlabByFavList(Integer id);

    List<PriceLists> getAll();

    PriceLists getByName(String name);

    List<String> getAllPriceListsName();

    PriceLists getPriceListsByName(String unitName);

    void removeAll ();
}
