package pages

import components.Layout.siteTitle
import components.{Date, Layout, UtilStyles}
import next.{Head, Link}
import slinky.core.{FunctionalComponent, ReactComponentClass}
import slinky.web.html._

import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport}

protected object Index {

  case class Post(id: String, date: String, title: String)
  case class Props(allPostsData: Seq[Post])
  @JSExportTopLevel(JSImport.Default, "Index")
  protected val component: ReactComponentClass[Props] =
    FunctionalComponent[Props] { props =>
      Layout(home = true)(
        Head(
          title(siteTitle)
        ),
        section(className := UtilStyles.headingMd)(
          p("Hi there!"),
          p("I am a Scala enthusiast!"),
          p(
            "This is a sample website - you’ll be building a site like this on ",
            a(href := "https://nextjs.org/learn")("our Next.js tutorial"),
            "."
          )
        ),
        section(className := s"${UtilStyles.headingMd} ${UtilStyles.padding1px}")(
          h2(className := UtilStyles.headingLg)("Blog"),
          ul(className := UtilStyles.list)(
            props.allPostsData.map { case Post(id, date, title) =>
              li(className := UtilStyles.listItem, key := id)(
                Link(href = s"/posts/$id")(
                  a(title)
                ),
                br(),
                small(className := UtilStyles.lightText)(
                  Date(dateString = date)
                )
              )
            }
          )
        )
      )
    }

}
