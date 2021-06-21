package pages

import components.{Date, Layout, UtilStyles}
import next.Head
import slinky.core.{FunctionalComponent, ReactComponentClass}
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport}

protected object Post {

  case class PostData(id: String, date: String, title: String, contentHtml: String)
  case class Props(postData: PostData)
  @JSExportTopLevel(JSImport.Default, "Post")
  protected val component: ReactComponentClass[Props] = FunctionalComponent[Props] { props =>
    Layout(home = false)(
      Head(
        title(props.postData.title)
      ),
      article(
        h1(className := UtilStyles.headingXl)(props.postData.title),
        div(className := UtilStyles.lightText)(
          Date(dateString = props.postData.date)
        ),
        div(dangerouslySetInnerHTML := js.Dynamic.literal(__html = props.postData.contentHtml))
      )
    )
  }

}
