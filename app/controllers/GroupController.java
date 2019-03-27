package controllers;

import models.GroupCamino;
import models.Pilgrim;
import models.Post;
import models.responses.Group;
import models.responses.Groups;
import models.responses.UserLogin;
import play.data.Form;
import play.data.FormFactory;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import java.util.ArrayList;
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
        if (Pilgrim.findByEmail(pilgrim.getEmail()) != null) {
            String alreadyExist = Http.Context.current().messages().at("already-exists");
            return Results.badRequest(alreadyExist);
        }
        pilgrim.save();

        return contentNegotiationRecipe(pilgrim);
    }

    /**
     * Login
     */
    @Transactional
    public Result login() {

        Form<UserLogin> userForm = formFactory.form(UserLogin.class).bindFromRequest();

        // Check if the form contains errors
        if (userForm.hasErrors()) {
            return Results.badRequest(Json.toJson(userForm.errorsAsJson()));
        }

        UserLogin user = userForm.get();

        Pilgrim pilgrim = Pilgrim.login(user.getEmail(), user.getPassword());
        if (pilgrim == null) {
            return Results.notFound();
        } else {
            return contentNegotiationRecipe(pilgrim);
        }
    }

    /**
     * Create new group
     */
    @Transactional
    public Result createGroup() {

        Pilgrim pilgrim = Pilgrim.findById(getUserAutenticated());
        if (pilgrim == null) {
            String unauthorizedMsg = Http.Context.current().messages().at("unauthorized");
            return Results.forbidden(unauthorizedMsg);
        }

        Form<GroupCamino> groupCaminoForm = formFactory.form(GroupCamino.class).bindFromRequest();

        // Check if the form contains errors
        if (groupCaminoForm.hasErrors()) {
            return Results.badRequest(Json.toJson(groupCaminoForm.errorsAsJson()));
        }

        GroupCamino groupCamino = groupCaminoForm.get();
        groupCamino.setFounder(pilgrim);
        groupCamino.save();

        return contentNegotiationRecipe(groupCamino);
    }


    /**
     * Delete group
     */
    @Transactional
    public Result deleteGroup(Long groupId) {

        Pilgrim pilgrim = Pilgrim.findById(getUserAutenticated());
        if (pilgrim == null) {
            String unauthorizedMsg = Http.Context.current().messages().at("unauthorized");
            return Results.forbidden(unauthorizedMsg);
        }

        GroupCamino groupCamino = GroupCamino.findById(groupId);
        if (groupCamino == null) {
            return Results.notFound();
        }
        groupCamino.delete();

        return Results.ok();
    }

    /**
     * Create new post
     */
    @Transactional
    public Result createPost(Long groupId) {

        Pilgrim pilgrim = Pilgrim.findById(getUserAutenticated());
        if (pilgrim == null) {
            String unauthorizedMsg = Http.Context.current().messages().at("unauthorized");
            return Results.forbidden(unauthorizedMsg);
        }

        GroupCamino groupCamino = GroupCamino.findById(groupId);
        if (groupCamino == null) {
            return Results.notFound();
        }

        Form<Post> postForm = formFactory.form(Post.class).bindFromRequest();

        // Check if the form contains errors
        if (postForm.hasErrors()) {
            return Results.badRequest(Json.toJson(postForm.errorsAsJson()));
        }

        Post post = postForm.get();
        post.setAuthor(pilgrim);
        post.setGroupCamino(groupCamino);
        post.save();

        return contentNegotiationRecipe(post);
    }


    /**
     * Add member to he group
     */
    @Transactional
    public Result addGroupMember(Long groupId) {

        Pilgrim pilgrim = Pilgrim.findById(getUserAutenticated());
        if (pilgrim == null) {
            String unauthorizedMsg = Http.Context.current().messages().at("unauthorized");
            return Results.forbidden(unauthorizedMsg);
        }

        GroupCamino groupCamino = GroupCamino.findById(groupId);
        if (groupCamino == null) {
            return Results.notFound();
        }

        groupCamino.addMember(pilgrim);
        groupCamino.save();

        return contentNegotiationRecipe(groupCamino);
    }

    /**
     * Add member to he group
     */
    @Transactional
    public Result deleteGroupMember(Long groupId) {

        Pilgrim pilgrim = Pilgrim.findById(getUserAutenticated());
        if (pilgrim == null) {
            String unauthorizedMsg = Http.Context.current().messages().at("unauthorized");
            return Results.forbidden(unauthorizedMsg);
        }

        GroupCamino groupCamino = GroupCamino.findById(groupId);
        if (groupCamino == null) {
            return Results.notFound();
        }

        //Update groups
        List<GroupCamino> newGroups = new ArrayList<>();
        List<GroupCamino> groupsCamino = pilgrim.getGroupsCamino();
        for (GroupCamino groupCamino1 : groupsCamino) {
            if (!groupCamino1.getId().equals(groupId)) {
                newGroups.add(groupCamino1);
            }
        }
        pilgrim.setGroupsCamino(newGroups);
        pilgrim.save();

        //Update members
        List<Pilgrim> newMembers = new ArrayList<>();
        List<Pilgrim> members = groupCamino.getMembers();
        for (Pilgrim member : members) {
            if (member.getId().equals(pilgrim.getId())) {
                newMembers.add(member);
            }
        }
        groupCamino.setMembers(newMembers);
        groupCamino.save();

        return contentNegotiationRecipe(groupCamino);
    }


    /**
     * List users groups
     */
    public Result listGroups() {

        Pilgrim pilgrim = Pilgrim.findById(getUserAutenticated());
        if (pilgrim == null) {
            String unauthorizedMsg = Http.Context.current().messages().at("unauthorized");
            return Results.forbidden(unauthorizedMsg);
        }

        List<GroupCamino> founderGroups = new ArrayList<>();
        List<GroupCamino> memberGroups = new ArrayList<>();
        List<GroupCamino> otherGroups = new ArrayList<>();
        List<GroupCamino> allGroups = GroupCamino.all();
        for (GroupCamino groupCamino : allGroups) {
            if (groupCamino.getFounder().getId().equals(pilgrim.getId())) {
                founderGroups.add(groupCamino);
            } else if (checkMember(pilgrim.getId(), groupCamino.getMembers())) {
                memberGroups.add(groupCamino);
            } else {
                otherGroups.add(groupCamino);
            }

        }

        Groups groups = new Groups(founderGroups, memberGroups, otherGroups);

        if (request().accepts("application/json")) {
            return Results.ok(Json.toJson(groups));
        } else {
            return Results.notAcceptable();
        }
    }

    private boolean checkMember(Long id, List<Pilgrim> members) {
        for (Pilgrim member : members) {
            if (member.getId().equals(id)) return true;
        }
        return false;
    }


    /**
     * Get group
     */
    public Result getGroup(Long id) {
        GroupCamino groupCamino = GroupCamino.findById(id);
        if (groupCamino == null) {
            return Results.notFound();
        }

        return contentNegotiationRecipe(groupCamino);
    }

    /**
     * List all groups
     */
    public Result allGroups() {
        if (request().accepts("application/json")) {
            return Results.ok(Json.toJson(Group.mapToGroupList(GroupCamino.all())));
        } else {
            return Results.notAcceptable();
        }
    }

    /*

     */

    /**
     * getImage("photo","/ApiGruposCaminoSantiago/images/users/");
     *//*


    public String getImage(String keyImage, String destination) {
        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> picture = body.getFile(keyImage);
        if (picture != null) {
            File file = picture.getFile();
            try {
                File dir = new File(destination);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File fileToCreate = new File(dir, "img_" + System.currentTimeMillis() + ".png");
                Files.move(file, fileToCreate);
                return fileToCreate.getAbsolutePath();

            } catch (IOException e) {
                return null;
            }
        } else {
            return null;
        }
    }


    public Result uploadFile() {
        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> picture = body.getFile("picture");
        if (picture != null) {
            String fileName = picture.getFilename();
            String contentType = picture.getContentType();
            File file = picture.getFile();

            try {
                File dir = new File("/ApiGruposCaminoSantiago/images/users/");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                Files.move(file, new File(dir, "img_" + System.currentTimeMillis() + ".png"));

            } catch (IOException e) {
                return badRequest("move error");
            }

            return ok("File uploaded");
        } else {
            return badRequest("errpr");
        }
    }
*/
    private Result contentNegotiationRecipe(Object object) {
        if (request().accepts("application/json")) {
            return Results.ok(Json.toJson(object));
        } else {
            return Results.notAcceptable();
        }
    }


    private long getUserAutenticated() {
        String userId = request().getHeaders().get("Authentication").orElse("");
        if (!userId.equals("")) {
            return Long.parseLong(userId);
        } else {
            return 0;
        }
    }
}
