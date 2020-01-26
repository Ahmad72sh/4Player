package Dao;

import Entity.DefaultPrices;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@SuppressWarnings("ALL")
@Repository
public class DefaultPricesDao extends GenericDaoImpl<DefaultPrices, Integer> {

    @PersistenceContext
    EntityManager em;


//    public List<DefaultPrices> getAllDefaultPrices(){
//        String query = "Select t from DefaultPrices t";
//        return (List<DefaultPrices>)em.createQuery(query).getResultList();
//    }

    public DefaultPrices getByName(String name){
        String query = "Select t from DefaultPrices t WHERE  t.Name like ?1";
        return (DefaultPrices) em.createQuery(query).setParameter(1,name).getResultList().get(0);
    }

    public void deleteAll (){
        String query = "DELETE from DefaultPrices";
        em.createQuery(query).executeUpdate();
    }



}
