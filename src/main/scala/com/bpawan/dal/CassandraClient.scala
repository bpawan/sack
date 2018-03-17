package com.bpawan.dal

import com.outworkers.phantom.connectors.{CassandraConnection, ContactPoints}
import com.typesafe.config.{Config, ConfigFactory}

import scala.collection.JavaConverters._
import scala.collection.mutable

object CassandraClient {
  val config: Config = ConfigFactory.load()

  lazy val connector: CassandraConnection = createConnector(
    config.getStringList("cassandra.host").asScala,
    config.getString("cassandra.keyspace")
  )

  def createConnector(hosts: mutable.Buffer[String], keyspace: String): CassandraConnection = {
    ContactPoints(hosts).keySpace(keyspace)
  }
}
