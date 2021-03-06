package protocol.engine.async.village.analysis.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.BoardProtocol
import licos.protocol.engine.async.analysis.village.client2server.BoardAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.client2server.Board
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class BoardAE extends BoardAnalysisEngine {
  override def process(box: VillageBOX, board: BoardProtocol)(implicit
      ec:                   ExecutionContext
  ): Future[Option[VillageMessageProtocol]] = {
    box match {
      case _: VillageBox => Future.successful(Some(VillageMessageTestProtocol(Board.`type`)))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
