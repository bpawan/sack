package com.bpawan.dal.test.utils

import com.bpawan.dal.entity.Song
import com.datastax.driver.core.utils.UUIDs
import com.outworkers.util.samplers.Sample
import com.outworkers.util.testing.{ShortString, gen}

trait SongsGenerator {
  implicit object SongGenerator extends Sample[Song] {
    override def sample: Song = {
      Song(
        UUIDs.timeBased(),
        gen[ShortString].value,
        album = "Toxicity",
        artist = "System of a Down"
      )
    }
  }
}
