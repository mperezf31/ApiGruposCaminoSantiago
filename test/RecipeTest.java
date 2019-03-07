import models.Ingredient;
import models.NutritionalData;
import models.Recipe;
import models.Step;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RecipeTest {

    private Recipe recipe;

    @Before
    public void setUp() {
        recipe = new Recipe("Tortilla francesa", "Nueva receta para la tortilla francesa", 4, 40);
    }

    @Test
    public void updateRecipeTitle() {
        assertNotNull(recipe);
        assertEquals("Tortilla francesa", recipe.getTitle());

        recipe.setTitle("Tortilla");
        assertEquals("Tortilla", recipe.getTitle());
    }

    @Test
    public void updateRecipeDescription() {
        assertNotNull(recipe);
        assertEquals("Nueva receta para la tortilla francesa", recipe.getDescription());

        recipe.setDescription("La mejor tortilla francesa");
        assertEquals("La mejor tortilla francesa", recipe.getDescription());
    }

    @Test
    public void addRecipeIngredients() {

        //Add the ingredients
        Ingredient ingredient_1 = new Ingredient("huevo");
        Ingredient ingredient_2 = new Ingredient("sal");
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient_1);
        ingredients.add(ingredient_2);
        recipe.setIngredients(ingredients);


        assertNotNull(recipe.getIngredients());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals("huevo", recipe.getIngredients().get(0).getName());
        assertEquals("sal", recipe.getIngredients().get(1).getName());
        assertNotEquals("Ingrediente falso", recipe.getIngredients().get(1).getName());

    }


    @Test
    public void addRecipeSteps() {

        //Add steps
        Step steps_1 = new Step("Poner una cucharadita de aceite a calentar en una sartén");
        Step steps_2 = new Step("Cuando esté caliente, vertir los huevos batidos");
        List<Step> steps = new ArrayList<>();
        steps.add(steps_1);
        steps.add(steps_2);
        recipe.setSteps(steps);

        assertNotNull(recipe.getSteps());
        assertEquals(2, recipe.getSteps().size());
        assertEquals("Poner una cucharadita de aceite a calentar en una sartén", recipe.getSteps().get(0).getDescription());
        assertEquals("Cuando esté caliente, vertir los huevos batidos", recipe.getSteps().get(1).getDescription());
        assertNotEquals("Step falso", recipe.getSteps().get(1).getDescription());


    }

    @Test
    public void updateNutritionalData() {

        NutritionalData nutritionalData = new NutritionalData(10, 23, 11, 89);
        recipe.setNutritionalData(nutritionalData);

        assertNotNull(recipe.getNutritionalData());
        assertEquals(10, (long) recipe.getNutritionalData().getCalories());
        assertEquals(23, (long) recipe.getNutritionalData().getProtein());
        assertEquals(11, (long) recipe.getNutritionalData().getFat());
        assertEquals(89, (long) recipe.getNutritionalData().getCarbohydrates());

    }

}
