package licos.protocol.element.auth.part

import licos.json.element.auth.robot2server.JsonProgrammingLanguage

final case class ProgrammingLanguageProtocol(name: String, version: String) {

  override def hashCode(): Int = 511001

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: ProgrammingLanguageProtocol =>
        name == protocol.name &&
          version == protocol.version
      case _ => false
    }
  }

  lazy val json: Option[JsonProgrammingLanguage] = {
    Some(
      JsonProgrammingLanguage(
        name,
        version
      )
    )
  }

}

object ProgrammingLanguageProtocol {

  def read(json: JsonProgrammingLanguage): Option[ProgrammingLanguageProtocol] = {
    Some(
      ProgrammingLanguageProtocol(
        json.name,
        json.version
      )
    )
  }

}
