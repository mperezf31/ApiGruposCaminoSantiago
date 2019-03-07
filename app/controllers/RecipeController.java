package controllers;

import models.Recipe;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */

public class RecipeController extends Controller {

    @Inject
    private FormFactory formFactory;

    /**
     * List all recipes
     */
    public Result listRecipes() {
        List<Recipe> recipes;

        recipes = Recipe.all();

        if (request().accepts("application/json")) {
            return Results.ok(Json.toJson(recipes));
        } else {
            return Results.notAcceptable();
        }
    }

    /**
     * Show the recipe result in json or xml format, it depends of the content-negotiation
     */
    private Result contentNegotiationRecipe(Recipe recipe) {

        if (request().accepts("application/json")) {
            return Results.ok(Json.toJson(recipe));
        } else {
            return Results.notAcceptable();
        }
    }
}
