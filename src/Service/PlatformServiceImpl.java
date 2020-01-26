package Service;

import Dao.PlatformDao;
import Entity.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlatformServiceImpl implements PlatformService {

    @Autowired
    PlatformDao platformDao;

    @Override
    public Platform Add(Platform T) throws Exception {
        try {
            platformDao.Insert(T);
            return T;
        } catch (Exception e) {
            throw new Exception("Error, Do not Inserted!!");
        }
    }

    @Override
    public Platform Update(Platform T) {
        platformDao.Update(T);
        return T;
    }

    @Override
    public void Remove(int id) {
        platformDao.Delete(id);
    }

    @Override
    public Platform getById(int id) {

        return platformDao.getEntity(id);
    }

    @Override
    public List<Platform> getAll() {
        return platformDao.selectAll();
    }

    @Override
    public Platform getByName(String name) {
        return platformDao.getEntityByName(name);
    }
}
