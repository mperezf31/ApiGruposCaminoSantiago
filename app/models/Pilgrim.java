package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.ebean.Finder;
import play.data.validation.Constraints.Required;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Pilgrim extends ModelBase {

    public static final Finder<Long, Pilgrim> find = new Finder<>(Pilgrim.class);

    @Required
    private String name;

    @Required
    private String email;

    @Required
    private String password;

    private String photo;

    @ManyToMany(mappedBy = "pilgrims")
    @JsonBackReference
    private List<GroupCamino> groupsCamino;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pilgrim")
    private List<Post> posts;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public String getPhoto() {
        return photo;
    }

    @JsonIgnore
    @Override
    public Timestamp getWhenCreated() {
        return super.getWhenCreated();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setGroupsCamino(List<GroupCamino> groupsCamino) {
        this.groupsCamino = groupsCamino;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
