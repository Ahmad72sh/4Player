package Service;

import Dao.DefaultPricesDao;
import Entity.DefaultPrices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DefaultPricesServiceImpl implements DefaultPricesService {

    @Autowired
    DefaultPricesDao defaultPricesDao;


    @Override
    public DefaultPrices Add(DefaultPrices T) throws Exception {

        try {
            defaultPricesDao.Insert(T);
            return T;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error, Do not Inserted!!");
        }
    }

    @Override
    public DefaultPrices Add(List<DefaultPrices> T) throws Exception {

        try {
            defaultPricesDao.Insert(T);
            return T.get(T.size()-1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error, Do not Inserted!!");
        }
    }

    @Override
    public DefaultPrices Update(DefaultPrices T) {

        defaultPricesDao.Update(T);
        return T;
    }

    @Override
    public void Remove(int id) {

        defaultPricesDao.Delete(id);

    }

    @Override
    public DefaultPrices getDefaultPricesById(int id) {

        return defaultPricesDao.getEntity(id);

    }

    @Override
    public List<DefaultPrices> getAll() {

        return defaultPricesDao.selectAll();

    }

    @Override
    public DefaultPrices getByName(String name) {

        return defaultPricesDao.getEntityByName(name);
    }


    @Override
    public DefaultPrices getDefaultPricesByName(Integer storeId, String name) {
        return defaultPricesDao.getByName(name);
    }

    @Override
    public List<String> getAllDefaultPricesName() {
        return defaultPricesDao.getAllEntityNameList();
    }

    @Override
    public void removeAll() {
        defaultPricesDao.deleteAll();
    }


}
