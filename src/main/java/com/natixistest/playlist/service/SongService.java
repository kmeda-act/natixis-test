package com.natixistest.playlist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.natixistest.playlist.dto.SongDTO;
import com.natixistest.playlist.entity.SongEntity;
import com.natixistest.playlist.exception.ResourceNotFoundException;
import com.natixistest.playlist.mapper.SongMapper;
import com.natixistest.playlist.repository.SongRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SongService {
	private final SongRepository songRepository;
    private final SongMapper songMapper;

    public List<SongDTO> findSongs(String title, String artist) {
    	List<SongEntity> songEntity = null;
    	
	    if (title != null && artist != null) {
	    	songEntity = songRepository.findByTitleAndArtistIgnoreCase(title, artist);
	    }else if (title != null) {
	    	songEntity = songRepository.findByTitleIgnoreCase(title);
	    }else if (artist != null) {
	    	songEntity = songRepository.findByArtistIgnoreCase(artist);
	    }else {
	    	songEntity = songRepository.findAll();
	    }

	    return songMapper.toDtoList(songEntity);
    }

    
    public SongDTO addSong(SongDTO dto) {
        SongEntity song = songMapper.toEntity(dto);
        SongEntity savedSong = songRepository.save(song);
        
        return songMapper.toDto(savedSong);
    }

    public SongDTO updateSong(SongDTO songDto) {
        SongEntity song = songRepository.findById(songDto.getId())
        		.orElseThrow(() -> new ResourceNotFoundException("Song not found: " + songDto.getTitle()));

        songMapper.updateEntity(songDto, song);
        
        SongEntity savedSong = songRepository.save(song);

        return songMapper.toDto(savedSong);
    }

    public void deleteSong(Long id) {
        SongEntity song = songRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Song not found for id: " + id));

        songRepository.delete(song);
    }
}
