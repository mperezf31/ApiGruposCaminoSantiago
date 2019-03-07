package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.Constraints.Required;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
public class Post extends ModelBase {

    private String content;

    @JsonBackReference
    @ManyToOne
    public GroupCamino groupCamino;

    @JsonBackReference
    @ManyToOne
    public Pilgrim pilgrim;

    public Post() {

    }

    @JsonIgnore
    @Override
    public Timestamp getWhenCreated() {
        return super.getWhenCreated();
    }

}
