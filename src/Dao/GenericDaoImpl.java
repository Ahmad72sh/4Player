package Dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


@Repository
public abstract class GenericDaoImpl <Entity, PK extends Serializable> implements GenericDao<Entity,PK>
{

    private Class<Entity> classType;
    private String className; //// for get name of entity class for HQL and ...

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public GenericDaoImpl()
    {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        classType = (Class<Entity>) pt.getActualTypeArguments()[0];
        String templateName = classType.getName(); //Class name generate here include entity.classname it dos'nt useful for HQl
        this.className= templateName.substring(templateName.lastIndexOf('.')+1);
    }


    @Override
    public Entity Insert(Entity T) {

        em.persist(T);
        em.flush();
        return T;
    }

    // 100 = BATCH_SIZE
    @Override
    public Entity Insert(List<Entity> T) {

        for (int i = 0; i <T.size() ; i++) {
            if (i>0 && i%100 == 0) {
                em.flush();
                em.clear();
            }
            em.persist(T.get(i));
            if (i == T.size()-1){
                em.flush();
                em.clear();
            }
        }

        return T.get(T.size()-1);
    }

    @Override
    public Entity Update(Entity T) {

        em.merge(T);
        return T;
    }

    // 100 = BATCH_SIZE
    @Override
    public Entity Update(List<Entity> T) {

        for (int i = 0; i <T.size() ; i++) {
            if (i>0 && i%100 == 0) {
                em.flush();
                em.clear();
            }
            em.merge(T.get(i));

            if (i == T.size()-1){
                em.flush();
                em.clear();
            }
        }

        return T.get(0);
    }

    @Override
    public void Delete(PK id) {

        em.remove(em.find(classType, id));

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Entity> selectAll() {
        String query ="Select t from "+className+" t";
        return (List<Entity>) em.createQuery(query).getResultList();
    }

    @Override
    public Entity getEntity(PK id) {

        return em.find(classType, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Entity getEntityByName (String name)
    {
        String query = "Select t From "+this.className+" t where (t.Name)  like ?1";
        return (Entity)em.createQuery(query).setParameter(1,name).getResultList().get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Entity> getEntityByFamily (String family)
    {
        String query = "Select t From "+this.className+" t where LOWER(t.Family)  like LOWER(concat('%',?1,'%'))";
        return em.createQuery(query).setParameter(1,family).getResultList();
    }

    public List<String> getAllEntityNameList ()
    {
        String query = "SELECT t.Name From "+this.className+" t";
        return em.createQuery(query).getResultList();
    }
}
