package Service;

import Dao.RegionDao;
import Entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegionServiceImpl implements RegionService {

    @Autowired
    RegionDao regionDao;

    @Override
    public Region Add(Region T) throws Exception {
        try {
            regionDao.Insert(T);
            return T;
        } catch (Exception e) {
            throw new Exception("Error, Do not Inserted!!");
        }
    }

    @Override
    public Region Update(Region T) {
        regionDao.Update(T);
        return T;
    }

    @Override
    public void Remove(int id) {
        regionDao.Delete(id);
    }

    @Override
    public Region getById(int id) {

        return regionDao.getEntity(id);
    }

    @Override
    public List<Region> getAll() {
        return regionDao.selectAll();
    }

    @Override
    public Region getByName(String name) {
        return regionDao.getEntityByName(name);
    }
}
