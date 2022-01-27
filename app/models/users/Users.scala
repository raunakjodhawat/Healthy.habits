package models.users
import scala.slick.driver.PostgresDriver.simple._
case class Users(
    phoneNumber: Long,
    secretPhrase: Option[String],
    secretQuestion: Option[String],
    secretAnswer: Option[String]
)

case class PendingOTPVerification(
    phoneNumber: String,
    countryCode: String
)

case object PendingOTPVerification {
  protected def checkIfUserExists(): Unit = {}
  def initiateUserCreation(
      pendingOTP: PendingOTPVerification
  ): Boolean = {

    println("hello")
    false
  }
}
