package models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.ebean.Finder;

import javax.persistence.*;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class GroupCamino extends ModelBase {

    private static final Finder<Long, GroupCamino> find = new Finder<>(GroupCamino.class);

    private String title;
    private String description;
    private String departurePlace;
    private Timestamp departureDate;
    private Timestamp arrivalDate;
    private String photo;
    private Pilgrim founder;

    @JsonManagedReference
    @ManyToOne
    public Modality modality;

    @Valid
    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Pilgrim> pilgrims;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupCamino")
    private List<Post> posts;

    public GroupCamino() {
    }

    public static List<GroupCamino> all() {
        return find.query().findList();
    }

}
