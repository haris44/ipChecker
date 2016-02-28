package models

import anorm._
import anorm.SqlParser._

import play.api.libs.json.{Writes, Json}

/**
  * Created by Alex on 17/02/2016.
  */

case class Ip (val ip : String,  val iduser : Int,  val addMac : String, val idgroupe : Int)

object Ip{

  implicit val jsonWrites: Writes[Ip] = Json.writes[Ip]

  val ipParser = {
    get[String]("ip") ~
      get[Int]("iduser") ~
      get[String]("addMac") ~
      get[Int]("idgroupe") map {
      case ip~iduser~addMac~idgroupe =>
        Ip(ip,iduser,addMac,idgroupe)
    }
  }

}
