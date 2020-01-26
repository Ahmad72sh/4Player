package Dao;

import Entity.Language;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class LanguageDao extends GenericDaoImpl<Language, Integer> {

    @PersistenceContext
    private EntityManager em;

//    public List<ShoppingList> getByGmaeID(Integer productId)
//    {
//        String query = "SELECT t from ShoppingList t where t.product.id = ?1";
//        return (List<ShoppingList>) em.createQuery(query).setParameter(1,productId).getResultList();
//    }
//
//    public List<ShoppingList> getShoppingListByCustomerId(Integer customerId){
//        String query = "SELECT t from ShoppingList t where t.customer.id = ?1";
//        return (List<ShoppingList>) em.createQuery(query).setParameter(1, customerId).getResultList();
//    }
//
//    public List<ShoppingList> getShoppingListBySellerId(Integer sellerId){
//        String query = "SELECT t from ShoppingList t where t.seller.id = ?1";
//        return (List<ShoppingList>) em.createQuery(query).setParameter(1, sellerId).getResultList();
//    }

}
