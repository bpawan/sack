package com.bpawan

import com.bpawan.api.HttpServer
import com.bpawan.api.resource.SongsRoutes
import com.bpawan.api.service.SongService
import com.bpawan.dal.CassandraClient
import com.bpawan.dal.database.SongsDatabase
import com.bpawan.msg.{KafkaProducerSettings, SongsProducer}

object App extends App {

  lazy val songsDatabase = new SongsDatabase(CassandraClient.connector)
  lazy val songsProducer = new SongsProducer(KafkaProducerSettings.producerSettings)

  lazy val songService = new SongService(songsDatabase, songsProducer)

  lazy val songsRouts = new SongsRoutes(songService)

  HttpServer.serve(songsRouts.songRoutes)
}
