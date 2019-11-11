package licos.json.validation.village

import licos.{LiCOSOnline, WerewolfWorld}
import play.api.libs.json.Reads
import play.api.libs.json.Reads.pattern

object StarValidation {
  private val label: String        = "star"
  val `@context`:    Reads[String] = pattern(WerewolfWorld.context(label).r)
  val `@id`:         Reads[String] = pattern(LiCOSOnline.state(label).r)
}
