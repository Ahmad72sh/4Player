package Service;

import Dao.WpPostMetaDao;
import Entity.WpPostMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WpPostmetaServiceImpl implements WpPostmetaService {

    @Autowired
    WpPostMetaDao wpPostmetaDao;

    @Override
    public WpPostMeta Add(WpPostMeta T) throws Exception {
        try {
            wpPostmetaDao.Insert(T);
            return T;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error, Do not Inserted!!");
        }
    }

    @Override
    public WpPostMeta Update(WpPostMeta T) {

        wpPostmetaDao.Update(T);
        return T;

    }

    @Override
    public WpPostMeta Update(List<WpPostMeta> T) {
        return wpPostmetaDao.Update(T);
    }


    @Override
    public void Remove(int id) {

        wpPostmetaDao.Delete(id);

    }

    @Override
    public WpPostMeta getById(int id) {

        return wpPostmetaDao.getEntity(id);

    }

    @Override
    public List<WpPostMeta> getAll() {

        return wpPostmetaDao.selectAll();

    }

    @Override
    public List<WpPostMeta> getByMetaKey(String metaKey) {
        return wpPostmetaDao.getByMetaKey(metaKey);
    }

    @Override
    public List<WpPostMeta> getByPostIDAndMetaKey(Long post_id, String metaKey) {
        return wpPostmetaDao.getByPostIDAndMetaKey(post_id, metaKey);
    }

    @Override
    public List<WpPostMeta> getByPostIDAndMetaKey(Long post_id, String metaKey1, String metaKey2) {
        return wpPostmetaDao.getByPostIDAndMetaKey(post_id, metaKey1, metaKey2);
    }

    @Override
    public List<WpPostMeta> getByPostIDAndMetaKey(Long post_id, String metaKey1, String metaKey2, String metaKey3) {
        return wpPostmetaDao.getByPostIDAndMetaKey(post_id, metaKey1, metaKey2, metaKey3);
    }
}
