package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._


import models.User


class Application extends Controller {

var userForm: Form[User] = Form {
    mapping(
      "email" -> nonEmptyText,
      "password" ->nonEmptyText,
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "phonenumber" -> nonEmptyText
       )(User.apply)(User.unapply)
  }
var userDelete: Form[User] = Form {
    mapping(
      "email" -> nonEmptyText,
      "password" ->nonEmptyText,
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "phonenumber" -> nonEmptyText
       )(User.apply)(User.unapply)
  }


	def index = Action { implicit request =>
		
		Ok(views.html.index("Your new application is ready."))
	}

	
	def registerUser = Action { implicit request =>

		Ok(views.html.register.registerUser(userForm))
	}

	def usersList = Action { implicit request =>
		
		val users = User.findAll
		Ok(views.html.register.userList(users,userDelete))

		}
	
	def addUser = Action { implicit request =>
		
		def values =  userForm.bind(userForm.bindFromRequest.data).get
		val int = User.add(values)
		
		Redirect(routes.Application.usersList)
	}

	
	def deleteUser = Action { implicit request =>
		
		def values =  userDelete.bind(userDelete.bindFromRequest.data).get
		 val int = User.delete(values)		
		val users = User.findAll
		
		Redirect(routes.Application.usersList)
	}	

	
}
