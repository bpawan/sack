package com.bpawan.dal.entity

import java.util.UUID

case class Song(
                 id: UUID,
                 title: String,
                 album: String,
                 artist: String
               )
