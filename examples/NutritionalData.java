package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

@Entity
public class NutritionalData extends ModelBase {

    private Integer calories;
    private Integer protein;
    private Integer carbohydrates;
    private Integer fat;

    @JsonBackReference
    @OneToOne(mappedBy = "nutritionalData")
    private Recipe recipe;

    public NutritionalData(Integer calories, Integer protein, Integer fat, Integer carbohydrates) {
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
    }

    @JsonIgnore
    @Override
    public Long getId() {
        return super.getId();
    }

    @JsonIgnore
    @Override
    public Timestamp getWhenCreated() {
        return super.getWhenCreated();
    }

    public Integer getCalories() {
        return calories;
    }

    public Integer getProtein() {
        return protein;
    }

    public Integer getCarbohydrates() {
        return carbohydrates;
    }

    public Integer getFat() {
        return fat;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public void setProtein(Integer protein) {
        this.protein = protein;
    }

    public void setCarbohydrates(Integer carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }
}
