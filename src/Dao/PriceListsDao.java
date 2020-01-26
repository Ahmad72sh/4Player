package Dao;

import Entity.PriceLists;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SuppressWarnings("JpaQlInspection")
@Repository
public class PriceListsDao extends GenericDaoImpl<PriceLists, Integer> {


        @PersistenceContext
        private EntityManager em;


        public PriceLists getPriceListsByName(String name)
        {
            String query = "SELECT t from PriceLists t where t.Name like ?1";
            return (PriceLists) em.createQuery(query).setParameter(1,name).getResultList().get(0);
        }

        public void deleteAll (){
            String query = "DELETE from PriceLists";
            em.createQuery(query).executeUpdate();
        }

//        public List<PriceLists> getFavSlabByFavList (Integer favListId){
//            String query = "SELECT t from FavSlab t where t.favList.id = ?1";
//            return (List<PriceLists>)em.createQuery(query).setParameter(1,favListId).getResultList();
//        }

    }
