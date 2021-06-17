import slinky.core.BuildingComponent._
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.core.{CustomAttribute, FunctionalComponent}
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.URIUtils.encodeURI
import scala.scalajs.js.annotation.JSImport

@JSImport("resources/layout.module.css", JSImport.Default)
@js.native
object Styles extends js.Object {
  val container: String = js.native
  val header: String = js.native
  val backToHome: String = js.native
}

@JSImport("resources/utils.module.css", JSImport.Default)
@js.native
object UtilStyles extends js.Object {
  val borderCircle: String = js.native
  val heading2Xl: String = js.native
  val headingLg: String = js.native
  val colorInherit: String = js.native
  val headingMd: String = js.native
  val padding1px: String = js.native
  val list: String = js.native
  val listItem: String = js.native
  val headingXl: String = js.native
  val lightText: String = js.native
}

@react object Layout {
  val name = "Alexander Samsig"
  val siteTitle = "Next.js Sample Website"

  val property = CustomAttribute[String]("property")

  case class Props(home: Boolean, children: ReactElement*)
  val component = FunctionalComponent[Props] { props =>
    div(className := Styles.container)(
      Next.Head(
        link(rel := "icon", href := "/favicon"),
        meta(
          property := "description",
          content := "Learn how to build a personal website using Next.js"
        ),
        meta(
          property := "og:image",
          content := s"https://og-image.vercel.app/${encodeURI(siteTitle)}.png?theme=light&md=0&fontSize=75px&images=https%3A%2F%2Fassets.vercel.com%2Fimage%2Fupload%2Ffront%2Fassets%2Fdesign%2Fnextjs-black-logo.svg"
        ),
        meta(property := "og:title", content := siteTitle),
        meta(property := "twitter:card", content := "summary_large_image")
      ),
      header(className := Styles.header)(
        if (props.home) {
          Seq(
            make(
              Next.Image(
                priority = true,
                src = "/images/profile.png",
                className = UtilStyles.borderCircle,
                height = 144,
                width = 144,
                alt = name
              )
            ),
            h1(className := UtilStyles.heading2Xl)(name)
          )
        } else {
          Seq(
            make(
              Next.Link(href = "/")(
                a(
                  Next.Image(
                    priority = true,
                    src = "/images/profile.png",
                    className = UtilStyles.borderCircle,
                    height = 108,
                    width = 108,
                    alt = name
                  )
                )
              )
            ),
            h2(className := UtilStyles.headingLg)(
              Next.Link(href = "/")(
                a(className := UtilStyles.colorInherit)(name)
              )
            )
          )
        }
      ),
      main(props.children),
      Option.unless(props.home) {
        div(className := Styles.backToHome)(
          Next.Link(href = "/")(
            a("← Back to home")
          )
        )
      }
    )
  }
}
