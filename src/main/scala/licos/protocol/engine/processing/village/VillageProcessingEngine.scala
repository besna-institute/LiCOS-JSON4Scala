package licos.protocol.engine.processing.village

import com.typesafe.scalalogging.Logger
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.{
  AnonymousAudienceChatFromClientProtocol,
  BoardProtocol,
  BuildVillageProtocol,
  ChatFromClientProtocol,
  ErrorFromClientProtocol,
  LeaveWaitingPageProtocol,
  OnymousAudienceBoardProtocol,
  OnymousAudienceChatFromClientProtocol,
  OnymousAudienceScrollProtocol,
  ReadyProtocol,
  ReceivedChatMessageProtocol,
  ReceivedFlavorTextMessageProtocol,
  ReceivedSystemMessageProtocol,
  ScrollProtocol,
  StarProtocol,
  VoteProtocol
}
import licos.protocol.element.village.server2client.{
  AnonymousAudienceChatFromServerProtocol,
  ChatFromServerProtocol,
  ErrorFromServerProtocol,
  FirstMorningPhaseProtocol,
  FlavorTextProtocol,
  GameResultProtocol,
  MorningPhaseProtocol,
  NextGameInvitationIsClosedProtocol,
  NextGameInvitationProtocol,
  NightPhaseProtocol,
  NoonPhaseProtocol,
  OnymousAudienceChatFromServerProtocol,
  PostMortemDiscussionProtocol
}
import licos.protocol.engine.analysis.village.client2server.*
import licos.protocol.engine.analysis.village.server2client.*
import licos.protocol.engine.processing.{JSON2ProtocolException, NoEngineException, ProcessingEngine}

import scala.util.{Failure, Try}

final class VillageProcessingEngine(
    anonymousAudienceChatFromClientAnalysisEngine: Option[AnonymousAudienceChatFromClientAnalysisEngine],
    boardAnalysisEngine:                           Option[BoardAnalysisEngine],
    buildVillageAnalysisEngine:                    Option[BuildVillageAnalysisEngine],
    chatFromClientAnalysisEngine:                  Option[ChatFromClientAnalysisEngine],
    errorFromClientAnalysisEngine:                 Option[ErrorFromClientAnalysisEngine],
    leaveWaitingPageAnalysisEngine:                Option[LeaveWaitingPageAnalysisEngine],
    onymousAudienceBoardAnalysisEngine:            Option[OnymousAudienceBoardAnalysisEngine],
    onymousAudienceChatFromClientAnalysisEngine:   Option[OnymousAudienceChatFromClientAnalysisEngine],
    onymousAudienceScrollAnalysisEngine:           Option[OnymousAudienceScrollAnalysisEngine],
    readyAnalysisEngine:                           Option[ReadyAnalysisEngine],
    receivedChatMessageAnalysisEngine:             Option[ReceivedChatMessageAnalysisEngine],
    receivedFlavorTextMessageAnalysisEngine:       Option[ReceivedFlavorTextMessageAnalysisEngine],
    receivedSystemMessageAnalysisEngine:           Option[ReceivedSystemMessageAnalysisEngine],
    scrollAnalysisEngine:                          Option[ScrollAnalysisEngine],
    starAnalysisEngine:                            Option[StarAnalysisEngine],
    voteAnalysisEngine:                            Option[VoteAnalysisEngine],
    anonymousAudienceChatFromServerAnalysisEngine: Option[AnonymousAudienceChatFromServerAnalysisEngine],
    chatFromServerAnalysisEngine:                  Option[ChatFromServerAnalysisEngine],
    errorFromServerAnalysisEngine:                 Option[ErrorFromServerAnalysisEngine],
    firstMorningPhaseAnalysisEngine:               Option[FirstMorningPhaseAnalysisEngine],
    flavorTextAnalysisEngine:                      Option[FlavorTextAnalysisEngine],
    gameResultAnalysisEngine:                      Option[GameResultAnalysisEngine],
    morningPhaseAnalysisEngine:                    Option[MorningPhaseAnalysisEngine],
    nextGameInvitationAnalysisEngine:              Option[NextGameInvitationAnalysisEngine],
    nextGameInvitationIsClosedAnalysisEngine:      Option[NextGameInvitationIsClosedAnalysisEngine],
    nightPhaseAnalysisEngine:                      Option[NightPhaseAnalysisEngine],
    noonPhaseAnalysisEngine:                       Option[NoonPhaseAnalysisEngine],
    onymousAudienceChatFromServerAnalysisEngine:   Option[OnymousAudienceChatFromServerAnalysisEngine],
    postMortemDiscussionAnalysisEngine:            Option[PostMortemDiscussionAnalysisEngine]
) extends ProcessingEngine {

  private val logger: Logger = Logger[VillageProcessingEngine]

  @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing", "org.wartremover.warts.Overloading"))
  def process(box: VillageBOX, msg: VillageMessageProtocol): Try[VillageMessageProtocol] = {

    def log(label: String): Unit = {
      val format: String = "process %s"
      logger.info(format.format(label))
    }

    msg match {
      case protocol: AnonymousAudienceChatFromServerProtocol =>
        anonymousAudienceChatFromServerAnalysisEngine match {
          case Some(engine: AnonymousAudienceChatFromServerAnalysisEngine) =>
            log(AnonymousAudienceChatFromServerAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(AnonymousAudienceChatFromServerAnalysisEngine.name))
        }
      case protocol: AnonymousAudienceChatFromClientProtocol =>
        anonymousAudienceChatFromClientAnalysisEngine match {
          case Some(engine: AnonymousAudienceChatFromClientAnalysisEngine) =>
            log(AnonymousAudienceChatFromClientAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(AnonymousAudienceChatFromClientAnalysisEngine.name))
        }
      case protocol: BoardProtocol =>
        boardAnalysisEngine match {
          case Some(engine: BoardAnalysisEngine) =>
            log(BoardAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(BoardAnalysisEngine.name))
        }
      case protocol: BuildVillageProtocol =>
        buildVillageAnalysisEngine match {
          case Some(engine: BuildVillageAnalysisEngine) =>
            log(BuildVillageAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(BuildVillageAnalysisEngine.name))
        }
      case protocol: ChatFromClientProtocol =>
        chatFromClientAnalysisEngine match {
          case Some(engine: ChatFromClientAnalysisEngine) =>
            log(ChatFromClientAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(ChatFromClientAnalysisEngine.name))
        }
      case protocol: ErrorFromServerProtocol =>
        errorFromServerAnalysisEngine match {
          case Some(engine: ErrorFromServerAnalysisEngine) =>
            log(ErrorFromServerAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(ErrorFromServerAnalysisEngine.name))
        }
      case protocol: ErrorFromClientProtocol =>
        errorFromClientAnalysisEngine match {
          case Some(engine: ErrorFromClientAnalysisEngine) =>
            log(ErrorFromClientAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(ErrorFromClientAnalysisEngine.name))
        }
      case protocol: LeaveWaitingPageProtocol =>
        leaveWaitingPageAnalysisEngine match {
          case Some(engine: LeaveWaitingPageAnalysisEngine) =>
            log(LeaveWaitingPageAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(LeaveWaitingPageAnalysisEngine.name))
        }
      case protocol: OnymousAudienceBoardProtocol =>
        onymousAudienceBoardAnalysisEngine match {
          case Some(engine: OnymousAudienceBoardAnalysisEngine) =>
            log(OnymousAudienceBoardAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(OnymousAudienceBoardAnalysisEngine.name))
        }
      case protocol: OnymousAudienceChatFromServerProtocol =>
        onymousAudienceChatFromServerAnalysisEngine match {
          case Some(engine: OnymousAudienceChatFromServerAnalysisEngine) =>
            log(OnymousAudienceChatFromServerAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(OnymousAudienceChatFromServerAnalysisEngine.name))
        }
      case protocol: OnymousAudienceChatFromClientProtocol =>
        onymousAudienceChatFromClientAnalysisEngine match {
          case Some(engine: OnymousAudienceChatFromClientAnalysisEngine) =>
            log(OnymousAudienceChatFromClientAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(OnymousAudienceChatFromClientAnalysisEngine.name))
        }
      case protocol: OnymousAudienceScrollProtocol =>
        onymousAudienceScrollAnalysisEngine match {
          case Some(engine: OnymousAudienceScrollAnalysisEngine) =>
            log(OnymousAudienceScrollAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(OnymousAudienceScrollAnalysisEngine.name))
        }
      case protocol: ReadyProtocol =>
        readyAnalysisEngine match {
          case Some(engine: ReadyAnalysisEngine) =>
            log(ReadyAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(ReadyAnalysisEngine.name))
        }
      case protocol: ReceivedChatMessageProtocol =>
        receivedChatMessageAnalysisEngine match {
          case Some(engine: ReceivedChatMessageAnalysisEngine) =>
            log(ReceivedChatMessageAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(ReceivedChatMessageAnalysisEngine.name))
        }
      case protocol: ReceivedFlavorTextMessageProtocol =>
        receivedFlavorTextMessageAnalysisEngine match {
          case Some(engine: ReceivedFlavorTextMessageAnalysisEngine) =>
            log(ReceivedFlavorTextMessageAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(ReceivedFlavorTextMessageAnalysisEngine.name))
        }
      case protocol: ReceivedSystemMessageProtocol =>
        receivedSystemMessageAnalysisEngine match {
          case Some(engine: ReceivedSystemMessageAnalysisEngine) =>
            log(ReceivedSystemMessageAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(ReceivedSystemMessageAnalysisEngine.name))
        }
      case protocol: ScrollProtocol =>
        scrollAnalysisEngine match {
          case Some(engine: ScrollAnalysisEngine) =>
            log(ScrollAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(ScrollAnalysisEngine.name))
        }
      case protocol: StarProtocol =>
        starAnalysisEngine match {
          case Some(engine: StarAnalysisEngine) =>
            log(StarAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(StarAnalysisEngine.name))
        }
      case protocol: VoteProtocol =>
        voteAnalysisEngine match {
          case Some(engine: VoteAnalysisEngine) =>
            log(VoteAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(VoteAnalysisEngine.name))
        }
      case protocol: ChatFromServerProtocol =>
        chatFromServerAnalysisEngine match {
          case Some(engine: ChatFromServerAnalysisEngine) =>
            log(ChatFromServerAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(ChatFromServerAnalysisEngine.name))
        }
      case protocol: FirstMorningPhaseProtocol =>
        firstMorningPhaseAnalysisEngine match {
          case Some(engine: FirstMorningPhaseAnalysisEngine) =>
            log(FirstMorningPhaseAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(FirstMorningPhaseAnalysisEngine.name))
        }
      case protocol: MorningPhaseProtocol =>
        morningPhaseAnalysisEngine match {
          case Some(engine: MorningPhaseAnalysisEngine) =>
            log(MorningPhaseAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(MorningPhaseAnalysisEngine.name))
        }
      case protocol: NoonPhaseProtocol =>
        noonPhaseAnalysisEngine match {
          case Some(engine: NoonPhaseAnalysisEngine) =>
            log(NoonPhaseAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(NoonPhaseAnalysisEngine.name))
        }
      case protocol: NightPhaseProtocol =>
        nightPhaseAnalysisEngine match {
          case Some(engine: NightPhaseAnalysisEngine) =>
            log(NightPhaseAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(NightPhaseAnalysisEngine.name))
        }
      case protocol: PostMortemDiscussionProtocol =>
        postMortemDiscussionAnalysisEngine match {
          case Some(engine: PostMortemDiscussionAnalysisEngine) =>
            log(PostMortemDiscussionAnalysisEngine.name)
            engine.process(box, protocol)
          case None =>
            Failure(new NoEngineException(PostMortemDiscussionAnalysisEngine.name))
        }
      case protocol: FlavorTextProtocol =>
        flavorTextAnalysisEngine match {
          case Some(engine: FlavorTextAnalysisEngine) =>
            log(FlavorTextAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(FlavorTextAnalysisEngine.name))
        }
      case protocol: GameResultProtocol =>
        gameResultAnalysisEngine match {
          case Some(engine: GameResultAnalysisEngine) =>
            log(GameResultAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(GameResultAnalysisEngine.name))
        }
      case protocol: NextGameInvitationProtocol =>
        nextGameInvitationAnalysisEngine match {
          case Some(engine: NextGameInvitationAnalysisEngine) =>
            log(NextGameInvitationAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(NextGameInvitationAnalysisEngine.name))
        }
      case protocol: NextGameInvitationIsClosedProtocol =>
        nextGameInvitationIsClosedAnalysisEngine match {
          case Some(engine: NextGameInvitationIsClosedAnalysisEngine) =>
            log(NextGameInvitationIsClosedAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(NextGameInvitationIsClosedAnalysisEngine.name))
        }
      case _ =>
        Failure(new JSON2ProtocolException("No protocol"))
    }
  }
}
