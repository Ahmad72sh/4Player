package Service;

import Dao.SaleTypeDao;
import Entity.SaleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SaleTypeServiceImpl implements SaleTypeService {

    @Autowired
    SaleTypeDao saleTypeDao;

    @Override
    public SaleType Add(SaleType T) throws Exception {
        try {
            saleTypeDao.Insert(T);
            return T;
        } catch (Exception e) {
            throw new Exception("Error, Do not Inserted!!");
        }
    }

    @Override
    public SaleType Update(SaleType T) {
        saleTypeDao.Update(T);
        return T;
    }

    @Override
    public void Remove(int id) {
        saleTypeDao.Delete(id);
    }

    @Override
    public SaleType getCustomerById(int id) {

        return saleTypeDao.getEntity(id);
    }

    @Override
    public List<SaleType> getAll() {
        return saleTypeDao.selectAll();
    }

    @Override
    public void deleteByName(String Name) {
        saleTypeDao.deleteByName(Name);
    }
}
