package models.users

case class Users(
    phoneNumber: Long,
    secretPhrase: Option[String],
    secretQuestion: Option[String],
    secretAnswer: Option[String]
) {}
