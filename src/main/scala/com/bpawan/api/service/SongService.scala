package com.bpawan.api.service

import akka.actor.ActorSystem
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import com.bpawan.dal.database.SongsDatabase
import com.bpawan.dal.entity.Song
import com.outworkers.phantom.ResultSet
import com.outworkers.phantom.connectors.CassandraConnection
import org.apache.kafka.clients.producer.ProducerRecord

import scala.concurrent.Future

class SongService(override val connector: CassandraConnection, producerSettings: ProducerSettings[Array[Byte], String])
  extends SongsDatabase(connector = connector) {


  def store(song: Song): Future[ResultSet] = {
    implicit val system: ActorSystem = ActorSystem("songs-api-service")
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    val pusehdToKafka = Source
      .single(song)
      .map(_.toString)
      .map(elem =>
        new ProducerRecord[Array[Byte], String]("songs", elem)
      )
      .runWith(Producer.plainSink[Array[Byte], String](producerSettings))


    saveOrUpdate(song)
  }
}
