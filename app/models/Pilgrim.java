package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.ebean.Finder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Pilgrim extends ModelBase {

    public static final Finder<Long, Pilgrim> find = new Finder<>(Pilgrim.class);

    private String name;
    private String email;
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

    @JsonIgnore
    @Override
    public Timestamp getWhenCreated() {
        return super.getWhenCreated();
    }

}
