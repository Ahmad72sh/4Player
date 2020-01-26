package Service;

import Entity.Kind;

import java.util.List;

public interface KindService {

    Kind Add(Kind T) throws Exception;

    Kind Update(Kind T);

    void Remove(int id);

    Kind getById(int id);

    List<Kind> getAll();

    Kind getByName(String name);

}
