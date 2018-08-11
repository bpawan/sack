CREATE KEYSPACE songs_db WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};
CREATE TABLE songs_db.songs_by_artist (song_id UUID PRIMARY KEY, title text, album text, artist text);
CREATE TABLE songs_db.songs (song_id UUID PRIMARY KEY, title text, album text, artist text);