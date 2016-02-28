package models

import anorm._
import anorm.SqlParser._

import play.api.libs.json.{Writes, Json}

/**
  * Created by Alex on 17/02/2016.
  */

case class Groupe(idgroupe : Int,  nom : String , annee : Int)

object Groupe{

  implicit val jsonWrites: Writes[Groupe] = Json.writes[Groupe]

  val groupeParser = {
    get[Int]("idgroupe") ~
      get[String]("nom") ~
      get[Int]("annee")  map {
      case idgroupe~nom~année =>
        Groupe(idgroupe,nom,année)
    }
  }

}
