package licos.protocol.engine.processing.lobby

import licos.protocol.engine.analysis.lobby.client2server._
import licos.protocol.engine.analysis.lobby.server2client._
import licos.protocol.engine.analysis.lobby.server2server.PlayedWithTokenAnalysisEngine
import licos.protocol.engine.processing.ProcessingEngineFactory

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading", "org.wartremover.warts.Var"))
final class LobbyProcessingEngineFactory extends ProcessingEngineFactory {
  private var pongAnalysisEngine:                 Option[PongAnalysisEngine]                 = None
  private var pingAnalysisEngine:                 Option[PingAnalysisEngine]                 = None
  private var waitingPageAnalysisEngine:          Option[WaitingPageAnalysisEngine]          = None
  private var lobbyAnalysisEngine:                Option[LobbyAnalysisEngine]                = None
  private var enterLobbyAnalysisEngine:           Option[EnterLobbyAnalysisEngine]           = None
  private var getAvatarInfoAnalysisEngine:        Option[GetAvatarInfoAnalysisEngine]        = None
  private var avatarInfoAnalysisEngine:           Option[AvatarInfoAnalysisEngine]           = None
  private var selectVillageAnalysisEngine:        Option[SelectVillageAnalysisEngine]        = None
  private var leaveWaitingPageAnalysisEngine:     Option[LeaveWaitingPageAnalysisEngine]     = None
  private var kickOutPlayerAnalysisEngine:        Option[KickOutPlayerAnalysisEngine]        = None
  private var buildVillageAnalysisEngine:         Option[BuildVillageAnalysisEngine]         = None
  private var advancedSearchAnalysisEngine:       Option[AdvancedSearchAnalysisEngine]       = None
  private var idSearchAnalysisEngine:             Option[IdSearchAnalysisEngine]             = None
  private var playAnalysisEngine:                 Option[PlayAnalysisEngine]                 = None
  private var playedAnalysisEngine:               Option[PlayedAnalysisEngine]               = None
  private var playedWithTokenAnalysisEngine:      Option[PlayedWithTokenAnalysisEngine]      = None
  private var readyAnalysisEngine:                Option[ReadyAnalysisEngine]                = None
  private var searchResultAnalysisEngine:         Option[SearchResultAnalysisEngine]         = None
  private var changeLangAnalysisEngine:           Option[ChangeLangAnalysisEngine]           = None
  private var changeUserEmailAnalysisEngine:      Option[ChangeUserEmailAnalysisEngine]      = None
  private var changeUserNameAnalysisEngine:       Option[ChangeUserNameAnalysisEngine]       = None
  private var changeUserPasswordAnalysisEngine:   Option[ChangeUserPasswordAnalysisEngine]   = None
  private var getSettingsAnalysisEngine:          Option[GetSettingsAnalysisEngine]          = None
  private var settingsAnalysisEngine:             Option[SettingsAnalysisEngine]             = None
  private var authorizationRequestAnalysisEngine: Option[AuthorizationRequestAnalysisEngine] = None
  private var authorizationRequestAcceptedResponseAnalysisEngine
      : Option[AuthorizationRequestAcceptedResponseAnalysisEngine] = None
  private var authorizationRequestAcceptedAnalysisEngine: Option[AuthorizationRequestAcceptedAnalysisEngine] = None
  private var renewAvatarTokenAnalysisEngine:             Option[RenewAvatarTokenAnalysisEngine]             = None
  private var newAvatarTokenAnalysisEngine:               Option[NewAvatarTokenAnalysisEngine]               = None

  override def create: LobbyProcessingEngine = {
    new LobbyProcessingEngine(
      pongAnalysisEngine,
      pingAnalysisEngine,
      waitingPageAnalysisEngine,
      lobbyAnalysisEngine,
      enterLobbyAnalysisEngine,
      getAvatarInfoAnalysisEngine,
      avatarInfoAnalysisEngine,
      selectVillageAnalysisEngine,
      leaveWaitingPageAnalysisEngine,
      kickOutPlayerAnalysisEngine,
      buildVillageAnalysisEngine,
      advancedSearchAnalysisEngine,
      idSearchAnalysisEngine,
      playAnalysisEngine,
      playedAnalysisEngine,
      playedWithTokenAnalysisEngine,
      readyAnalysisEngine,
      searchResultAnalysisEngine,
      changeLangAnalysisEngine,
      changeUserEmailAnalysisEngine,
      changeUserNameAnalysisEngine,
      changeUserPasswordAnalysisEngine,
      getSettingsAnalysisEngine,
      settingsAnalysisEngine,
      authorizationRequestAnalysisEngine,
      authorizationRequestAcceptedResponseAnalysisEngine,
      authorizationRequestAcceptedAnalysisEngine,
      renewAvatarTokenAnalysisEngine,
      newAvatarTokenAnalysisEngine
    )
  }

  def set(pongAnalysisEngine: PongAnalysisEngine): LobbyProcessingEngineFactory = {
    this.pongAnalysisEngine = Option(pongAnalysisEngine)
    this
  }

  def set(pingAnalysisEngine: PingAnalysisEngine): LobbyProcessingEngineFactory = {
    this.pingAnalysisEngine = Option(pingAnalysisEngine)
    this
  }

  def set(waitingPageAnalysisEngine: WaitingPageAnalysisEngine): LobbyProcessingEngineFactory = {
    this.waitingPageAnalysisEngine = Option(waitingPageAnalysisEngine)
    this
  }

  def set(lobbyAnalysisEngine: LobbyAnalysisEngine): LobbyProcessingEngineFactory = {
    this.lobbyAnalysisEngine = Option(lobbyAnalysisEngine)
    this
  }

  def set(enterLobbyAnalysisEngine: EnterLobbyAnalysisEngine): LobbyProcessingEngineFactory = {
    this.enterLobbyAnalysisEngine = Option(enterLobbyAnalysisEngine)
    this
  }

  def set(getAvatarInfoAnalysisEngine: GetAvatarInfoAnalysisEngine): LobbyProcessingEngineFactory = {
    this.getAvatarInfoAnalysisEngine = Option(getAvatarInfoAnalysisEngine)
    this
  }

  def set(avatarInfoAnalysisEngine: AvatarInfoAnalysisEngine): LobbyProcessingEngineFactory = {
    this.avatarInfoAnalysisEngine = Option(avatarInfoAnalysisEngine)
    this
  }

  def set(selectVillageAnalysisEngine: SelectVillageAnalysisEngine): LobbyProcessingEngineFactory = {
    this.selectVillageAnalysisEngine = Option(selectVillageAnalysisEngine)
    this
  }

  def set(leaveWaitingPageAnalysisEngine: LeaveWaitingPageAnalysisEngine): LobbyProcessingEngineFactory = {
    this.leaveWaitingPageAnalysisEngine = Option(leaveWaitingPageAnalysisEngine)
    this
  }

  def set(kickOutPlayerAnalysisEngine: KickOutPlayerAnalysisEngine): LobbyProcessingEngineFactory = {
    this.kickOutPlayerAnalysisEngine = Option(kickOutPlayerAnalysisEngine)
    this
  }

  def set(buildVillageAnalysisEngine: BuildVillageAnalysisEngine): LobbyProcessingEngineFactory = {
    this.buildVillageAnalysisEngine = Option(buildVillageAnalysisEngine)
    this
  }

  def set(advancedSearchAnalysisEngine: AdvancedSearchAnalysisEngine): LobbyProcessingEngineFactory = {
    this.advancedSearchAnalysisEngine = Option(advancedSearchAnalysisEngine)
    this
  }

  def set(idSearchAnalysisEngine: IdSearchAnalysisEngine): LobbyProcessingEngineFactory = {
    this.idSearchAnalysisEngine = Option(idSearchAnalysisEngine)
    this
  }

  def set(playAnalysisEngine: PlayAnalysisEngine): LobbyProcessingEngineFactory = {
    this.playAnalysisEngine = Option(playAnalysisEngine)
    this
  }

  def set(playedAnalysisEngine: PlayedAnalysisEngine): LobbyProcessingEngineFactory = {
    this.playedAnalysisEngine = Option(playedAnalysisEngine)
    this
  }

  def set(playedWithTokenAnalysisEngine: PlayedWithTokenAnalysisEngine): LobbyProcessingEngineFactory = {
    this.playedWithTokenAnalysisEngine = Option(playedWithTokenAnalysisEngine)
    this
  }

  def set(readyAnalysisEngine: ReadyAnalysisEngine): LobbyProcessingEngineFactory = {
    this.readyAnalysisEngine = Option(readyAnalysisEngine)
    this
  }

  def set(searchResultAnalysisEngine: SearchResultAnalysisEngine): LobbyProcessingEngineFactory = {
    this.searchResultAnalysisEngine = Option(searchResultAnalysisEngine)
    this
  }

  def set(changeLangAnalysisEngine: ChangeLangAnalysisEngine): LobbyProcessingEngineFactory = {
    this.changeLangAnalysisEngine = Option(changeLangAnalysisEngine)
    this
  }

  def set(changeUserEmailAnalysisEngine: ChangeUserEmailAnalysisEngine): LobbyProcessingEngineFactory = {
    this.changeUserEmailAnalysisEngine = Option(changeUserEmailAnalysisEngine)
    this
  }

  def set(changeUserNameAnalysisEngine: ChangeUserNameAnalysisEngine): LobbyProcessingEngineFactory = {
    this.changeUserNameAnalysisEngine = Option(changeUserNameAnalysisEngine)
    this
  }

  def set(changeUserPasswordAnalysisEngine: ChangeUserPasswordAnalysisEngine): LobbyProcessingEngineFactory = {
    this.changeUserPasswordAnalysisEngine = Option(changeUserPasswordAnalysisEngine)
    this
  }

  def set(getSettingsAnalysisEngine: GetSettingsAnalysisEngine): LobbyProcessingEngineFactory = {
    this.getSettingsAnalysisEngine = Option(getSettingsAnalysisEngine)
    this
  }

  def set(settingsAnalysisEngine: SettingsAnalysisEngine): LobbyProcessingEngineFactory = {
    this.settingsAnalysisEngine = Option(settingsAnalysisEngine)
    this
  }

  def set(authorizationRequestAnalysisEngine: AuthorizationRequestAnalysisEngine): LobbyProcessingEngineFactory = {
    this.authorizationRequestAnalysisEngine = Option(authorizationRequestAnalysisEngine)
    this
  }

  def set(
      authorizationRequestAcceptedResponseAnalysisEngine: AuthorizationRequestAcceptedResponseAnalysisEngine
  ): LobbyProcessingEngineFactory = {
    this.authorizationRequestAcceptedResponseAnalysisEngine = Option(authorizationRequestAcceptedResponseAnalysisEngine)
    this
  }

  def set(
      authorizationRequestAcceptedAnalysisEngine: AuthorizationRequestAcceptedAnalysisEngine
  ): LobbyProcessingEngineFactory = {
    this.authorizationRequestAcceptedAnalysisEngine = Option(authorizationRequestAcceptedAnalysisEngine)
    this
  }

  def set(
      renewAvatarTokenAnalysisEngine: RenewAvatarTokenAnalysisEngine
  ): LobbyProcessingEngineFactory = {
    this.renewAvatarTokenAnalysisEngine = Option(renewAvatarTokenAnalysisEngine)
    this
  }

  def set(newAvatarTokenAnalysisEngine: NewAvatarTokenAnalysisEngine): LobbyProcessingEngineFactory = {
    this.newAvatarTokenAnalysisEngine = Option(newAvatarTokenAnalysisEngine)
    this
  }
}
