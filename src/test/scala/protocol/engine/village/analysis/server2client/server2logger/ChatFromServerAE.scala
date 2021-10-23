package protocol.engine.village.analysis.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.ChatFromServerProtocol4Logger
import licos.protocol.engine.analysis.village.server2client.server2logger.ChatFromServerAnalysisEngine4Logger
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.server2logger.ChatFromServer
import protocol.engine.village.VillageBox

import scala.util.{Failure, Success, Try}

final class ChatFromServerAE extends ChatFromServerAnalysisEngine4Logger {
  override def process(box: VillageBOX, chatFromServer: ChatFromServerProtocol4Logger): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox => Success(VillageMessageTestProtocol(ChatFromServer.`type`))
      case _ => Failure(new VillageBOXNotFoundException())
    }
  }
}
