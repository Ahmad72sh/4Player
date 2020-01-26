package Service;

import Entity.Region;

import java.util.List;

public interface RegionService {

    Region Add(Region T) throws Exception;

    Region Update(Region T);

    void Remove(int id);

    Region getById(int id);

    List<Region> getAll();

    Region getByName(String name);

}
