package licos.protocol.engine.async.processing.village.server2logger

import com.typesafe.scalalogging.Logger
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.{
  AnonymousAudienceChatFromClientProtocol,
  BoardProtocol,
  ChatFromClientProtocol,
  ErrorFromClientProtocol,
  OnymousAudienceBoardProtocol,
  OnymousAudienceChatFromClientProtocol,
  OnymousAudienceScrollProtocol,
  ScrollProtocol,
  StarProtocol,
  VoteProtocol
}
import licos.protocol.element.village.server2client.server2logger.{
  AnonymousAudienceChatFromServerProtocol,
  ChatFromServerProtocol,
  ErrorFromServerProtocol,
  FirstMorningPhaseProtocol,
  FlavorTextProtocol,
  GameResultProtocol,
  MorningPhaseProtocol,
  NightPhaseProtocol,
  NoonPhaseProtocol,
  OnymousAudienceChatFromServerProtocol,
  PostMortemDiscussionProtocol
}
import licos.protocol.engine.async.analysis.village.client2server.server2logger.*
import licos.protocol.engine.async.analysis.village.server2client.server2logger.*
import licos.protocol.engine.async.processing.ProcessingEngine
import licos.protocol.engine.processing.village.VillageBOX
import licos.protocol.engine.processing.{JSON2ProtocolException, NoEngineException}

import scala.concurrent.{ExecutionContext, Future}

final class VillageProcessingEngine4Logger(
    anonymousAudienceChatFromClientAnalysisEngine: Option[AnonymousAudienceChatFromClientAnalysisEngine4Logger],
    boardAnalysisEngine:                           Option[BoardAnalysisEngine4Logger],
    chatFromClientAnalysisEngine:                  Option[ChatFromClientAnalysisEngine4Logger],
    errorFromClientAnalysisEngine:                 Option[ErrorFromClientAnalysisEngine4Logger],
    onymousAudienceBoardAnalysisEngine:            Option[OnymousAudienceBoardAnalysisEngine4Logger],
    onymousAudienceChatFromClientAnalysisEngine:   Option[OnymousAudienceChatFromClientAnalysisEngine4Logger],
    onymousAudienceScrollAnalysisEngine:           Option[OnymousAudienceScrollAnalysisEngine4Logger],
    scrollAnalysisEngine:                          Option[ScrollAnalysisEngine4Logger],
    starAnalysisEngine:                            Option[StarAnalysisEngine4Logger],
    voteAnalysisEngine:                            Option[VoteAnalysisEngine4Logger],
    anonymousAudienceChatFromServerAnalysisEngine: Option[AnonymousAudienceChatFromServerAnalysisEngine4Logger],
    chatFromServerAnalysisEngine:                  Option[ChatFromServerAnalysisEngine4Logger],
    errorFromServerAnalysisEngine:                 Option[ErrorFromServerAnalysisEngine4Logger],
    firstMorningPhaseAnalysisEngine:               Option[FirstMorningPhaseAnalysisEngine4Logger],
    flavorTextAnalysisEngine:                      Option[FlavorTextAnalysisEngine4Logger],
    gameResultAnalysisEngine:                      Option[GameResultAnalysisEngine4Logger],
    morningPhaseAnalysisEngine:                    Option[MorningPhaseAnalysisEngine4Logger],
    nightPhaseAnalysisEngine:                      Option[NightPhaseAnalysisEngine4Logger],
    noonPhaseAnalysisEngine:                       Option[NoonPhaseAnalysisEngine4Logger],
    onymousAudienceChatFromServerAnalysisEngine:   Option[OnymousAudienceChatFromServerAnalysisEngine4Logger],
    postMortemDiscussionAnalysisEngine:            Option[PostMortemDiscussionAnalysisEngine4Logger]
) extends ProcessingEngine {

  private val logger: Logger = Logger[VillageProcessingEngine4Logger]

  @SuppressWarnings(
    Array[String](
      "org.wartremover.warts.ImplicitParameter",
      "org.wartremover.warts.Nothing",
      "org.wartremover.warts.Overloading"
    )
  )
  def process(box: VillageBOX, msg: VillageMessageProtocol)(implicit
      ec:          ExecutionContext
  ): Future[Option[VillageMessageProtocol]] = {

    def log(label: String): Unit = {
      val format: String = "process %s"
      logger.info(format.format(label))
    }

    msg match {
      case protocol: AnonymousAudienceChatFromServerProtocol =>
        anonymousAudienceChatFromServerAnalysisEngine match {
          case Some(engine: AnonymousAudienceChatFromServerAnalysisEngine4Logger) =>
            log(AnonymousAudienceChatFromServerAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(AnonymousAudienceChatFromServerAnalysisEngine4Logger.name))

        }
      case protocol: AnonymousAudienceChatFromClientProtocol =>
        anonymousAudienceChatFromClientAnalysisEngine match {
          case Some(engine: AnonymousAudienceChatFromClientAnalysisEngine4Logger) =>
            log(AnonymousAudienceChatFromClientAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(AnonymousAudienceChatFromClientAnalysisEngine4Logger.name))
        }
      case protocol: BoardProtocol =>
        boardAnalysisEngine match {
          case Some(engine: BoardAnalysisEngine4Logger) =>
            log(BoardAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(BoardAnalysisEngine4Logger.name))
        }
      case protocol: ChatFromClientProtocol =>
        chatFromClientAnalysisEngine match {
          case Some(engine: ChatFromClientAnalysisEngine4Logger) =>
            log(ChatFromClientAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(ChatFromClientAnalysisEngine4Logger.name))
        }
      case protocol: ErrorFromServerProtocol =>
        errorFromServerAnalysisEngine match {
          case Some(engine: ErrorFromServerAnalysisEngine4Logger) =>
            log(ErrorFromServerAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(ErrorFromServerAnalysisEngine4Logger.name))
        }
      case protocol: ErrorFromClientProtocol =>
        errorFromClientAnalysisEngine match {
          case Some(engine: ErrorFromClientAnalysisEngine4Logger) =>
            log(ErrorFromClientAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(ErrorFromClientAnalysisEngine4Logger.name))
        }
      case protocol: OnymousAudienceBoardProtocol =>
        onymousAudienceBoardAnalysisEngine match {
          case Some(engine: OnymousAudienceBoardAnalysisEngine4Logger) =>
            log(OnymousAudienceBoardAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(OnymousAudienceBoardAnalysisEngine4Logger.name))
        }
      case protocol: OnymousAudienceChatFromServerProtocol =>
        onymousAudienceChatFromServerAnalysisEngine match {
          case Some(engine: OnymousAudienceChatFromServerAnalysisEngine4Logger) =>
            log(OnymousAudienceChatFromServerAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(OnymousAudienceChatFromServerAnalysisEngine4Logger.name))
        }
      case protocol: OnymousAudienceChatFromClientProtocol =>
        onymousAudienceChatFromClientAnalysisEngine match {
          case Some(engine: OnymousAudienceChatFromClientAnalysisEngine4Logger) =>
            log(OnymousAudienceChatFromClientAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(OnymousAudienceChatFromClientAnalysisEngine4Logger.name))
        }
      case protocol: OnymousAudienceScrollProtocol =>
        onymousAudienceScrollAnalysisEngine match {
          case Some(engine: OnymousAudienceScrollAnalysisEngine4Logger) =>
            log(OnymousAudienceScrollAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(OnymousAudienceScrollAnalysisEngine4Logger.name))
        }
      case protocol: ScrollProtocol =>
        scrollAnalysisEngine match {
          case Some(engine: ScrollAnalysisEngine4Logger) =>
            log(ScrollAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(ScrollAnalysisEngine4Logger.name))
        }
      case protocol: StarProtocol =>
        starAnalysisEngine match {
          case Some(engine: StarAnalysisEngine4Logger) =>
            log(StarAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(StarAnalysisEngine4Logger.name))
        }
      case protocol: VoteProtocol =>
        voteAnalysisEngine match {
          case Some(engine: VoteAnalysisEngine4Logger) =>
            log(VoteAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(VoteAnalysisEngine4Logger.name))
        }
      case protocol: ChatFromServerProtocol =>
        chatFromServerAnalysisEngine match {
          case Some(engine: ChatFromServerAnalysisEngine4Logger) =>
            log(ChatFromServerAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(ChatFromServerAnalysisEngine4Logger.name))
        }
      case protocol: FirstMorningPhaseProtocol =>
        firstMorningPhaseAnalysisEngine match {
          case Some(engine: FirstMorningPhaseAnalysisEngine4Logger) =>
            log(FirstMorningPhaseAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(FirstMorningPhaseAnalysisEngine4Logger.name))
        }
      case protocol: MorningPhaseProtocol =>
        morningPhaseAnalysisEngine match {
          case Some(engine: MorningPhaseAnalysisEngine4Logger) =>
            log(MorningPhaseAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(MorningPhaseAnalysisEngine4Logger.name))
        }
      case protocol: NoonPhaseProtocol =>
        noonPhaseAnalysisEngine match {
          case Some(engine: NoonPhaseAnalysisEngine4Logger) =>
            log(NoonPhaseAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(NoonPhaseAnalysisEngine4Logger.name))
        }
      case protocol: NightPhaseProtocol =>
        nightPhaseAnalysisEngine match {
          case Some(engine: NightPhaseAnalysisEngine4Logger) =>
            log(NightPhaseAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(NightPhaseAnalysisEngine4Logger.name))
        }
      case protocol: PostMortemDiscussionProtocol =>
        postMortemDiscussionAnalysisEngine match {
          case Some(engine: PostMortemDiscussionAnalysisEngine4Logger) =>
            log(PostMortemDiscussionAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(PostMortemDiscussionAnalysisEngine4Logger.name))
        }
      case protocol: FlavorTextProtocol =>
        flavorTextAnalysisEngine match {
          case Some(engine: FlavorTextAnalysisEngine4Logger) =>
            log(FlavorTextAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(FlavorTextAnalysisEngine4Logger.name))
        }
      case protocol: GameResultProtocol =>
        gameResultAnalysisEngine match {
          case Some(engine: GameResultAnalysisEngine4Logger) =>
            log(GameResultAnalysisEngine4Logger.name)
            engine.process(box, protocol)
          case None => Future.failed(new NoEngineException(GameResultAnalysisEngine4Logger.name))
        }
      case _ =>
        Future.failed(new JSON2ProtocolException("No protocol"))
    }
  }
}
