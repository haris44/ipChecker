package controllers

import dao.GroupeDao
import models.{Groupe}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
/**
  * Created by Alex on 17/02/2016.
  */
class GroupeService extends Controller{

  def get (idgroupe : Int) = Action{
    Ok(Json.toJson(GroupeDao.get(idgroupe)))
  }

  def getAll() = Action{
    Ok(Json.toJson(GroupeDao.getAll))
  }

  def insert = Action (parse.json) {
    request => val result = GroupeDao.insert(request.body)
      Ok(result)
  }

}
