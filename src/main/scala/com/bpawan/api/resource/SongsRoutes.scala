package com.bpawan.api.resource

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.bpawan.api.serializer.JsonSupport
import com.bpawan.api.service.SongService
import com.bpawan.dal.entity.Song

import scala.util.{Failure, Success}

class SongsRoutes(val songService: SongService) extends JsonSupport{
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