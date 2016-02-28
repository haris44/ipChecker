package dao

import anorm._
import models.{Ip, User}

import play.api.db.DB
import play.api.Play.current
import anorm.SqlParser.str
import play.api.libs.json
import play.api.libs.json.{Json, JsValue}

object UserDao{

  def getAll = DB.withConnection { implicit c =>
    SQL("SELECT * FROM user").as(User.userParser* )
  }

  def get (id : Int) = DB.withConnection { implicit c =>
    SQL("SELECT * FROM user WHERE iduser = {id}").on('id -> id).as(User.userParser*)
  }

  def getByName (nom : String, prenom : String) = DB.withConnection { implicit c =>
    SQL("SELECT * FROM user WHERE nom = {nom} AND prenom = {prenom}").on('prenom -> prenom, 'nom -> nom).as(User.userParser*)
  }


  def insert (value : JsValue) : Long = DB.withConnection { implicit c =>
    val existUser = getByName((value \ "nom").as[String].toLowerCase(), (value \ "prenom").as[String].toLowerCase())
    var response : Long = 0
    if(existUser.nonEmpty) {
      response = existUser(0).iduser;
    }
    else {
      val rep: Option[Long]  = SQL("INSERT INTO user (titre, nom, prenom, idgroupe) VALUES ({titre}, {nom}, {prenom}, {idgroupe})").on(
        'titre -> 0,
        'nom -> (value \ "nom").as[String].toLowerCase(),
        'prenom ->(value \ "prenom").as[String].toLowerCase(),
        'idgroupe ->(value \ "idgroupe").as[Int]
      ).executeInsert();
    response = rep.get;
    }
    response
  }

}

