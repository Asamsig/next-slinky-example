import Layout.siteTitle
import slinky.core.ReactComponentClass._
import slinky.core.{FunctionalComponent, ReactComponentClass}
import slinky.web.html._

import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport}

object Index {
  @JSExportTopLevel(JSImport.Default, "Index")
  val component: ReactComponentClass[Unit] = FunctionalComponent[Unit] { _ =>
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
      )
    )
  }
}
