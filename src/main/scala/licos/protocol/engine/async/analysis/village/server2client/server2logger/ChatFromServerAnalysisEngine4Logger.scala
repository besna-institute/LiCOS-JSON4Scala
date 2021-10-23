package licos.protocol.engine.async.analysis.village.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.ChatFromServerProtocol4Logger
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.concurrent.{ExecutionContext, Future}

trait ChatFromServerAnalysisEngine4Logger extends VillageMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: VillageBOX, chatFromServer: ChatFromServerProtocol4Logger)(implicit
      ec:          ExecutionContext
  ): Future[Option[VillageMessageProtocol]]
}

object ChatFromServerAnalysisEngine4Logger {
  val name: String = "village.serve2client.server2logger.ChatFromServer"
}
