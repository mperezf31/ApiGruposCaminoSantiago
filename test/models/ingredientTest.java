package models;

import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.test.Helpers;
import play.test.WithApplication;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ingredientTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return Helpers.fakeApplication(Helpers.inMemoryDatabase());
    }

    private Long ingredientID;

    @Before
    public void setUp() {
        Ingredient ingredient = new Ingredient("sal");
        ingredient.save();
        ingredientID = ingredient.getId();
    }


    @Test
    public void shouldFindObjectsByID() {
        Ingredient ingredient = Ingredient.findById(ingredientID);
        assertNotNull(ingredient);
    }

    @Test
    public void shouldNotFindObjectsByIDNotExisting() {
        Ingredient ingredient = Ingredient.findById(1000L);
        assertNull(ingredient);
    }

    @Test
    public void shouldFindObjectsByName() {
        Ingredient ingredient = Ingredient.findIngredientByName("sal");
        assertNotNull(ingredient);
    }

    @Test
    public void shouldNotFindObjectsByNameNotExisting() {
        Ingredient ingredient = Ingredient.findIngredientByName("vinagre");
        assertNull(ingredient);
    }
}
