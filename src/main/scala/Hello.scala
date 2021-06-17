import typings.next.utilsMod.NextApiHandler

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport}

object Hello {
  @JSExportTopLevel(JSImport.Default, "Hello")
  val endpoint: NextApiHandler[js.Object] = (req, res) => {
    res.status(200).json(js.Dynamic.literal(text = "Hello"))
  }
}
