package Service;

import Entity.SaleType;

import java.util.List;

public interface SaleTypeService {

    SaleType Add(SaleType T) throws Exception;

    SaleType Update(SaleType T);

    void Remove(int id);

    SaleType getCustomerById(int id);

    List<SaleType> getAll();

    void deleteByName (String Name);

}
