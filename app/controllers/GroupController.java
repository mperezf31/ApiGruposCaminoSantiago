package controllers;

import models.GroupCamino;
import models.Pilgrim;
import play.data.Form;
import play.data.FormFactory;
import play.db.ebean.Transactional;
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
     * Create new user
     */
    @Transactional
    public Result createUser() {

        Form<Pilgrim> pilgrimForm = formFactory.form(Pilgrim.class).bindFromRequest();

        // Check if the form contains errors
        if (pilgrimForm.hasErrors()) {
            return Results.badRequest(Json.toJson(pilgrimForm.errorsAsJson()));
        }

        Pilgrim pilgrim = pilgrimForm.get();
        pilgrim.save();

        return contentNegotiationRecipe(pilgrim);
    }

    /**
     * Create new group
     */
    @Transactional
    public Result createGroup() {

        Form<Pilgrim> pilgrimForm = formFactory.form(Pilgrim.class).bindFromRequest();

        // Check if the form contains errors
        if (pilgrimForm.hasErrors()) {
            return Results.badRequest(Json.toJson(pilgrimForm.errorsAsJson()));
        }

        Pilgrim pilgrim = pilgrimForm.get();
        pilgrim.save();

        return contentNegotiationRecipe(pilgrim);
    }

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

    private Result contentNegotiationRecipe(Object object) {

        if (request().accepts("application/json")) {
            return Results.ok(Json.toJson(object));
        } else {
            return Results.notAcceptable();
        }
    }
}
