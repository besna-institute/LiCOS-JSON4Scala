package licos.protocol.engine.async.analysis.village.client2server.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.OnymousAudienceScrollProtocol4Logger
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.concurrent.{ExecutionContext, Future}

trait OnymousAudienceScrollAnalysisEngine4Logger extends VillageMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: VillageBOX, onymousAudienceScroll: OnymousAudienceScrollProtocol4Logger)(implicit
      ec:          ExecutionContext
  ): Future[Option[VillageMessageProtocol]]
}

object OnymousAudienceScrollAnalysisEngine4Logger {
  val name: String = "village.client2server.server2logger.OnymousAudienceScroll"
}
