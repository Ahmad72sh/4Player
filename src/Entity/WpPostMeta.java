package Entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "wp_postmeta")

public class WpPostMeta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "meta_id")
    private Long metaId;
    @Basic(optional = false)
    @Column(name = "post_id")
    private long postId;
    @Column(name = "meta_key")
    private String metaKey;
    @Lob
    @Column(name = "meta_value")
    private String metaValue;

    public WpPostMeta() {
    }

    public WpPostMeta(Long metaId) {
        this.metaId = metaId;
    }

    public WpPostMeta(Long metaId, long postId) {
        this.metaId = metaId;
        this.postId = postId;
    }

    public Long getMetaId() {
        return metaId;
    }

    public void setMetaId(Long metaId) {
        this.metaId = metaId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getMetaKey() {
        return metaKey;
    }

    public void setMetaKey(String metaKey) {
        this.metaKey = metaKey;
    }

    public String getMetaValue() {
        return metaValue;
    }

    public void setMetaValue(String metaValue) {
        this.metaValue = metaValue;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (metaId != null ? metaId.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof WpPostMeta)) {
//            return false;
//        }
//        WpPostMeta other = (WpPostMeta) object;
//        if ((this.metaId == null && other.metaId != null) || (this.metaId != null && !this.metaId.equals(other.metaId))) {
//            return false;
//        }
//        return true;
//    }


    @Override
    public String toString() {
        return "WpPostMeta{" +
                "metaId=" + metaId +
                ", postId=" + postId +
                ", metaKey='" + metaKey + '\'' +
                ", metaValue='" + metaValue + '\'' +
                '}';
    }
}