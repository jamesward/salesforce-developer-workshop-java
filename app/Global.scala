import java.io.File

import play.api.{Mode, Configuration}
import play.api.Mode.Mode
import play.api.mvc._
import play.filters.gzip.GzipFilter
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Global extends WithFilters(new GzipFilter(), OnlyHttpsFilter) {

  // nice error message when database isn't configured
  override def onLoadConfig(config: Configuration, path: File, classloader: ClassLoader, mode: Mode): Configuration = {
    config.getString("db.default.url").fold {
      mode match {
        case Mode.Dev =>
          throw new Exception("You need to configure Heroku Connect! Get the HEROKUCONNECT_URL config value from your Heroku app.\nCreate a file named conf/local.conf containing:\ndb.default.url=\"YOUR_HEROKUCONNECT_URL?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory\"")
        case Mode.Prod =>
          throw new Exception("You need to add the Heroku Connect add-on to this app")
      }
    } { _ =>
      super.onLoadConfig(config, path, classloader, mode)
    }
  }
}

object OnlyHttpsFilter extends Filter {
  def apply(nextFilter: (RequestHeader) => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    nextFilter(requestHeader).map { result =>
      if (requestHeader.secure || requestHeader.domain == "localhost")
        result
      else
        Results.MovedPermanently("https://" + requestHeader.domain + requestHeader.uri)
    }
  }
}