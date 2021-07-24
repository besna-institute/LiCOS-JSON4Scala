package licos.protocol.element.lobby.part

import java.util.Locale

import licos.json.element.village.JsonSubError
import licos.knowledge.{Data2Knowledge, Severity}
import licos.protocol.element.village.part.NameProtocol

final case class ErrorProtocol(content: NameProtocol, severity: Severity, source: String, isFromServer: Boolean) {

  override def hashCode(): Int = 522001

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: ErrorProtocol =>
        protocol.content == content &&
          protocol.severity == severity &&
          protocol.source == source &&
          protocol.isFromServer == isFromServer
      case _ => false
    }
  }

  lazy val json: Option[JsonSubError] = {
    Some(
      JsonSubError(
        content.json(Some(Locale.ENGLISH)),
        severity.label,
        source,
        isFromServer
      )
    )
  }

}

object ErrorProtocol {

  def read(json: JsonSubError): Option[ErrorProtocol] = {
    Data2Knowledge
      .severityOpt(json.severity)
      .map { severity: Severity =>
        ErrorProtocol(
          Data2Knowledge.name(json.content),
          severity,
          json.source,
          json.isFromServer
        )
      }
  }

}
