package Service;

import Dao.GameDao;
import Entity.Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    @Autowired
    GameDao gameDao;

    @Override
    public Games Add(Games T) throws Exception {
        try {
            gameDao.Insert(T);
            return T;
        } catch (Exception e) {
            throw new Exception("Error, Do not Inserted!!");
        }
    }

    @Override
    public Games Update(Games T) {
        gameDao.Update(T);
        return T;
    }

    @Override
    public void Remove(int id) {
        gameDao.Delete(id);
    }

    @Override
    public Games getById(int id) {

        return gameDao.getEntity(id);
    }

    @Override
    public List<Games> getAll() {
        return gameDao.selectAll();
    }

    @Override
    public Games getByName(String name) {
        return gameDao.getEntityByName(name);
    }
}
