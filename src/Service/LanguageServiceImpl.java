package Service;

import Dao.LanguageDao;
import Entity.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    LanguageDao languageDao;

    @Override
    public Language Add(Language T) throws Exception {
        try {
            languageDao.Insert(T);
            return T;
        } catch (Exception e) {
            throw new Exception("Error, Do not Inserted!!");
        }
    }

    @Override
    public Language Update(Language T) {
        languageDao.Update(T);
        return T;
    }

    @Override
    public void Remove(int id) {
        languageDao.Delete(id);
    }

    @Override
    public Language getById(int id) {

        return languageDao.getEntity(id);
    }

    @Override
    public List<Language> getAll() {
        return languageDao.selectAll();
    }

    @Override
    public Language getByName(String name) {
        return languageDao.getEntityByName(name);
    }
}
