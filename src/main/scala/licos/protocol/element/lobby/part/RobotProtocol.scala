package licos.protocol.element.lobby.part

import licos.json.element.lobby.JsonRobot

final case class RobotProtocol(min: Int, current: Int) {

  override def hashCode(): Int = 522009

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: RobotProtocol =>
        protocol.min == min &&
          protocol.current == current
      case _ => false
    }
  }

  lazy val json: Option[JsonRobot] = {
    Some(
      JsonRobot(
        min,
        current
      )
    )
  }

}

object RobotProtocol {

  def read(json: JsonRobot): Option[RobotProtocol] = {
    Some(
      RobotProtocol(
        json.min,
        json.current
      )
    )
  }

}
