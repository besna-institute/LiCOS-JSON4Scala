package licos.protocol.element.lobby.server2client

import licos.json.element.lobby.server2client.JsonSearchResult
import licos.protocol.element.lobby.part.{ErrorProtocol, VillageProtocol}
import play.api.libs.json.{JsValue, Json}

final case class SearchResultProtocol(villages: Seq[VillageProtocol], error: Option[ErrorProtocol])
    extends Server2ClientLobbyMessageProtocol {

  override def hashCode(): Int = 523010

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: SearchResultProtocol =>
        protocol.villages == villages &&
          protocol.error == error
      case _ => false
    }
  }

  private lazy val json: Option[JsonSearchResult] = {
    Some(
      new JsonSearchResult(
        villages.flatMap(_.json.toList),
        error.flatMap(_.json)
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object SearchResultProtocol {

  def read(json: JsonSearchResult): Option[SearchResultProtocol] = {
    Some(
      SearchResultProtocol(
        json.villages.flatMap(j => VillageProtocol.read(j).toList),
        json.error.flatMap(ErrorProtocol.read)
      )
    )
  }

}
