package com.bpawan.msg

import akka.actor.ActorSystem
import akka.kafka.ProducerSettings
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.kafka.common.serialization.{ByteArraySerializer, StringSerializer}

object KafkaProducerSettings {
  val system: ActorSystem = ActorSystem.create("messaging-system")

  val config: Config = ConfigFactory.load()

  lazy val producerSettings: ProducerSettings[Array[Byte], String] = createProducerSetting(
    config.getString("kafka.host"),
    config.getInt("kafka.port")
  )

  def createProducerSetting(kafkaHost: String, kafkaPort: Int): ProducerSettings[Array[Byte], String] = {
    ProducerSettings(system, new ByteArraySerializer, new StringSerializer)
      .withBootstrapServers(s"$kafkaHost:${kafkaPort.toString}")
  }
}
