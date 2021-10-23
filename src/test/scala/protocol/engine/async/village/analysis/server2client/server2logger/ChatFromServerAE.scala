package protocol.engine.async.village.analysis.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.ChatFromServerProtocol
import licos.protocol.engine.async.analysis.village.server2client.server2logger.ChatFromServerAnalysisEngine4Logger
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.server2logger.ChatFromServer
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class ChatFromServerAE extends ChatFromServerAnalysisEngine4Logger {
  override def process(box: VillageBOX, chatFromServer: ChatFromServerProtocol)(implicit
      ec:                   ExecutionContext
  ): Future[Option[VillageMessageProtocol]] = {
    box match {
      case _: VillageBox => Future.successful(Some(VillageMessageTestProtocol(ChatFromServer.`type`)))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
