package Service;

import Dao.EditionDao;
import Entity.Edition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EditionServiceImpl implements EditionService {

    @Autowired
    EditionDao editionDao;

    @Override
    public Edition Add(Edition T) throws Exception {
        try {
            editionDao.Insert(T);
            return T;
        } catch (Exception e) {
            throw new Exception("Error, Do not Inserted!!");
        }
    }

    @Override
    public Edition Update(Edition T) {
        editionDao.Update(T);
        return T;
    }

    @Override
    public void Remove(int id) {
        editionDao.Delete(id);
    }

    @Override
    public Edition getById(int id) {

        return editionDao.getEntity(id);
    }

    @Override
    public List<Edition> getAll() {
        return editionDao.selectAll();
    }

    @Override
    public Edition getByName(String name) {
        return editionDao.getEntityByName(name);
    }

    @Override
    public List<Edition> getByGameId(Integer GameID) {
        return editionDao.getByGmaeID(GameID);
    }

    @Override
    public Edition getByNameAndGameID(String name, Integer GameID) {
        return editionDao.getByNameAndGameID(name,GameID);
    }
}
