package dao

import anorm._
import models.{User, Ip}

import play.api.db.DB
import play.api.Play.current
import play.api.libs.json.{Json, JsValue}

import scala.util.matching.Regex

object IpDao{

  def getAll = DB.withConnection { implicit c =>
    SQL("SELECT * FROM ip").as(Ip.ipParser* )
  }

  def get (ip : Int) = DB.withConnection { implicit c =>
    SQL("SELECT * FROM ip WHERE ip = {ip}").on('ip -> ip).as(Ip.ipParser*)
  }

  def getMaxbyGroup (groupeId : Int) = DB.withConnection { implicit c =>
    val retour = SQL("SELECT * FROM ip WHERE idgroupe = {groupeid} ORDER BY ip DESC LIMIT 1")
      .on('groupeid -> (groupeId))
      .as(Ip.ipParser*);
    retour(0)
  }

  def insert (value : JsValue) = DB.withConnection { implicit c => {
    val last = getMaxbyGroup((value \ "idgroupe").as[Int]);
    val dernier = last.ip.split('.')(3).toInt + 1
    val newip = "172.16." + (last.idgroupe + 100) + "." + dernier;
    val user = UserDao.insert(value);
    val response = SQL("INSERT INTO ip (ip, iduser, addMac, idgroupe) VALUES ({ip}, {iduser}, {addMac}, {idgroupe})").on(
      'ip -> newip,
      'iduser -> user,
      'addMac -> (value \ "addMac").as[String],
      'idgroupe -> (value \ "idgroupe").as[Long]
    ).executeInsert() ; newip
  }
  }

}

