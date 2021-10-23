package licos.protocol.engine.async.analysis.village.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.AnonymousAudienceChatFromServerProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.concurrent.{ExecutionContext, Future}

trait AnonymousAudienceChatFromServerAnalysisEngine4Logger extends VillageMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(
      box:                             VillageBOX,
      anonymousAudienceChatFromServer: AnonymousAudienceChatFromServerProtocol
  )(implicit ec:                       ExecutionContext): Future[Option[VillageMessageProtocol]]
}

object AnonymousAudienceChatFromServerAnalysisEngine4Logger {
  val name: String = "village.server2client.server2logger.AnonymousAudienceChatFromServer"
}
