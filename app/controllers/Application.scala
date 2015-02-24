package controllers

import play.api._
import play.api.mvc._
import play.api.cache.Cache
import play.api.Play.current

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def login = Action {
    val r = (new scala.util.Random).nextInt(100000)
    Ok("Welcome!").withSession(
      "connected" -> s"${r}@gmail.com"
    );
  }

  def test = Action { request =>
    val user = request.session.get("connected");
    if(user != null) {
      Ok("Hello " + user);
    } else {
      Unauthorized("Oops, you are not connected");
    }
  }

  def logout = Action { request =>
    Ok("Bye").withNewSession;
  }
}
