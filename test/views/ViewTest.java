package views;

import models.*;
import org.junit.Test;
import play.Application;
import play.test.Helpers;
import play.test.WithApplication;
import play.twirl.api.Content;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.test.Helpers.contentAsString;

public class ViewTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return Helpers.fakeApplication(Helpers.inMemoryDatabase());
    }

    @Test
    public void testRecipeView() {
        Content html = views.xml.recipe.render(getRecipe());
        assertEquals("application/xml", html.contentType());
        assertTrue(contentAsString(html).contains("<recipe>"));
        assertTrue(contentAsString(html).contains("<id>"));
        assertTrue(contentAsString(html).contains("<whenCreated>"));
        assertTrue(contentAsString(html).contains("<title>"));
        assertTrue(contentAsString(html).contains("<description>"));
        assertTrue(contentAsString(html).contains("<serves>"));
        assertTrue(contentAsString(html).contains("<preparationTime>"));
        assertTrue(contentAsString(html).contains("<ingredients>"));
        assertTrue(contentAsString(html).contains("<steps>"));
    }

    @Test
    public void testRecipesView() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(getRecipe());
        Content html = views.xml.recipes.render(recipes);
        assertEquals("application/xml", html.contentType());
        assertTrue(contentAsString(html).contains("<recipes>"));
    }

    @Test
    public void testIngredientView() {
        Content html = views.xml.ingredient.render(new Ingredient("tomate"));
        assertEquals("application/xml", html.contentType());
        assertTrue(contentAsString(html).contains("<ingredient>"));
        assertTrue(contentAsString(html).contains("<id>"));
        assertTrue(contentAsString(html).contains("<name>"));
    }

    @Test
    public void testStepView() {
        Content html = views.xml.step.render(new Step("Poner dos litros de agua a hervir"));
        assertEquals("application/xml", html.contentType());
        assertTrue(contentAsString(html).contains("<step>"));
        assertTrue(contentAsString(html).contains("<id>"));
        assertTrue(contentAsString(html).contains("<description>"));
    }

    @Test
    public void testCategoryView() {
        Content html = views.xml.category.render(new Category("carne"));
        assertEquals("application/xml", html.contentType());
        assertTrue(contentAsString(html).contains("<category>"));
        assertTrue(contentAsString(html).contains("<id>"));
        assertTrue(contentAsString(html).contains("<name>"));
    }

    @Test
    public void testNutritionalDataView() {
        Content html = views.xml.nutritionalData.render(new NutritionalData(12, 19, 1, 18));
        assertEquals("application/xml", html.contentType());
        assertTrue(contentAsString(html).contains("<nutritionalData>"));
        assertTrue(contentAsString(html).contains("<calories>"));
        assertTrue(contentAsString(html).contains("<protein>"));
        assertTrue(contentAsString(html).contains("<carbohydrates>"));
        assertTrue(contentAsString(html).contains("<fat>"));
    }


    private Recipe getRecipe() {
        Recipe recipe = new Recipe("Sopa", "Receta para elavorar la sopa de la abuela",4,40);

        //Add ingredients
        Ingredient ingredient_1 = new Ingredient("fideos");
        Ingredient ingredient_2 = new Ingredient("sal");
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient_1);
        ingredients.add(ingredient_2);
        recipe.setIngredients(ingredients);

        //Add steps
        Step steps_1 = new Step("Poner a hervir dos litros de agua");
        Step steps_2 = new Step("Echar 200g de sal");
        List<Step> steps = new ArrayList<>();
        steps.add(steps_1);
        steps.add(steps_2);
        recipe.setSteps(steps);

        return recipe;
    }

}
