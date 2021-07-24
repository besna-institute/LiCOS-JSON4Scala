package licos.protocol.element.village.part

import java.util.Locale

import licos.json.element.village.JsonChatText

final case class ChatTextProtocol(`@value`: String, `@language`: Locale) {

  override def hashCode(): Int = 533013

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: ChatTextProtocol =>
        protocol.`@value` == `@value` &&
          protocol.`@language` == `@language`
      case _ => false
    }
  }

  lazy val json: JsonChatText = JsonChatText(`@value`, `@language`.getLanguage)
}
