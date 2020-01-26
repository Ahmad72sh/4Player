package Service;

import Entity.Edition;

import java.util.List;

public interface EditionService {

    Edition Add(Edition T) throws Exception;

    Edition Update(Edition T);

    void Remove(int id);

    Edition getById(int id);

    List<Edition> getAll();

    Edition getByName(String name);

    List<Edition> getByGameId (Integer GameID);

    Edition getByNameAndGameID (String name, Integer GameID);

}
