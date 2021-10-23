package licos.protocol.engine.async.analysis.village.client2server.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.AnonymousAudienceChatFromClientProtocol4Logger
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.concurrent.{ExecutionContext, Future}

trait AnonymousAudienceChatFromClientAnalysisEngine4Logger extends VillageMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(
      box:                             VillageBOX,
      anonymousAudienceChatFromClient: AnonymousAudienceChatFromClientProtocol4Logger
  )(implicit ec:                       ExecutionContext): Future[Option[VillageMessageProtocol]]
}

object AnonymousAudienceChatFromClientAnalysisEngine4Logger {
  val name: String = "village.client2server.server2logger.AnonymousAudienceChatFromClient"
}
