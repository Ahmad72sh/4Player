package Dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao <Entity, PK extends Serializable>
{
    Entity Insert(Entity T);

    Entity Insert(List<Entity> T);

    Entity Update(Entity T);

    Entity Update(List<Entity> T);

    void Delete(PK id);

    List<Entity> selectAll();

    Entity getEntity(PK id);

    Entity getEntityByName(String name);

    List<Entity> getEntityByFamily(String family);

    List<String> getAllEntityNameList();

}
