package controllers;

import models.GroupCamino;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import java.util.List;

public class GroupController extends Controller {

    @Inject
    private FormFactory formFactory;

    /**
     * List all groups
     */
    public Result listGroups() {
        List<GroupCamino> groups = GroupCamino.all();

        if (request().accepts("application/json")) {
            return Results.ok(Json.toJson(groups));
        } else {
            return Results.notAcceptable();
        }
    }

    private Result contentNegotiationRecipe(GroupCamino recipe) {

        if (request().accepts("application/json")) {
            return Results.ok(Json.toJson(recipe));
        } else {
            return Results.notAcceptable();
        }
    }
}
