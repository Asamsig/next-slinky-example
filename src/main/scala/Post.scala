import slinky.core.{FunctionalComponent, ReactComponentClass}
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.Promise
import scala.scalajs.js.annotation.{JSExportAll, JSExportTopLevel, JSImport}

@JSExportAll
object Post {

  case class PostData(id: String, date: String, title: String, contentHtml: String)
  case class Props(postData: PostData)
  @JSExportTopLevel(JSImport.Default, "Post")
  val component: ReactComponentClass[Props] = FunctionalComponent[Props] { props =>
    Layout(home = false)(
      Next.Head(
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

  @JSExportTopLevel("getStaticPaths", "Post")
  val getStaticPaths: js.Function0[Promise[js.Object]] = () =>
    Promise.resolve[js.Object] {
      val paths = Posts.getAllPostIds()
      js.Dynamic.literal(
        paths = paths,
        fallback = false
      )
    }

  @JSExportTopLevel("getStaticProps", "Post")
  val getStaticProps: js.Function1[js.Dynamic, Promise[js.Object]] = params =>
    Promise.resolve[js.Object] {
      val postData = Posts.getPostData(params.params.id.toString)
      js.Dynamic.literal(
        props = js.Dynamic.literal(
          postData = postData
        )
      )
    }

}
