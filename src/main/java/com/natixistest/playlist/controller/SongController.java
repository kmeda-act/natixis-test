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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.natixistest.playlist.dto.SongDTO;
import com.natixistest.playlist.service.SongService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/song")
public class SongController {
	private final SongService songService;

    @GetMapping
    public ResponseEntity<List<SongDTO>> findSongs(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String artist
    ) {
        List<SongDTO> songs = songService.findSongs(title, artist);
		return ResponseEntity.ok(songs);
    }

    @PostMapping
    public ResponseEntity<SongDTO> addSong(@RequestBody SongDTO dto) {
    	SongDTO song = songService.addSong(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(song);
    }

    @PutMapping
    public ResponseEntity<SongDTO> updateSong(@Valid @RequestBody SongDTO songDto) {
    	SongDTO savedSong = songService.updateSong(songDto);
    	
        return ResponseEntity.ok(savedSong);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);

        return ResponseEntity.noContent().build();
    }
}
