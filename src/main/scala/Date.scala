import slinky.core.annotations.react
import slinky.core.{CustomAttribute, FunctionalComponent}
import slinky.web.html.time
import typings.dateFns.dateFnsFormatMod.default.{^ => format}
import typings.dateFns.dateFnsParseISOMod.{default => parseISO}

@react object Date {

  private val dateTime = CustomAttribute[String]("dateTime")
  case class Props(dateString: String)
  val component = FunctionalComponent[Props] { props =>
    val date = parseISO(props.dateString)
    time(dateTime := props.dateString)(
      format("LLLL, d, yyyy", date)
    )
  }

}
