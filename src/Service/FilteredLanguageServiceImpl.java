package Service;

import Dao.FilteredLanguageDao;
import Entity.FilteredLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FilteredLanguageServiceImpl implements FilteredLanguageService {

    @Autowired
    FilteredLanguageDao filteredLanguageDao;

    @Override
    public FilteredLanguage Add(FilteredLanguage T) throws Exception {
        try {
            filteredLanguageDao.Insert(T);
            return T;
        } catch (Exception e) {
            throw new Exception("Error, Do not Inserted!!");
        }
    }

    @Override
    public FilteredLanguage Update(FilteredLanguage T) {
        filteredLanguageDao.Update(T);
        return T;
    }

    @Override
    public void Remove(int id) {
        filteredLanguageDao.Delete(id);
    }

    @Override
    public FilteredLanguage getById(int id) {

        return filteredLanguageDao.getEntity(id);
    }

    @Override
    public List<FilteredLanguage> getAll() {
        return filteredLanguageDao.selectAll();
    }

    @Override
    public FilteredLanguage getByName(String name) {
        return filteredLanguageDao.getEntityByName(name);
    }
}
