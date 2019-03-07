package models;

import io.ebean.Finder;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Recipe extends ModelBase {

    private static final Finder<Long, Recipe> find = new Finder<>(Recipe.class);

    private String title;
    private String description;
    private Integer serves;

    private Integer preparationTime;

    public Recipe(String title, String description, Integer serves, Integer preparationTime) {
        this.title = title;
        this.description = description;
        this.serves = serves;
        this.preparationTime = preparationTime;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getServes() {
        return serves;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }

    public static List<Recipe> all() {
        return find.query().findList();
    }

}
