package controllers;

import models.Session;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

import java.util.List;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result getSessions() {
        List<Session> sessions = Session.find.all();
        return ok(Json.toJson(sessions));
    }

}
