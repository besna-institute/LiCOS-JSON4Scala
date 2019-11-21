package licos.protocol.engine.analysis.village.client2server.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.OnymousAudienceChatFromClientProtocol
import licos.protocol.engine.analysis.village.VillageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX

import scala.util.Try

trait OnymousAudienceChatFromClientAnalysisEngine extends VillageAnalysisEngine {
  def process(box: VillageBOX, onymousAudienceChatFromClient: OnymousAudienceChatFromClientProtocol): Try[VillageMessageProtocol]
}

object OnymousAudienceChatFromClientAnalysisEngine {
  val name: String = "village.client2server.server2logger.OnymousAudienceChatFromClient"
}
