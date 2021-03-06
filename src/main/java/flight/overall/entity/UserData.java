package flight.overall.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Set;

/**
 * @author FLIGHT
 * @creationDate 03.05.2022
 */

@Entity
@Table(name = "user_data")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String secondName;

    private String username;

    private LocalDate birthday;

    private String about;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userData", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "userData", allowSetters = true)
    private Set<UserLink> userLinks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthDate) {
        this.birthday = birthDate;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Set<UserLink> getUserLinks() {
        return userLinks;
    }

    public void setUserLinks(Set<UserLink> userLinks) {
        this.userLinks = userLinks;
    }

    public Long getAge() {
        if (birthday != null)
            return ChronoUnit.YEARS.between(birthday, LocalDate.now());
        else return null;
    }

    public String getFormattedBirthday() {
        if (birthday == null) return "";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return birthday.format(formatter);
    }
}
