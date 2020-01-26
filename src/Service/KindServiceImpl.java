package Service;

import Dao.KindDao;
import Entity.Kind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class KindServiceImpl implements KindService {

    @Autowired
    KindDao kindDao;

    @Override
    public Kind Add(Kind T) throws Exception {
        try {
            kindDao.Insert(T);
            return T;
        } catch (Exception e) {
            throw new Exception("Error, Do not Inserted!!");
        }
    }

    @Override
    public Kind Update(Kind T) {
        kindDao.Update(T);
        return T;
    }

    @Override
    public void Remove(int id) {
        kindDao.Delete(id);
    }

    @Override
    public Kind getById(int id) {

        return kindDao.getEntity(id);
    }

    @Override
    public List<Kind> getAll() {
        return kindDao.selectAll();
    }

    @Override
    public Kind getByName(String name) {
        return kindDao.getEntityByName(name);
    }
}
