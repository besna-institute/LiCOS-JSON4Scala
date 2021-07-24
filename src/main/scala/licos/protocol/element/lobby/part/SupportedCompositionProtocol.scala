package licos.protocol.element.lobby.part

import licos.json.element.lobby.client2server.JsonSupportedComposition
import licos.knowledge.Composition

final case class SupportedCompositionProtocol(
    numberOfPlayers: Int,
    isSupportedForA: Boolean,
    isSupportedForB: Boolean,
    isSupportedForC: Boolean
) {

  override def hashCode(): Int = 522011

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: SupportedCompositionProtocol =>
        protocol.numberOfPlayers == numberOfPlayers &&
          protocol.isSupportedForA == isSupportedForA &&
          protocol.isSupportedForB == isSupportedForB &&
          protocol.isSupportedForC == isSupportedForC
      case _ => false
    }
  }

  lazy val json: Option[JsonSupportedComposition] = {
    Some(
      JsonSupportedComposition(
        isSupportedForA,
        isSupportedForB,
        isSupportedForC
      )
    )
  }

  def isSupportedForTheNumberOfPlayers: Boolean = Composition.support.`for`.contains(numberOfPlayers)

  def AOpt: Option[Composition] = {
    if (isSupportedForTheNumberOfPlayers) {
      Some(Composition.support.`for`(numberOfPlayers).A)
    } else {
      Option.empty[Composition]
    }
  }

  def BOpt: Option[Composition] = {
    if (isSupportedForTheNumberOfPlayers) {
      Some(Composition.support.`for`(numberOfPlayers).B)
    } else {
      Option.empty[Composition]
    }
  }

  def COpt: Option[Composition] = {
    if (isSupportedForTheNumberOfPlayers) {
      Some(Composition.support.`for`(numberOfPlayers).C)
    } else {
      Option.empty[Composition]
    }
  }

}

object SupportedCompositionProtocol {

  def read(json: JsonSupportedComposition, numberOfPlayers: Int): Option[SupportedCompositionProtocol] = {
    Some(
      SupportedCompositionProtocol(
        numberOfPlayers,
        json.A,
        json.B,
        json.C
      )
    )
  }
}
