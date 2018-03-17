package com.bpawan.api

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.bpawan.App.songRoutes
import com.bpawan.api.service.SongService
import com.typesafe.config.{Config, ConfigFactory}

import scala.concurrent.{ExecutionContextExecutor, Future}

object HttpServer {
  val config: Config = ConfigFactory.load()

  def serve(songService: SongService): Future[Http.ServerBinding] = startServer(
    config.getString("http.host"),
    config.getInt("http.port")
  )

  def startServer(host: String, port: Int): Future[Http.ServerBinding] = {
    implicit val system: ActorSystem = ActorSystem("songs-api-service")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    val routes: Route = songRoutes

    println(s"Starting server at http://$host:$port")
    Http().bindAndHandle(handler = routes, interface = host, port = port)
  }
}
