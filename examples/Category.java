package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Finder;
import play.data.validation.Constraints.Required;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Category extends ModelBase {

    public static final Finder<Long, Category> find = new Finder<>(Category.class);

    @Required(message = "name-is-required")
    private String name;

    @ManyToMany(mappedBy = "categories")
    @JsonBackReference
    private List<Recipe> recipes;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @JsonIgnore
    @Override
    public Timestamp getWhenCreated() {
        return super.getWhenCreated();
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public static Category findCategoryByName(String name) {
        return find.query().where().eq("name", name).findOne();
    }

}
