package json.engine

abstract class VillageUnitTestExample(filePath: String) {
  private val baseUrl: String =
    "https://raw.githubusercontent.com/besna-institute/werewolfworld/gh-pages/village/unitTest/0.3/"

  val path: String = baseUrl.concat(filePath)

  val `type`: String
}
