package dao

import anorm._
import models.{Groupe}

import play.api.db.DB
import play.api.Play.current
import play.api.libs.json.{Json, JsValue}

object GroupeDao{

  def getAll = DB.withConnection { implicit c =>
    SQL("SELECT * FROM groupe").as(Groupe.groupeParser* )
  }

  def get (idgroupe : Int) = DB.withConnection { implicit c =>
    SQL("SELECT * FROM groupe WHERE idgroupe = {idgroupe}").on('idgroupe -> idgroupe).as(Groupe.groupeParser*)
  }

  def insert (value : JsValue) = DB.withConnection { implicit c =>
    val response = SQL("INSERT INTO groupe (nom, annee, right) VALUES ({nom}, {annee}, {right})").on(
      'nom -> (value \ "nom").as[String],
      'annee -> (value \ "annee").as[Int],
      'right -> (value \ "right").as[Int]
    ).executeInsert() ; Json.toJson(response)
  }

}

