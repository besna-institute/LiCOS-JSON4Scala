package licos.protocol.element.village.part

import licos.json.element.village.JsonUpdate
import licos.knowledge.Phase

final case class UpdateProtocol(phase: Phase, day: Int) {

  override def hashCode(): Int = 533016

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: UpdateProtocol =>
        protocol.phase == phase &&
          protocol.day == day
      case _ => false
    }
  }

  def json(`@id`: String): JsonUpdate = JsonUpdate(
    `@id`.concat("/update"),
    phase.label,
    day
  )
}
