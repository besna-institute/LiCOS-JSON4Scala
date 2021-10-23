package licos.protocol.engine.async.analysis.village.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.FlavorTextProtocol4Logger
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.concurrent.{ExecutionContext, Future}

trait FlavorTextAnalysisEngine4Logger extends VillageMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: VillageBOX, flavorText: FlavorTextProtocol4Logger)(implicit
      ec:          ExecutionContext
  ): Future[Option[VillageMessageProtocol]]
}

object FlavorTextAnalysisEngine4Logger {
  val name: String = "village.server2client.server2logger.FlavorText"
}
