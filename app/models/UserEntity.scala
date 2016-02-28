package models

import anorm._
import anorm.SqlParser._

import play.api.libs.json.{Writes, Json}

/**
  * Created by Alex on 17/02/2016.
  */

case class User (iduser : Int,  nom : String , prenom : String,  titre : String, idgroupe : Int)

object User{

  implicit val jsonWrites: Writes[User] = Json.writes[User]

  val userParser = {
    get[Int]("iduser") ~
      get[String]("nom") ~
      get[String]("prenom") ~
      get[String]("titre") ~
      get[Int]("idgroupe") map {
      case iduser~nom~prenom~titre~idgroupe =>
        User(iduser,nom,prenom,titre,idgroupe)
    }
  }

}
