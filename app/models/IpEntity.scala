package models

import anorm._
import anorm.SqlParser._

import play.api.libs.json.{Writes, Json}

/**
  * Created by Alex on 17/02/2016.
  */

case class Ip (val ip : String,  val user : String , val iduser : Int,  val addMac : String, val idgroupe : Int)

object Ip{

  implicit val jsonWrites: Writes[Ip] = Json.writes[Ip]

  val ipParser = {
    get[String]("ip") ~
      get[String]("user") ~
      get[Int]("iduser") ~
      get[String]("addMac") ~
      get[Int]("idgroupe") map {
      case ip~user~iduser~addMac~idgroupe =>
        Ip(ip,user,iduser,addMac,idgroupe)
    }
  }

}
