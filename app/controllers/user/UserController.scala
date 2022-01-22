package controllers.user

import models.users.Users

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
      NoContent
  }

}
