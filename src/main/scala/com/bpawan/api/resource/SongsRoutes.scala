package com.bpawan.api.resource

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import com.bpawan.api.serializer.JsonSupport
import com.bpawan.api.service.SongService
import com.bpawan.dal.entity.Song

import scala.util.{Failure, Success}

trait SongsRoutes extends JsonSupport {
  val songService: SongService

  def songRoutes: Route = {
    pathPrefix("song") {
      (post & entity(as[Song])) { song =>
        onComplete(songService.store(song)) {
          case Success(_) => complete(StatusCodes.Created)
          case Failure(exception) => complete(exception.getMessage)
        }
      }
    }
  }
}
