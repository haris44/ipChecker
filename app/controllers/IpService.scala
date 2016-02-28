package controllers

import dao.{IpDao}
import play.api.libs.json.Json
import play.api.mvc._
/**
  * Created by Alex on 17/02/2016.
  */
class IpService extends Controller{

  def get (ip : Int) = Action{
    Ok(Json.toJson(IpDao.get(ip)))
  }

  def getAll () = Action{
    Ok(Json.toJson(IpDao.getAll))
  }

  def insert = Action (parse.json) {
    request => val result = IpDao.insert(request.body)
      Ok(result)
  }


}
