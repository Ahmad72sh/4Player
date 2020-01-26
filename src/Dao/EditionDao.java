package Dao;

import Entity.Edition;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class EditionDao extends GenericDaoImpl<Edition, Integer> {

    @PersistenceContext
    private EntityManager em;

    public List<Edition> getByGmaeID(Integer GameId)
    {
        String query = "SELECT t from Edition t where t.game.id = ?1";
        return (List<Edition>) em.createQuery(query).setParameter(1,GameId).getResultList();
    }

    public Edition getByNameAndGameID (String name,Integer GameId){
        String query = "SELECT t from Edition t where t.game.id = ?2 And t.Name = ?1";
        return (Edition) em.createQuery(query).setParameter(1, name).setParameter(2,GameId).getResultList().get(0);
    }
//
//    public List<ShoppingList> getShoppingListBySellerId(Integer sellerId){
//        String query = "SELECT t from ShoppingList t where t.seller.id = ?1";
//        return (List<ShoppingList>) em.createQuery(query).setParameter(1, sellerId).getResultList();
//    }

}
