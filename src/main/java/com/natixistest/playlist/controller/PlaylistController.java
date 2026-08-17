package com.natixistest.playlist.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.natixistest.playlist.dto.PlaylistDTO;
import com.natixistest.playlist.dto.SongDTO;
import com.natixistest.playlist.enums.ShuffleType;
import com.natixistest.playlist.service.PlaylistService;
import com.natixistest.playlist.service.ShuffleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor
public class PlaylistController {
	private final PlaylistService playlistService;
	private final ShuffleService shuffleService;

	@GetMapping
	public List<PlaylistDTO> findPlaylist(String userName) {
		return playlistService.findByUser(userName);
	}

	@PostMapping
    public ResponseEntity<PlaylistDTO> create(@Valid @RequestBody PlaylistDTO playlistDto) {
        PlaylistDTO savedPlaylist = playlistService.create(playlistDto);
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedPlaylist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaylistDTO> update(@Valid @RequestBody PlaylistDTO playlistDto) {
    	PlaylistDTO savedPlaylist = playlistService.update(playlistDto);

    	return ResponseEntity
    			.ok(savedPlaylist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        playlistService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{playlistId}/songs")
    public ResponseEntity<PlaylistDTO> addSongs(
            @PathVariable Long playlistId,
            @Valid @RequestBody List<Long> songIds) {

        return ResponseEntity.ok(
            playlistService.addSongs(
                playlistId,
                songIds
            )
        );
    }
    
    @DeleteMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<Void> removeSong(
            @PathVariable Long playlistId,
            @PathVariable Long songId) {

        playlistService.removeSong(playlistId, songId);

        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{playlistId}/shuffle/{shuffleType}")
    public ResponseEntity<List<SongDTO>> shuffle(
            @PathVariable Long playlistId,
            @PathVariable ShuffleType shuffleType) {

        PlaylistDTO playlist =
                playlistService.findById(playlistId);

        List<SongDTO> shuffledSongs =
                shuffleService.shuffle(
                        playlist.getSongs(),
                        shuffleType
                );

        return ResponseEntity.ok(shuffledSongs);
    }
}
