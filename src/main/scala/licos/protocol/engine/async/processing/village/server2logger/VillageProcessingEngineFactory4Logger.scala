package licos.protocol.engine.async.processing.village.server2logger

import licos.protocol.engine.async.analysis.village.client2server.server2logger.*
import licos.protocol.engine.async.analysis.village.server2client.server2logger.*
import licos.protocol.engine.async.processing.ProcessingEngineFactory

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading", "org.wartremover.warts.Var"))
final class VillageProcessingEngineFactory4Logger extends ProcessingEngineFactory {

  private var anonymousAudienceChatFromClientAnalysisEngine
      : Option[AnonymousAudienceChatFromClientAnalysisEngine4Logger] =
    Option.empty[AnonymousAudienceChatFromClientAnalysisEngine4Logger]
  private var boardAnalysisEngine: Option[BoardAnalysisEngine4Logger] = Option.empty[BoardAnalysisEngine4Logger]
  private var chatFromClientAnalysisEngine: Option[ChatFromClientAnalysisEngine4Logger] =
    Option.empty[ChatFromClientAnalysisEngine4Logger]
  private var errorFromClientAnalysisEngine: Option[ErrorFromClientAnalysisEngine4Logger] =
    Option.empty[ErrorFromClientAnalysisEngine4Logger]
  private var onymousAudienceBoardAnalysisEngine: Option[OnymousAudienceBoardAnalysisEngine4Logger] =
    Option.empty[OnymousAudienceBoardAnalysisEngine4Logger]
  private var onymousAudienceChatFromClientAnalysisEngine: Option[OnymousAudienceChatFromClientAnalysisEngine4Logger] =
    Option.empty[OnymousAudienceChatFromClientAnalysisEngine4Logger]
  private var onymousAudienceScrollAnalysisEngine: Option[OnymousAudienceScrollAnalysisEngine4Logger] =
    Option.empty[OnymousAudienceScrollAnalysisEngine4Logger]
  private var scrollAnalysisEngine: Option[ScrollAnalysisEngine4Logger] = Option.empty[ScrollAnalysisEngine4Logger]
  private var starAnalysisEngine:   Option[StarAnalysisEngine4Logger]   = Option.empty[StarAnalysisEngine4Logger]
  private var voteAnalysisEngine:   Option[VoteAnalysisEngine4Logger]   = Option.empty[VoteAnalysisEngine4Logger]
  private var anonymousAudienceChatFromServerAnalysisEngine
      : Option[AnonymousAudienceChatFromServerAnalysisEngine4Logger] =
    Option.empty[AnonymousAudienceChatFromServerAnalysisEngine4Logger]
  private var chatFromServerAnalysisEngine: Option[ChatFromServerAnalysisEngine4Logger] =
    Option.empty[ChatFromServerAnalysisEngine4Logger]
  private var errorFromServerAnalysisEngine: Option[ErrorFromServerAnalysisEngine4Logger] =
    Option.empty[ErrorFromServerAnalysisEngine4Logger]
  private var firstMorningPhaseAnalysisEngine: Option[FirstMorningPhaseAnalysisEngine4Logger] =
    Option.empty[FirstMorningPhaseAnalysisEngine4Logger]
  private var flavorTextAnalysisEngine: Option[FlavorTextAnalysisEngine4Logger] =
    Option.empty[FlavorTextAnalysisEngine4Logger]
  private var gameResultAnalysisEngine: Option[GameResultAnalysisEngine4Logger] =
    Option.empty[GameResultAnalysisEngine4Logger]
  private var morningPhaseAnalysisEngine: Option[MorningPhaseAnalysisEngine4Logger] =
    Option.empty[MorningPhaseAnalysisEngine4Logger]
  private var nightPhaseAnalysisEngine: Option[NightPhaseAnalysisEngine4Logger] =
    Option.empty[NightPhaseAnalysisEngine4Logger]
  private var noonPhaseAnalysisEngine: Option[NoonPhaseAnalysisEngine4Logger] =
    Option.empty[NoonPhaseAnalysisEngine4Logger]
  private var onymousAudienceChatFromServerAnalysisEngine: Option[OnymousAudienceChatFromServerAnalysisEngine4Logger] =
    Option.empty[OnymousAudienceChatFromServerAnalysisEngine4Logger]
  private var postMortemDiscussionAnalysisEngine: Option[PostMortemDiscussionAnalysisEngine4Logger] =
    Option.empty[PostMortemDiscussionAnalysisEngine4Logger]

  override def create: VillageProcessingEngine4Logger = {
    new VillageProcessingEngine4Logger(
      anonymousAudienceChatFromClientAnalysisEngine,
      boardAnalysisEngine,
      chatFromClientAnalysisEngine,
      errorFromClientAnalysisEngine,
      onymousAudienceBoardAnalysisEngine,
      onymousAudienceChatFromClientAnalysisEngine,
      onymousAudienceScrollAnalysisEngine,
      scrollAnalysisEngine,
      starAnalysisEngine,
      voteAnalysisEngine,
      anonymousAudienceChatFromServerAnalysisEngine,
      chatFromServerAnalysisEngine,
      errorFromServerAnalysisEngine,
      firstMorningPhaseAnalysisEngine,
      flavorTextAnalysisEngine,
      gameResultAnalysisEngine,
      morningPhaseAnalysisEngine,
      nightPhaseAnalysisEngine,
      noonPhaseAnalysisEngine,
      onymousAudienceChatFromServerAnalysisEngine,
      postMortemDiscussionAnalysisEngine
    )
  }

  def set(
      anonymousAudienceChatFromClientAnalysisEngine: AnonymousAudienceChatFromClientAnalysisEngine4Logger
  ): VillageProcessingEngineFactory4Logger = {
    this.anonymousAudienceChatFromClientAnalysisEngine = Option(anonymousAudienceChatFromClientAnalysisEngine)
    this
  }

  def set(boardAnalysisEngine: BoardAnalysisEngine4Logger): VillageProcessingEngineFactory4Logger = {
    this.boardAnalysisEngine = Option(boardAnalysisEngine)
    this
  }

  def set(chatFromClientAnalysisEngine: ChatFromClientAnalysisEngine4Logger): VillageProcessingEngineFactory4Logger = {
    this.chatFromClientAnalysisEngine = Option(chatFromClientAnalysisEngine)
    this
  }

  def set(
      errorFromClientAnalysisEngine: ErrorFromClientAnalysisEngine4Logger
  ): VillageProcessingEngineFactory4Logger = {
    this.errorFromClientAnalysisEngine = Option(errorFromClientAnalysisEngine)
    this
  }

  def set(
      onymousAudienceBoardAnalysisEngine: OnymousAudienceBoardAnalysisEngine4Logger
  ): VillageProcessingEngineFactory4Logger = {
    this.onymousAudienceBoardAnalysisEngine = Option(onymousAudienceBoardAnalysisEngine)
    this
  }

  def set(
      onymousAudienceChatFromClientAnalysisEngine: OnymousAudienceChatFromClientAnalysisEngine4Logger
  ): VillageProcessingEngineFactory4Logger = {
    this.onymousAudienceChatFromClientAnalysisEngine = Option(onymousAudienceChatFromClientAnalysisEngine)
    this
  }

  def set(
      onymousAudienceScrollAnalysisEngine: OnymousAudienceScrollAnalysisEngine4Logger
  ): VillageProcessingEngineFactory4Logger = {
    this.onymousAudienceScrollAnalysisEngine = Option(onymousAudienceScrollAnalysisEngine)
    this
  }

  def set(scrollAnalysisEngine: ScrollAnalysisEngine4Logger): VillageProcessingEngineFactory4Logger = {
    this.scrollAnalysisEngine = Option(scrollAnalysisEngine)
    this
  }

  def set(starAnalysisEngine: StarAnalysisEngine4Logger): VillageProcessingEngineFactory4Logger = {
    this.starAnalysisEngine = Option(starAnalysisEngine)
    this
  }

  def set(voteAnalysisEngine: VoteAnalysisEngine4Logger): VillageProcessingEngineFactory4Logger = {
    this.voteAnalysisEngine = Option(voteAnalysisEngine)
    this
  }

  def set(
      anonymousAudienceChatFromServerAnalysisEngine: AnonymousAudienceChatFromServerAnalysisEngine4Logger
  ): VillageProcessingEngineFactory4Logger = {
    this.anonymousAudienceChatFromServerAnalysisEngine = Option(anonymousAudienceChatFromServerAnalysisEngine)
    this
  }

  def set(chatFromServerAnalysisEngine: ChatFromServerAnalysisEngine4Logger): VillageProcessingEngineFactory4Logger = {
    this.chatFromServerAnalysisEngine = Option(chatFromServerAnalysisEngine)
    this
  }

  def set(
      errorFromServerAnalysisEngine: ErrorFromServerAnalysisEngine4Logger
  ): VillageProcessingEngineFactory4Logger = {
    this.errorFromServerAnalysisEngine = Option(errorFromServerAnalysisEngine)
    this
  }

  def set(
      firstMorningPhaseAnalysisEngine: FirstMorningPhaseAnalysisEngine4Logger
  ): VillageProcessingEngineFactory4Logger = {
    this.firstMorningPhaseAnalysisEngine = Option(firstMorningPhaseAnalysisEngine)
    this
  }

  def set(flavorTextAnalysisEngine: FlavorTextAnalysisEngine4Logger): VillageProcessingEngineFactory4Logger = {
    this.flavorTextAnalysisEngine = Option(flavorTextAnalysisEngine)
    this
  }

  def set(gameResultAnalysisEngine: GameResultAnalysisEngine4Logger): VillageProcessingEngineFactory4Logger = {
    this.gameResultAnalysisEngine = Option(gameResultAnalysisEngine)
    this
  }

  def set(morningPhaseAnalysisEngine: MorningPhaseAnalysisEngine4Logger): VillageProcessingEngineFactory4Logger = {
    this.morningPhaseAnalysisEngine = Option(morningPhaseAnalysisEngine)
    this
  }

  def set(nightPhaseAnalysisEngine: NightPhaseAnalysisEngine4Logger): VillageProcessingEngineFactory4Logger = {
    this.nightPhaseAnalysisEngine = Option(nightPhaseAnalysisEngine)
    this
  }

  def set(noonPhaseAnalysisEngine: NoonPhaseAnalysisEngine4Logger): VillageProcessingEngineFactory4Logger = {
    this.noonPhaseAnalysisEngine = Option(noonPhaseAnalysisEngine)
    this
  }

  def set(
      onymousAudienceChatFromServerAnalysisEngine: OnymousAudienceChatFromServerAnalysisEngine4Logger
  ): VillageProcessingEngineFactory4Logger = {
    this.onymousAudienceChatFromServerAnalysisEngine = Option(onymousAudienceChatFromServerAnalysisEngine)
    this
  }

  def set(
      postMortemDiscussionAnalysisEngine: PostMortemDiscussionAnalysisEngine4Logger
  ): VillageProcessingEngineFactory4Logger = {
    this.postMortemDiscussionAnalysisEngine = Option(postMortemDiscussionAnalysisEngine)
    this
  }
}
