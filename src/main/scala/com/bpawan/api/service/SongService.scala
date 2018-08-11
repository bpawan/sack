package com.bpawan.api.service

import com.bpawan.dal.database.SongsDatabase
import com.bpawan.dal.entity.Song
import com.bpawan.msg.SongsProducer
import com.outworkers.phantom.ResultSet

import scala.concurrent.Future

class SongService(songsDatabase: SongsDatabase, songsProducer: SongsProducer)
{
  def store(song: Song): Future[ResultSet] = {
    songsProducer.publishSong(song)
    songsDatabase.saveOrUpdate(song)
  }
}
