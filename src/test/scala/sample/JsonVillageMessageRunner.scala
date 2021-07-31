package sample

import json.engine.village.VillageBox
import json.engine.village.analysis.server2client.{
  ChatFromServerAE,
  ErrorFromServerAE,
  FlavorTextAE,
  GameResultAE,
  NextGameInvitationAE,
  NextGameInvitationIsClosedAE,
  PhaseAE
}
import licos.json.engine.BOX
import licos.json.engine.processing.{
  SpecificProcessingEngineFactory,
  VillagePE,
  VillageProcessingEngine,
  VillageProcessingEngineFactory
}
import play.api.libs.json.{JsValue, Json}

import scala.io.{BufferedSource, Source}

object JsonVillageMessageRunner extends App {
  private val processingEngineFactory: VillageProcessingEngineFactory = SpecificProcessingEngineFactory
    .create(VillagePE)
    .asInstanceOf[VillageProcessingEngineFactory]
    .set(new ChatFromServerAE())
    .set(new PhaseAE())
    .set(new FlavorTextAE())
    .set(new GameResultAE())
    .set(new NextGameInvitationAE())
    .set(new NextGameInvitationIsClosedAE())
    .set(new ErrorFromServerAE())

  private val processingEngine: VillageProcessingEngine = processingEngineFactory.create

  private val aJSONExampleOfTheLiCOSProtocol: String = {
    val source: BufferedSource = Source.fromURL(
      "https://raw.githubusercontent.com/besna-institute/werewolfworld/gh-pages/village/example/0.3/server2client/firstMorning.jsonld"
    )
    val json: String = source.getLines().mkString("\n")
    source.close()
    json
  }

  private val anExampleOfBOX: BOX = new VillageBox("test")

  processingEngine.process(anExampleOfBOX, aJSONExampleOfTheLiCOSProtocol) match {
    case Right(jsValue: JsValue) =>
      println("Send a response:")
      println(Json.prettyPrint(jsValue))
    case Left(jsValue: JsValue) =>
      println("Send no response")
      println(Json.prettyPrint(jsValue))
  }

}
