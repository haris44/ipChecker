package dao

import anorm._
import models.Ip

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
    SQL("SELECT MAX(ip) FROM ip WHERE groupeid = {groupeid}").on('groupeid -> (groupeId)).as(Ip.ipParser*);
  }

  def insert (value : JsValue) = DB.withConnection { implicit c => {
    val last = getMaxbyGroup((value \ "idgroupe ").as[Int]).asInstanceOf[Ip];
    val newip = "172.16." + (last.idgroupe * 100) + "" + last.ip.split('.')(3);
    val userid = UserDao.insert(value).asInstanceOf[Int];
    val response = SQL("INSERT INTO ip (ip, iduser, addMac, idgroupe) VALUES ({ip}, {iduser}, {addMac}, {idgroupe})").on(
      'ip -> newip,
      'iduser -> userid,
      'addMac -> (value \ "addMac ").as[String],
      'idgroupe -> (value \ "idgroupe ").as[Int]
    ).executeInsert() ; newip
  }
  }

}

