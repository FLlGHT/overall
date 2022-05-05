package flight.overall.entity;

import javax.persistence.*;

@Entity
public class UserLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserData userData;

    private String link;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getShortLink() {
        if (link.startsWith("http"))
            return link.replace("https://","").replace("http://","").replace("www.","");
        return link;
    }

    public String getFullLink() {
        if (link.startsWith("http"))
            return link;
        else return "https://" + link;
    }
}
