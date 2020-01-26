package Service;

import Entity.Platform;

import java.util.List;

public interface PlatformService {

    Platform Add(Platform T) throws Exception;

    Platform Update(Platform T);

    void Remove(int id);

    Platform getById(int id);

    List<Platform> getAll();

    Platform getByName(String name);

}
