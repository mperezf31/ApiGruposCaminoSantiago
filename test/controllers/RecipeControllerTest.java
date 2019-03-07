package controllers;

import models.Category;
import models.Ingredient;
import models.Recipe;
import models.Step;
import org.junit.Test;
import play.Application;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.NOT_FOUND;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class RecipeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return Helpers.fakeApplication(Helpers.inMemoryDatabase());
    }

    @Test
    public void testIndex() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void testAddRecipe() {
        Recipe recipe = new Recipe("Sopa", "Receta para elavorar la sopa de la abuela", 4, 40);

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

        //Add category
        Category category = new Category("sopas");
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        recipe.setCategories(categories);

        Http.RequestBuilder request = new Http.RequestBuilder()
                .header("Accept", "application/xml")
                .method(POST)
                .bodyJson(Json.toJson(recipe))
                .uri("/recipe");

        Result result = route(app, request);
        assertEquals(OK, result.status());
        assertEquals("application/xml", result.contentType().get());

    }


    @Test
    public void testGetRecipesJson() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .header("Accept", "application/json")
                .method(GET)
                .uri("/recipes");

        Result result = route(app, request);
        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
    }


    @Test
    public void testGetRecipesXml() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .header("Accept", "application/xml")
                .method(GET)
                .uri("/recipes");

        Result result = route(app, request);
        assertEquals(OK, result.status());
        assertEquals("application/xml", result.contentType().get());

    }

    @Test
    public void testDeleteRecipes() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(DELETE)
                .uri("/recipe/1000");

        Result result = route(app, request);
        assertEquals(NOT_FOUND, result.status());
    }

    @Test
    public void testBadRoute() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("1/recipe");

        Result result = route(app, request);
        assertEquals(NOT_FOUND, result.status());
    }
}
