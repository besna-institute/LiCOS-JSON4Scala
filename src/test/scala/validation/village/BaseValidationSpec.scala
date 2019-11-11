package validation.village

import licos.json.validation.village.BaseValidation
import org.scalatest.{FlatSpec, Matchers}

class BaseValidationSpec extends FlatSpec with Matchers {
  "Timestamp" should "fully matches the regex of timestamp" in {
    "2006-10-07T12:06:56.568+09:00" should fullyMatch regex BaseValidation.timestampRegex
  }
}
