package Service;

import Entity.Games;

import java.util.List;

public interface GameService {

    Games Add(Games T) throws Exception;

    Games Update(Games T);

    void Remove(int id);

    Games getById(int id);

    List<Games> getAll();

    Games getByName (String name);

}
