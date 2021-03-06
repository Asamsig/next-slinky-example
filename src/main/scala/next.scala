import slinky.core.annotations.react
import slinky.core.{ExternalComponent, ExternalComponentNoProps}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** Need a proper facade for Next.js components
  */
package object next {

  @react object Link extends ExternalComponent {
    case class Props(href: String)
    override val component = NextLink
  }

  @js.native
  @JSImport("next/link", JSImport.Default)
  private object NextLink extends js.Object {
    def apply(): js.Object = js.native
  }

  @react object Image extends ExternalComponent /*WithAttributes[image.tag.type]*/ {
    case class Props(
        className: String,
        src: String,
        height: Int,
        width: Int,
        alt: String,
        priority: Boolean = false
    )
    override val component = NextImage
  }

  @js.native
  @JSImport("next/image", JSImport.Default)
  private object NextImage extends js.Object {
    def apply(): js.Object = js.native
  }

  object Head extends ExternalComponentNoProps {
    override val component = NextHead
  }

  @js.native
  @JSImport("next/head", JSImport.Default)
  private object NextHead extends js.Object {
    def apply(): js.Object = js.native
  }

}
