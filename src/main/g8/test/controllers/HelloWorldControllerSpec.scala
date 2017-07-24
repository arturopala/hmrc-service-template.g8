package controllers

import connectors.BackendConnector
import org.scalatest.BeforeAndAfterEach
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Configuration
import play.api.i18n.MessagesApi
import play.api.test.FakeRequest
import play.api.test.Helpers._

class HelloWorldControllerSpec extends PlaySpec with MockitoSugar with GuiceOneAppPerSuite with BeforeAndAfterEach {

  val messagesApi: MessagesApi = app.injector.instanceOf[MessagesApi]
  val mockConfig: Configuration = app.injector.instanceOf[Configuration]
  val backendConnector: BackendConnector = mock[BackendConnector]

  val mockHelloWorldController = new HelloWorldController(messagesApi, backendConnector, mockConfig)

  "HelloWorldController" should {
    "return Status: OK Body: empty" in {
      val response = mockHelloWorldController.helloWorld()(FakeRequest("GET", "/hello-world"))

      status(response) mustBe OK
      contentType(response).get mustBe HTML
      contentAsString(response) must include(messagesApi("app.name"))
    }
  }
}


