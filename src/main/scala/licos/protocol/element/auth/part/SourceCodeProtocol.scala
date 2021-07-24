package licos.protocol.element.auth.part

import licos.json.element.auth.robot2server.JsonSourceCode

final case class SourceCodeProtocol(
    timestamp:           String,
    programmingLanguage: Seq[ProgrammingLanguageProtocol],
    url:                 String
) {

  override def hashCode(): Int = 511002

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: SourceCodeProtocol =>
        protocol.timestamp == timestamp &&
          protocol.programmingLanguage == programmingLanguage &&
          protocol.url == url
      case _ => false
    }
  }

  lazy val json: Option[JsonSourceCode] = {
    Some(
      JsonSourceCode(
        timestamp,
        programmingLanguage.flatMap(_.json.toList),
        url
      )
    )
  }
}

object SourceCodeProtocol {

  def read(json: JsonSourceCode): Option[SourceCodeProtocol] = {
    Some(
      SourceCodeProtocol(
        json.timestamp,
        json.programmingLanguage.flatMap { j =>
          ProgrammingLanguageProtocol.read(j).toList
        },
        json.url
      )
    )
  }

}
