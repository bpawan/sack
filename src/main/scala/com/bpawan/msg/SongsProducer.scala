package com.bpawan.msg

import akka.actor.ActorSystem
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import com.bpawan.dal.entity.Song
import org.apache.kafka.clients.producer.ProducerRecord

class SongsProducer(producerSettings: ProducerSettings[Array[Byte], String]) {
  implicit val system: ActorSystem = ActorSystem("songs-api-service")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  def publishSong(song: Song): Unit = {
    Source
      .single(song)
      .map(_.toString)
      .map(elem =>
        new ProducerRecord[Array[Byte], String]("songs", elem)
      )
      .runWith(Producer.plainSink[Array[Byte], String](producerSettings))
  }
}
