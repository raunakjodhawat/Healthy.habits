package controllers.user

import com.twitter.finagle.Postgres
import models.users.PendingOTPVerification.initiateUserCreation
import models.users.{PendingOTPVerification, Users}

import javax.inject._
import play.api.mvc._
import play.api.libs.json._

import scala.collection.mutable.ListBuffer

/** Contains all User related pre-checks
  * @param controllerComponents
  */
@Singleton
class UserController @Inject() (val controllerComponents: ControllerComponents)
    extends BaseController {

  private val allUsers = new ListBuffer[Users]()

  allUsers.append(Users(123456999, None, None, None))
  allUsers.append(Users(987645211, None, None, None))
  implicit val result = Json.format[Users]
  implicit val pendingOTPReads = Json.reads[PendingOTPVerification]
  val client = Postgres
    .Client()
    .withCredentials("postgres", Some("password"))
    .database("healthyHabitsPostgres")
    .withSessionPool
    .maxSize(1)
    .withBinaryResults(true)
    .withBinaryParams(true)
    .withTransport
    .tls("localhost")
    .newRichClient("localhost:5430")

  /** Returns list of all users, only if the incoming username and password match
    * @return List of all users from the database
    */
  def getAllUsers(): Action[AnyContent] = Action {
    implicit request: Request[AnyContent] =>
      Ok(Json.toJson(allUsers))
  }

  def getUserById(phoneNumber: Long): Action[AnyContent] = Action {
    implicit request: Request[AnyContent] =>
      allUsers.find(_.phoneNumber == phoneNumber) match {
        case Some(x) => Ok(Json.toJson(x))
        case _       => NoContent
      }
  }

  /** Check if user is already present
    *  if no: initiate registration process
    *     - ask for phone number and country code
    *      - send randomly generated OTP to phone number, and save OTP + phone number + country Code in pendingOTPVerification
    *        - Ask for OTP or option to re-send OTP
    * @return
    */
  def createUser(): Action[AnyContent] = Action {
    implicit request: Request[AnyContent] =>
      val body: AnyContent = request.body
      println(body.asJson)
      NoContent
  }

  /** Checks if there's any entry with the selected phone number and country code in the database
    * If yes, Send a SMS to the phone number with OTP
    * else, generates a random otp
    *      Put phone number, country code and otp in pendingOTPVerification
    *      Send SMS to the phone number with OTP
    *
    * Required Parameter in request body: phoneNumber: String(10), countryCode: String(max 6)
    * @return 204 if OTP is successfully generated, otherwise 400
    */
  def initiateCreateUser(): Action[AnyContent] = Action {
    implicit request: Request[AnyContent] =>
      request.body.asJson
        .map { json =>
          Json.fromJson[PendingOTPVerification](json) match {
            case JsSuccess(value, _) => {
              println(value)
              initiateUserCreation(value, client)
              Ok("Request created")
            }
            case _ => BadRequest
          }
        }
        .getOrElse {
          BadRequest
        }
  }
}
