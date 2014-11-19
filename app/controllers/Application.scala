package controllers

import play.api._
import play.api.mvc._

import play.api.Logger
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

object RequestIdLoggingFilter extends Filter {
  def apply(nextFilter: (RequestHeader) => Future[Result])
  (requestHeader: RequestHeader): Future[Result] = {
    val startTime = System.currentTimeMillis
    nextFilter(requestHeader).map { result =>
      println(s"request_id=${requestHeader.headers.get("X-Request-ID")}")
      Logger.info(s"request_id=${requestHeader.headers.get("X-Request-ID")}")
      result
    }
  }
}

object Global extends WithFilters(RequestIdLoggingFilter) {

}

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}
