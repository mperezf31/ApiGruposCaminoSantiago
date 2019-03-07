package controllers;

import models.Category;
import models.Ingredient;
import models.Recipe;
import play.cache.SyncCacheApi;
import play.data.Form;
import play.data.FormFactory;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */

public class RecipeController extends Controller {

    @Inject
    private FormFactory formFactory;

    @Inject
    private SyncCacheApi cache;

    /**
     * Create new recipe
     */
    @Transactional
    public Result createRecipe() {

        Form<Recipe> recipeForm = formFactory.form(Recipe.class).bindFromRequest();

        // Check if the form contains errors
        if (recipeForm.hasErrors()) {
            return Results.badRequest(Json.toJson(recipeForm.errorsAsJson()));
        }

        Recipe recipe = recipeForm.get();

        // Check the ingredients of the new recipe, if one does not exist it is added to the database
        recipe.setIngredients(getIngredients(recipe.getIngredients()));

        // Check the categories of the new recipe, if one does not exist it is added to the database
        recipe.setCategories(getCategories(recipe.getCategories()));

        recipe.save();

        //Add recipe to the cache
        cache.set("recipe-" + recipe.getId(), recipe);

        // Remove recipes from the cache, because there is one more
        cache.remove("recipes");

        return contentNegotiationRecipe(recipe);
    }

    private List<Ingredient> getIngredients(List<Ingredient> ingredientsToCreate) {
        List<Ingredient> ingredients = new ArrayList<>();

        for (Ingredient ingredientToCreate : ingredientsToCreate) {
            // Find the ingredient in the database by the name
            Ingredient ingredientInDB = Ingredient.findIngredientByName(ingredientToCreate.getName());
            if (ingredientInDB != null) {
                ingredients.add(ingredientInDB);
            } else {
                ingredientToCreate.save();
                ingredients.add(ingredientToCreate);
            }
        }

        return ingredients;
    }

    private List<Category> getCategories(List<Category> categoriesToCreate) {
        List<Category> categories = new ArrayList<>();

        for (Category categoryToCreate : categoriesToCreate) {
            Category categoryInDB = Category.findCategoryByName(categoryToCreate.getName());
            if (categoryInDB != null) {
                categories.add(categoryInDB);
            } else {
                categoryToCreate.save();
                categories.add(categoryToCreate);
            }
        }
        return categories;
    }

    /**
     * Retrieve a recipe by id
     */
    public Result retrieveRecipe(Integer recipeId) {

        // Get the recipe from the cache, if it is null get from the database
        Recipe recipe = cache.get("recipe-" + recipeId);
        if (recipe == null) {
            recipe = Recipe.findById(recipeId.longValue());

            if (recipe != null) {
                //Add recipe to the cache
                cache.set("recipe-" + recipeId, recipe);
            } else {
                return Results.notFound();
            }
        }

        return contentNegotiationRecipe(recipe);
    }

    /**
     * Update a recipe
     */
    @Transactional
    public Result updateRecipe(Integer recipeId) {

        Recipe recipeToUpdate = Recipe.findById(recipeId.longValue());
        if (recipeToUpdate == null) {
            return Results.notFound();
        }

        Form<Recipe> recipeForm = formFactory.form(Recipe.class).bindFromRequest();

        // Check if the form contains errors
        if (recipeForm.hasErrors()) {
            return Results.badRequest(Json.toJson(recipeForm.errorsAsJson()));
        }

        Recipe recipeData = recipeForm.get();
        //Update recipe data
        recipeToUpdate.setTitle(recipeData.getTitle());
        recipeToUpdate.setDescription(recipeData.getDescription());
        recipeToUpdate.setServes(recipeData.getServes());
        recipeToUpdate.setPreparationTime(recipeData.getPreparationTime());
        recipeToUpdate.setIngredients(getIngredients(recipeData.getIngredients()));
        recipeToUpdate.setSteps(recipeData.getSteps());
        recipeToUpdate.setCategories(getCategories(recipeData.getCategories()));
        recipeToUpdate.setNutritionalData(recipeData.getNutritionalData());
        recipeToUpdate.update();

        //Update recipe to the cache
        cache.set("recipe-" + recipeId, recipeToUpdate);

        // Remove recipes from the cache, because the content has changed
        cache.remove("recipes");

        return contentNegotiationRecipe(recipeToUpdate);
    }

    /**
     * Remove a recipe
     */
    public Result deleteRecipe(Integer recipeId) {

        Recipe recipe = Recipe.findById(recipeId.longValue());

        if (recipe != null && recipe.delete()) {
            //Remove the recipe in cache
            cache.remove("recipe-" + recipeId);
            // Remove recipes from the cache, because there is one less
            cache.remove("recipes");
            return Results.ok();
        } else {
            return Results.notFound();
        }
    }

    /**
     * List all recipes
     */
    public Result listRecipes() {
        List<Recipe> recipes;

        //Get recipes by category
        String category = request().getQueryString("category");

        if (category != null) {
            //In this case we don´t use cache because the data is updated a lot.
            Category categoryInDB = Category.findCategoryByName(category);
            if (categoryInDB != null) {
                recipes = categoryInDB.getRecipes();
            } else {
                return Results.notFound();
            }

        } else {
            //First check if the cache contains all recipes, if it return null get them from the database
            recipes = cache.get("recipes");
            if (recipes == null) {
                recipes = Recipe.all();
                //Add recipes to the cache
                cache.set("recipes", recipes);
            }
        }


        if (request().accepts("application/json")) {
            return Results.ok(Json.toJson(recipes));
        } else if (request().accepts("application/xml")) {
            return Results.ok(views.xml.recipes.render(recipes));
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
        } else if (request().accepts("application/xml")) {
            return Results.ok(views.xml.recipe.render(recipe));
        } else {
            return Results.notAcceptable();
        }
    }


}
