package Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Component
public class PriceLists {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String Name;
    private String PlatiItemURL;
    private String Price;
    private String Region;
    private String Language;
    private String Platform;
    private String Kind;
    private Long EditionID;
    private String EditionName;
    private boolean DefaultForSale;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPlatiItemURL() {
        return PlatiItemURL;
    }

    public void setPlatiItemURL(String platiItemURL) {
        PlatiItemURL = platiItemURL;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }

    public String getKind() {
        return Kind;
    }

    public void setKind(String kind) {
        Kind = kind;
    }

    public Long getEditionID() {
        return EditionID;
    }

    public void setEditionID(Long editionID) {
        EditionID = editionID;
    }

    public Boolean isDefaultForSale() {
        return DefaultForSale;
    }

    public void setDefaultForSale(Boolean defaultForSale) {
        DefaultForSale = defaultForSale;
    }

    public String getEditionName() {
        return EditionName;
    }

    public void setEditionName(String editionName) {
        EditionName = editionName;
    }
}
