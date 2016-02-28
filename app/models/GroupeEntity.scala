package models

import anorm._
import anorm.SqlParser._

import play.api.libs.json.{Writes, Json}

/**
  * Created by Alex on 17/02/2016.
  */

case class Groupe(idgroupe : Int,  nom : String , année : String,  groupeid : String)

object Groupe{

  implicit val jsonWrites: Writes[Groupe] = Json.writes[Groupe]

  val groupeParser = {
    get[Int]("idgroupe") ~
      get[String]("nom") ~
      get[String]("année") ~
      get[String]("groupeid") map {
      case idgroupe~nom~année~groupeid =>
        Groupe(idgroupe,nom,année,groupeid)
    }
  }

}
