package protocol.engine.auth

import java.nio.charset.StandardCharsets

import com.typesafe.scalalogging.Logger
import licos.json.parser.AuthParser
import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.engine.processing.{
  AuthPE,
  AuthProcessingEngine,
  AuthProcessingEngineFactory,
  SpecificProcessingEngineFactory
}
import org.junit.experimental.theories.{DataPoints, Theories, Theory}
import org.junit.runner.RunWith
import org.scalatest.junit.AssertionsForJUnit
import protocol.element.AuthMessageTestProtocol
import protocol.engine.AuthExample
import protocol.engine.auth.analysis.{
  AuthenticationAndAuthorizationRequestAE,
  AuthenticationRequestResponseAE,
  AuthorizationRequestResponseAE
}
import protocol.engine.auth.example.{
  AuthenticationAndAuthorizationRequest,
  AuthenticationRequestResponse,
  AuthorizationRequestResponse
}

import scala.io.{Codec, Source}
import scala.util.{Failure, Success}

object AuthProcessingEngineSpec {
  @DataPoints
  def exampleSeq: Array[AuthExample] = Array[AuthExample](
    AuthenticationAndAuthorizationRequest("authenticationAndAuthorizationRequest.json"),
    AuthenticationRequestResponse("authenticationRequestResponse.json"),
    AuthorizationRequestResponse("authorizationRequestResponse.json")
  )
}

@RunWith(classOf[Theories])
class AuthProcessingEngineSpec extends AssertionsForJUnit with AuthParser {

  private final val log: Logger = Logger[AuthProcessingEngineSpec]

  private val processingEngineFactory: AuthProcessingEngineFactory = SpecificProcessingEngineFactory
    .create(AuthPE)
    .asInstanceOf[AuthProcessingEngineFactory]
    .set(new AuthenticationAndAuthorizationRequestAE())
    .set(new AuthenticationRequestResponseAE())
    .set(new AuthorizationRequestResponseAE())

  private val processingEngine: AuthProcessingEngine = processingEngineFactory.create

  @Theory
  def process(jsonExample: AuthExample): Unit = {
    val jsonType:       String = jsonExample.`type`
    val url:            String = jsonExample.path
    implicit val codec: Codec  = Codec(StandardCharsets.UTF_8)
    log.info(url)
    val source = Source.fromURL(url)
    val msg: String = source.getLines.mkString("\n")
    source.close()
    log.debug(msg)
    processingEngine.process(new AuthBox(), msg) match {
      case Success(protocol: AuthMessageProtocol) =>
        protocol match {
          case p: AuthMessageTestProtocol =>
            assert(p.text == jsonType)
          case _ =>
            fail(
              Seq[String](
                "No AuthMessageTestProtocol"
              ).mkString("\n")
            )
        }
      case Failure(error: Throwable) =>
        fail(
          Seq[String](
            "No response is generated.",
            error.getMessage,
            msg
          ).mkString("\n")
        )
    }
  }
}
