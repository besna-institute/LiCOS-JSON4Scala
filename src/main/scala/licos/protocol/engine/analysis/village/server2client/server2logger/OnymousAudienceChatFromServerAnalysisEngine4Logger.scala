package licos.protocol.engine.analysis.village.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.OnymousAudienceChatFromServerProtocol4Logger
import licos.protocol.engine.analysis.village.VillageMessageAnalysisEngine
import licos.protocol.engine.processing.village.VillageBOX

import scala.util.Try

trait OnymousAudienceChatFromServerAnalysisEngine4Logger extends VillageMessageAnalysisEngine {
  def process(
      box:                           VillageBOX,
      onymousAudienceChatFromServer: OnymousAudienceChatFromServerProtocol4Logger
  ): Try[VillageMessageProtocol]
}

object OnymousAudienceChatFromServerAnalysisEngine4Logger {
  val name: String = "village.server2client.server2logger.OnymousAudienceChatFromServer"
}
