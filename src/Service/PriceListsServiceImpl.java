package Service;

import Dao.PriceListsDao;
import Entity.PriceLists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PriceListsServiceImpl implements PriceListsService {

    @Autowired
    private PriceListsDao priceListsDao;

    @Override
    public PriceLists Add(PriceLists T) {
        priceListsDao.Insert(T);
        return T;
    }

    @Override
    public PriceLists Add(List<PriceLists> T) throws Exception {

        try {
            priceListsDao.Insert(T);
            return T.get(T.size()-1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error, Do not Inserted!!");
        }
    }

    @Override
    public PriceLists Update(PriceLists T) {
        priceListsDao.Update(T);
        return T;
    }

    @Override
    public void Remove(int id) {
        priceListsDao.Delete(id);
    }

//    @Override
//    public List<PriceLists> getFavSlabByFavList(Integer id) {
//        return priceListsDao.getFavSlabByFavList(id);
//    }

    @Override
    public List<PriceLists> getAll() {
        return priceListsDao.selectAll();
    }

    @Override
    public PriceLists getByName(String name) {
        return priceListsDao.getEntityByName(name);
    }

    @Override
    public List<String> getAllPriceListsName() {
        return priceListsDao.getAllEntityNameList();
    }

    @Override
    public PriceLists getPriceListsByName(String name) {
        return priceListsDao.getPriceListsByName(name);
    }

    @Override
    public void removeAll() {
        priceListsDao.deleteAll();
    }
}
