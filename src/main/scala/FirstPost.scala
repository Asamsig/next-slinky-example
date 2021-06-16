import slinky.core.{FunctionalComponent, ReactComponentClass}
import slinky.web.html.{h1, title}

import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport}

object FirstPost {
  @JSExportTopLevel(JSImport.Default, "FirstPost")
  val component: ReactComponentClass[Unit] = FunctionalComponent[Unit] { _ =>
    Layout(home = false)(
      Next.Head()(
        title("First Post")
      ),
      h1("First Post")
    )
  }
}
