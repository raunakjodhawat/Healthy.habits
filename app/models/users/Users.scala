package models.users

import com.twitter.finagle.Postgres
import com.twitter.finagle.postgres.PostgresClientImpl
import com.twitter.util.Await

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
      pendingOTP: PendingOTPVerification,
      client: PostgresClientImpl
  ): Boolean = {
    val create = Await.result {
      client.execute("select * from pendingotpverification")
    }
    println(create)
    false
  }
}
