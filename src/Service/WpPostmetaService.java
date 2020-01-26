package Service;

import Entity.WpPostMeta;

import java.util.List;

public interface WpPostmetaService {

    WpPostMeta Add(WpPostMeta T) throws Exception;

    WpPostMeta Update(WpPostMeta T);

    WpPostMeta Update(List<WpPostMeta> T);

    void Remove(int id);

    WpPostMeta getById(int id);

    List<WpPostMeta> getAll();

    List<WpPostMeta> getByMetaKey(String metaKey);

    List<WpPostMeta> getByPostIDAndMetaKey(Long post_id , String metaKey);

    List<WpPostMeta> getByPostIDAndMetaKey(Long post_id , String metaKey1, String metaKey2);

    List<WpPostMeta> getByPostIDAndMetaKey(Long post_id , String metaKey1, String metaKey2, String metaKey3);

}
