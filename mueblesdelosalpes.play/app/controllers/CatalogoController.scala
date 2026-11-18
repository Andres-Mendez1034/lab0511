package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._

@Singleton
class CatalogoController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  private val catalogo = Json.arr(
    Json.obj("referencia" -> 1, "nombre" -> "Silla clásica", "descripcion" -> "Una confortable silla con estilo del siglo XIX.", "tipo" -> "Interior", "precio" -> 123.0, "imagen" -> "sillaClasica", "cantidad" -> 45, "seleccion" -> false),
    Json.obj("referencia" -> 2, "nombre" -> "Sillón new wave", "descripcion" -> "Innovador y cómodo.", "tipo" -> "Interior", "precio" -> 5655.0, "imagen" -> "newWave", "cantidad" -> 60, "seleccion" -> false),
    Json.obj("referencia" -> 3, "nombre" -> "Silla moderna", "descripcion" -> "Lo último en la moda de interiores.", "tipo" -> "Interior", "precio" -> 5464.0, "imagen" -> "sillaModerna", "cantidad" -> 50, "seleccion" -> false),
    Json.obj("referencia" -> 4, "nombre" -> "Mesa de jardín", "descripcion" -> "Una bella mesa para exteriores.", "tipo" -> "Exterior", "precio" -> 4568.0, "imagen" -> "mesaJardin", "cantidad" -> 100, "seleccion" -> false)
  )

  def muebles = Action {
    Ok(catalogo).as("application/json")
  }

  def agregar = Action(parse.json) { request =>
    val referencia = (request.body \ "referencia").asOpt[Long]
    referencia match {
      case Some(_) => Created(request.body).as("application/json")
      case None => BadRequest(Json.obj("error" -> "Falta referencia"))
    }
  }

  def agregarAlCarrito = Action(parse.json) { request =>
    Created(request.body).as("application/json")
  }
}
