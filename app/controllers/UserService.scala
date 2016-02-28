package controllers

import dao.{UserDao, GroupeDao}
import models.{User, Groupe}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
/**
  * Created by Alex on 17/02/2016.
  */
class UserService extends Controller{

  def get (iduser : Int) = Action{
    Ok(Json.toJson(UserDao.get(iduser)))
  }

  def insert = Action (parse.json) {
    request => val result = UserDao.insert(request.body)
      Ok(result)
  }


}
