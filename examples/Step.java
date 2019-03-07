package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.Constraints.Required;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
public class Step extends ModelBase {

    @Required(message = "description-is-required")
    private String description;

    @JsonBackReference
    @ManyToOne
    public Recipe recipe;

    public Step(String description) {
        this.description = description;
    }

    @JsonIgnore
    @Override
    public Timestamp getWhenCreated() {
        return super.getWhenCreated();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
