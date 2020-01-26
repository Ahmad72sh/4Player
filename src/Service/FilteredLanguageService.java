package Service;

import Entity.FilteredLanguage;

import java.util.List;

public interface FilteredLanguageService {

    FilteredLanguage Add(FilteredLanguage T) throws Exception;

    FilteredLanguage Update(FilteredLanguage T);

    void Remove(int id);

    FilteredLanguage getById(int id);

    List<FilteredLanguage> getAll();

    FilteredLanguage getByName(String name);

}
