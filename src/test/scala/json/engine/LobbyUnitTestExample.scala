package json.engine

abstract class LobbyUnitTestExample(filePath: String) {
  private val baseUrl: String = "https://raw.githubusercontent.com/besna-institute/werewolfworld/gh-pages/lobby/unitTest/0.3/"

  val path: String = baseUrl.concat(filePath)

  val `type`: String
}
