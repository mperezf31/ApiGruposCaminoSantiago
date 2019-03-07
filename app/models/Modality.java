package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Finder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Modality extends ModelBase {

    public static final Finder<Long, Modality> find = new Finder<>(Modality.class);

    private String name;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modality")
    private List<GroupCamino> groupsCamino;

    public String getName() {
        return name;
    }

    @JsonIgnore
    @Override
    public Timestamp getWhenCreated() {
        return super.getWhenCreated();
    }

}
