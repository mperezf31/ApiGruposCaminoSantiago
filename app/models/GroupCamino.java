package models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.ebean.Finder;
import play.data.validation.Constraints.Required;

import javax.persistence.*;
import java.util.List;

@Entity
public class GroupCamino extends ModelBase {

    private static final Finder<Long, GroupCamino> find = new Finder<>(GroupCamino.class);

    @Required
    private String title;

    @Required
    private String description;

    @Required
    private String departurePlace;

    @Required
    private Long departureDate;

    private Long arrivalDate;

    private String photo;

    @JsonManagedReference
    @ManyToOne
    private Pilgrim founder;

    @JsonManagedReference
    @ManyToOne
    private Modality modality;

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Pilgrim> members;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupCamino")
    private List<Post> posts;

    public GroupCamino() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public Long getDepartureDate() {
        return departureDate;
    }

    public Long getArrivalDate() {
        return arrivalDate;
    }

    public String getPhoto() {
        return photo;
    }

    public Pilgrim getFounder() {
        return founder;
    }

    public Modality getModality() {
        return modality;
    }

    public List<Pilgrim> getMembers() {
        return members;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }

    public void setDepartureDate(Long departureDate) {
        this.departureDate = departureDate;
    }

    public void setArrivalDate(Long arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setFounder(Pilgrim founder) {
        this.founder = founder;
    }

    public void setModality(Modality modality) {
        this.modality = modality;
    }

    public void setMembers(List<Pilgrim> members) {
        this.members = members;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public static List<GroupCamino> all() {
        return find.query().findList();
    }

    public static GroupCamino findById(Long id) {
        return find.query().where().eq("id", id).findOne();
    }

    public void addMember(Pilgrim pilgrim) {
        this.members.add(pilgrim);
        pilgrim.getGroupsCamino().add(this);
    }
}
