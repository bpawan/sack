package com.bpawan.dal.model

import java.util.UUID

import com.bpawan.dal.entity.Song
import com.outworkers.phantom.dsl._

import scala.concurrent.Future

abstract class SongsModel extends Table[SongsModel, Song] {

  override def tableName: String = "songs"

  object id extends TimeUUIDColumn with PartitionKey {
    override lazy val name = "song_id"
  }

  object artist extends StringColumn
  object title extends StringColumn
  object album extends StringColumn

  def getBySongId(id: UUID): Future[Option[Song]] = {
    select
      .where(_.id eqs id)
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .one()
  }

  def getAllSongs: Future[ResultSet] = {
    select
      .all
      .future
  }

  def deleteById(id: UUID): Future[ResultSet] = {
    delete
      .where(_.id eqs id)
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .future()
  }
}