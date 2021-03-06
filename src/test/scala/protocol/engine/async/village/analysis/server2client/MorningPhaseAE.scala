package protocol.engine.async.village.analysis.server2client

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.MorningPhaseProtocol
import licos.protocol.engine.async.analysis.village.server2client.MorningPhaseAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}
import protocol.element.VillageMessageTestProtocol
import protocol.engine.village.example.server2client.MorningPhase
import protocol.engine.village.VillageBox

import scala.concurrent.{ExecutionContext, Future}

final class MorningPhaseAE extends MorningPhaseAnalysisEngine {
  override def process(box: VillageBOX, morningPhase: MorningPhaseProtocol)(implicit
      ec:                   ExecutionContext
  ): Future[Option[VillageMessageProtocol]] = {
    box match {
      case _: VillageBox => Future.successful(Some(VillageMessageTestProtocol(MorningPhase.`type`)))
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
