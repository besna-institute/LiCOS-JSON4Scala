package licos.protocol.element.lobby.part

import licos.json.element.lobby.JsonHuman

final case class HumanProtocol(max: Int, current: Int) {

  override def hashCode(): Int = 522003

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: HumanProtocol =>
        protocol.max == max &&
          protocol.current == current
      case _ => false
    }
  }

  lazy val json: Option[JsonHuman] = {
    Some(
      JsonHuman(
        max,
        current
      )
    )
  }

}

object HumanProtocol {

  def read(json: JsonHuman): Option[HumanProtocol] = {
    Some(
      HumanProtocol(
        json.max,
        json.current
      )
    )
  }

}
