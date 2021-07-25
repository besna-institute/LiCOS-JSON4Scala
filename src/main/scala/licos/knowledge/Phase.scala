package licos.knowledge

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration._

sealed abstract class Phase(val label: String) extends Product with Serializable {
  override def toString: String = label

  override def equals(o: Any): Boolean = {
    o match {
      case p: Phase =>
        import cats.implicits._
        p.label === label
      case _ => false
    }
  }

  def timeLimit(day: Int, numberOfAlivePlayers: Int): Option[FiniteDuration]
}

case object FlavorText extends Phase("flavor text") {

  override def hashCode(): Int = 512001

  override def timeLimit(day: Int, numberOfAlivePlayers: Int): Option[FiniteDuration] = Option.empty[FiniteDuration]

}

case object Morning extends Phase("morning") {

  override def hashCode(): Int = 512002

  override def timeLimit(day: Int, numberOfAlivePlayers: Int): Option[FiniteDuration] = {
    if (1 < day) {
      if (numberOfAlivePlayers <= 6) {
        Some(4.minutes)
      } else if (numberOfAlivePlayers <= 12) {
        Some(6.minutes)
      } else {
        Some(8.minutes)
      }
    } else {
      Some(5.minutes)
    }
  }

}

case object Noon extends Phase("noon") {

  override def hashCode(): Int = 512003

  override def timeLimit(day: Int, numberOfAlivePlayers: Int): Option[FiniteDuration] = Some(60.seconds)

}

case object Night extends Phase("night") {

  override def hashCode(): Int = 512004

  override def timeLimit(day: Int, numberOfAlivePlayers: Int): Option[FiniteDuration] = Some(60.seconds)

}

case object Result extends Phase("result") {

  override def hashCode(): Int = 512005

  override def timeLimit(day: Int, numberOfAlivePlayers: Int): Option[FiniteDuration] = Option.empty[FiniteDuration]

}

case object PostMortemDiscussion extends Phase("post-mortem discussion") {

  override def hashCode(): Int = 512006

  override def timeLimit(day: Int, numberOfAlivePlayers: Int): Option[FiniteDuration] = Some((24 * 60 * 60).seconds)

}
