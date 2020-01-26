package Service;

import Entity.Language;

import java.util.List;

public interface LanguageService {

    Language Add(Language T) throws Exception;

    Language Update(Language T);

    void Remove(int id);

    Language getById(int id);

    List<Language> getAll();

    Language getByName(String name);

}
