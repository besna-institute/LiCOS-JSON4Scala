package licos.protocol.engine.analysis.village.server2client.server2logger

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.server2logger.ChatFromServerProtocol
import licos.protocol.engine.analysis.village.VillageAnalysisEngine
import licos.protocol.engine.processing.VillageBOX

import scala.util.Try

trait ChatFromServerAnalysisEngine extends VillageAnalysisEngine {
  def process(box: VillageBOX, chatFromServer: ChatFromServerProtocol): Try[VillageMessageProtocol]
}

object ChatFromServerAnalysisEngine {
  val name: String = "village.serve2client.server2logger.ChatFromServer"
}