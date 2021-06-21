package pages

import slinky.core.{FunctionalComponent, KeyAndRefAddingStage, ReactComponentClass}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport}

protected object App {

  case class Props(Component: ReactComponentClass[js.Object], pageProps: js.Object)
  @JSExportTopLevel(JSImport.Default, "App")
  protected val component: ReactComponentClass[Props] = FunctionalComponent[Props] { args =>
    new KeyAndRefAddingStage[js.Object](js.Array(args.Component, args.pageProps))
  }

}
