package com.bpawan.dal.database

import com.bpawan.dal.entity.Song
import com.bpawan.dal.model.{SongsByArtistModel, SongsModel}
import com.outworkers.phantom.connectors.CassandraConnection
import com.outworkers.phantom.dsl._

import scala.concurrent.Future

class SongsDatabase(override val connector: CassandraConnection) extends Database[SongsDatabase](connector) {
  object SongsModel extends SongsModel with connector.Connector
  object SongsByArtistsModel extends SongsByArtistModel with connector.Connector

  def saveOrUpdate(songs: Song): Future[ResultSet] = {

    Batch.logged
      .add(SongsModel.store(songs))
      .add(SongsByArtistsModel.store(songs))
      .future()
  }

  def delete(song: Song): Future[ResultSet] = {
    Batch.logged
      .add(SongsModel.delete.where(_.id eqs song.id))
      .add(SongsByArtistsModel.delete.where(_.artist eqs song.artist).and(_.id eqs song.id))
      .future()
  }

  def getAll: Future[ResultSet] = {
    SongsModel.getAllSongs
  }
}
