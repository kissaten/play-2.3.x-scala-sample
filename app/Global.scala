import play.api._
import play.api.mvc._

import play.api.Logger
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

object RequestIdLoggingFilter extends Filter {
  def apply(nextFilter: (RequestHeader) => Future[Result])
  (requestHeader: RequestHeader): Future[Result] = {
    Logger.info(s"request_id=${requestHeader.headers.get("X-Request-ID")}")
    nextFilter(requestHeader)
  }
}

object Global extends WithFilters(RequestIdLoggingFilter) {
  // ...
}
