package licos.protocol.engine.async.analysis.village.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.ReceivedFlavorTextMessageProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.concurrent.{ExecutionContext, Future}

trait ReceivedFlavorTextMessageAnalysisEngine extends VillageMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(
      box:                       VillageBOX,
      receivedFlavorTextMessage: ReceivedFlavorTextMessageProtocol
  )(implicit ec:                 ExecutionContext): Future[Option[VillageMessageProtocol]]
}

object ReceivedFlavorTextMessageAnalysisEngine {
  val name: String = "village.client2server.ReceivedFlavorTextMessage"
}
