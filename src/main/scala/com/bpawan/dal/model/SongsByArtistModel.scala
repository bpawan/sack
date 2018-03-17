package com.bpawan.dal.model

import com.bpawan.dal.entity.Song
import com.outworkers.phantom.dsl._

import scala.concurrent.Future

abstract class SongsByArtistModel extends Table[SongsByArtistModel, Song] {

  override def tableName: String = "songs_by_artist"

  object id extends TimeUUIDColumn with ClusteringOrder {
    override lazy val name = "song_id"
  }

  object artist extends StringColumn with PartitionKey
  object title extends StringColumn
  object album extends StringColumn

  def getByArtist(artist: String): Future[List[Song]] = {
    select
      .where(_.artist eqs artist)
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .fetch()
  }

  def deleteByArtistAndId(artist: String, id: UUID): Future[ResultSet] = {
    delete
      .where(_.artist eqs artist)
      .and(_.id eqs id)
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .future()
  }
}
