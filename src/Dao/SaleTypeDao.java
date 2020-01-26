package Dao;

import Entity.SaleType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class SaleTypeDao extends GenericDaoImpl<SaleType, Integer> {

    @PersistenceContext
    private EntityManager em;

    public void deleteByName(String saleTypeName)
    {
        String query = "SELECT t from SaleType t where t.product.Name = ?1";
        em.remove(em.createQuery(query).setParameter(1,saleTypeName).getResultList().get(0));
    }
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
