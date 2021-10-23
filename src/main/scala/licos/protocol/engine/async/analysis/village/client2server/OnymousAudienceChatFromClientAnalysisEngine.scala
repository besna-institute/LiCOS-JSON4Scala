package licos.protocol.engine.async.analysis.village.client2server

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.OnymousAudienceChatFromClientProtocol
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.concurrent.{ExecutionContext, Future}

trait OnymousAudienceChatFromClientAnalysisEngine extends VillageMessageAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(
      box:                           VillageBOX,
      onymousAudienceChatFromClient: OnymousAudienceChatFromClientProtocol
  )(implicit ec:                     ExecutionContext): Future[Option[VillageMessageProtocol]]
}

object OnymousAudienceChatFromClientAnalysisEngine {
  val name: String = "village.client2server.OnymousAudienceChatFromClient"
}
