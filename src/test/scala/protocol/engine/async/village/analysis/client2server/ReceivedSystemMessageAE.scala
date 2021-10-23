package protocol.engine.async.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ReceivedSystemMessageProtocol
import licos.protocol.engine.async.analysis.village.client2server.ReceivedSystemMessageAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.ReceivedSystemMessage
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class ReceivedSystemMessageAE extends ReceivedSystemMessageAnalysisEngine {
  override def process(
      box:                   VillageBOX,
      receivedSystemMessage: ReceivedSystemMessageProtocol
  )(implicit ec:             ExecutionContext): Future[Option[VillageMessageProtocol]] = {
    box match {
      case _: VillageBox => Future.successful(Some(VillageMessageTestProtocol(ReceivedSystemMessage.`type`)))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
