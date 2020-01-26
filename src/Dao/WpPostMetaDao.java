package Dao;

import Entity.WpPostMeta;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class WpPostMetaDao extends GenericDaoImpl<WpPostMeta, Integer> {

    @PersistenceContext
    private EntityManager em;

    public List<WpPostMeta> getByMetaKey (String metaKey)
    {
        String query = "SELECT t from WpPostMeta t where t.metakey = ?1";
        return (List<WpPostMeta>) em.createQuery(query).setParameter(1,metaKey).getResultList();
    }

    public List<WpPostMeta> getByPostIDAndMetaKey (Long post_id , String metaKey)
    {
        String query = "SELECT t from WpPostMeta t where t.postId = ?1 And t.metaKey = ?2";
        return (List<WpPostMeta>) em.createQuery(query).setParameter(1,post_id).setParameter(2,metaKey).getResultList();
    }

    public List<WpPostMeta> getByPostIDAndMetaKey (Long post_id , String metaKey1 , String metaKey2)
    {
        String query = "SELECT t from WpPostMeta t where t.postId = ?1 And ( t.metaKey = ?2 Or t.metaKey = ?3 )";
        return (List<WpPostMeta>) em.createQuery(query).setParameter(1,post_id).setParameter(2,metaKey1).setParameter(3,metaKey2).getResultList();
    }

    public List<WpPostMeta> getByPostIDAndMetaKey (Long post_id , String metaKey1 , String metaKey2 , String metaKey3)
    {
        String query = "SELECT t from WpPostMeta t where t.postId = ?1 And ( t.metaKey = ?2 Or t.metaKey = ?3 Or  t.metaKey = ?4)";
        return (List<WpPostMeta>) em.createQuery(query).setParameter(1,post_id).setParameter(2,metaKey1).setParameter(3,metaKey2).setParameter(4,metaKey3).getResultList();
    }

}
