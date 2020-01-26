package Service;

import Entity.DefaultPrices;

import java.util.List;

public interface DefaultPricesService {

    DefaultPrices Add(DefaultPrices T) throws Exception;

    DefaultPrices Add (List<DefaultPrices> T) throws Exception;

    DefaultPrices Update(DefaultPrices T);

    void Remove(int id);

    DefaultPrices getDefaultPricesById(int id);

    List<DefaultPrices> getAll();

    DefaultPrices getByName(String name);

    DefaultPrices getDefaultPricesByName(Integer storeId, String name);

    List<String> getAllDefaultPricesName();

    void removeAll ();

}
