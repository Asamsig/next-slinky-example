import Layout.siteTitle
import slinky.core.ReactComponentClass._
import slinky.core.{FunctionalComponent, ReactComponentClass}
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.Promise
import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport}

object Index {

  case class Post(id: String, date: String, title: String)
  case class Props(posts: Seq[Post])
  @JSExportTopLevel(JSImport.Default, "Index")
  val component: ReactComponentClass[Props] =
    FunctionalComponent[Props] { props =>
      println("Props: " + props.posts)
      Layout(home = true)(
        Next.Head(
          title(siteTitle)
        ),
        section(className := UtilStyles.headingMd)(
          p("Hi there!"),
          p("I am a Scala enthusiast!"),
          p(
            "This is a sample website - you’ll be building a site like this on ",
            a(href := "https://nextjs.org/learn")("our Next.js tutorial"),
            "."
          ),
          div(
            "Read ",
            Next.Link(href = "/posts/first-post")(
              a("this page!")
            )
          )
        ),
        section(className := s"${UtilStyles.headingMd} ${UtilStyles.padding1px}")(
          h2(className := UtilStyles.headingLg)("Blog"),
          ul(className := UtilStyles.list)(
            props.posts.map { case Post(id, date, title) =>
              li(className := UtilStyles.listItem, key := id)(
                title,
                br(),
                id,
                br(),
                date
              )
            }
          )
        )
      )
    }

  @JSExportTopLevel("getStaticProps", "Index")
  val getStaticProps: js.Function0[Promise[js.Object]] = () =>
    Promise.resolve[js.Object] {
      println("Static Props called")
      val allPostsData = Posts.getSortedPostsData()
      js.Dynamic.literal(
        props = js.Dynamic.literal(posts = allPostsData)
      )
    }

}
