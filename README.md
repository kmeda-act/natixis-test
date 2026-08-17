## API Testing

The API requests used for testing are available in playlist-requests.json.

The application runs on:

http://localhost:8080

The file data.sql adds some values to the song table, so you can start using the playlist requests without needing to add the songs first

### Songs

GET /song
POST /song
PUT /song/{id}
DELETE /song/{id}

### Playlists

GET /playlist?userName={userName}
POST /playlist
PUT /playlist/{id}
DELETE /playlist/{id}

### Playlist Songs

POST /playlists/{playlistId}/songs
DELETE /playlists/{playlistId}/songs/{songId}

### Shuffle

GET /playlists/{playlistId}/shuffle/RANDOM
GET /playlists/{playlistId}/shuffle/GENRE
GET /playlists/{playlistId}/shuffle/SMART