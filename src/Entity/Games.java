package Entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Component
public class Games {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 20)
    private Long PostId;
    private String Name;
    private String PlatiURL;
    private String SteamDBURL;
    @OneToMany(mappedBy = "game" ,cascade=CascadeType.REMOVE)
    private List<Edition> editionList = new ArrayList();
    private Boolean isActive;



    public Integer getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPlatiURL() {
        return PlatiURL;
    }

    public void setPlatiURL(String platiURL) {
        PlatiURL = platiURL;
    }

    public String getSteamDBURL() {
        return SteamDBURL;
    }

    public void setSteamDBURL(String steamDBURL) {
        SteamDBURL = steamDBURL;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Long getPostId() {
        return PostId;
    }

    public void setPostId(Long postId) {
        PostId = postId;
    }

    public List<Edition> getEditionList() {
        return editionList;
    }

    public void setEditionList(List<Edition> editionList) {
        this.editionList = editionList;
    }
}
