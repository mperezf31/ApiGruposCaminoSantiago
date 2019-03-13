package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.ebean.Finder;
import play.data.validation.Constraints.Required;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "founder")
    private List<GroupCamino> groupsFounder;

    @ManyToMany(mappedBy = "members")
    @JsonBackReference
    private List<GroupCamino> groupsCamino;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
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
    public List<GroupCamino> getGroupsFounder() {
        return groupsFounder;
    }

    @JsonIgnore
    public List<GroupCamino> getGroupsCamino() {
        return groupsCamino;
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

    public void setGroupsFounder(List<GroupCamino> groupsFounder) {
        this.groupsFounder = groupsFounder;
    }

    public static Pilgrim findById(Long id) {
        return find.query().where().eq("id", id).findOne();
    }

    public static Pilgrim findByEmail(String email) {
        return find.query().where().eq("email", email).findOne();
    }

    public static Pilgrim login(String email, String password) {
        return find.query().where().eq("email", email).eq("password", password).findOne();
    }

}
