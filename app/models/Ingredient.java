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
public class Ingredient extends ModelBase {

    private static final Finder<Long, Ingredient> find = new Finder<>(Ingredient.class);

    @Required(message = "name-is-required")
    private String name;

    @JsonBackReference
    @ManyToMany(mappedBy = "ingredients")
    private List<Recipe> recipes;

    public Ingredient(String name) {
        this.name = name;
    }

    @JsonIgnore
    @Override
    public Timestamp getWhenCreated() {
        return super.getWhenCreated();
    }

    public String getName() {
        return name;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Ingredient findById(Long id) {
        return find.query().where().eq("id", id).findOne();
    }

    public static Ingredient findIngredientByName(String name) {
        return find.query().where().eq("name", name).findOne();
    }

}
