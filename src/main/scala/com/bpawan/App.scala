package com.bpawan

import com.bpawan.api.HttpServer
import com.bpawan.api.resource.SongsRoutes
import com.bpawan.api.service.SongService
import com.bpawan.dal.CassandraClient
import com.bpawan.msg.KafkaProducer

object App extends App with SongsRoutes {

  lazy val songService = new SongService(CassandraClient.connector, KafkaProducer.producerSettings)

  HttpServer.serve(songService)
}
