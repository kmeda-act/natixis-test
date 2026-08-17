package com.natixistest.playlist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.natixistest.playlist.dto.PlaylistDTO;
import com.natixistest.playlist.entity.PlaylistEntity;
import com.natixistest.playlist.entity.SongEntity;
import com.natixistest.playlist.exception.ResourceNotFoundException;
import com.natixistest.playlist.mapper.PlaylistMapper;
import com.natixistest.playlist.repository.PlaylistRepository;
import com.natixistest.playlist.repository.SongRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaylistService {
	private final PlaylistRepository playlistRepository;
	private final SongRepository songRepository;
    private final PlaylistMapper playlistMapper;

    public PlaylistDTO create(PlaylistDTO playlistDto) {
        PlaylistEntity playlist = playlistMapper.toEntity(playlistDto);
        PlaylistEntity savedPlaylist = playlistRepository.save(playlist);
        
        return playlistMapper.toDto(savedPlaylist);
    }

    public List<PlaylistDTO> findByUser(String userName) {
        List<PlaylistEntity> playlist = playlistRepository.findByUserNameIgnoreCase(userName);
        
        return playlistMapper.toDtoList(playlist);
    }

    public PlaylistDTO findById(Long id) {
    	PlaylistEntity playlist = playlistRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Playlist not found: " + id));
    	
    	return playlistMapper.toDto(playlist);
    }

    public PlaylistDTO update(PlaylistDTO playlistDto) {
    	PlaylistEntity playlist = playlistRepository.findById(playlistDto.getId())
    		.orElseThrow(() -> new ResourceNotFoundException("Playlist not found: " + playlistDto.getId()));
    	
    	playlistMapper.updateEntity(playlistDto, playlist);

    	PlaylistEntity savedPlaylist = playlistRepository.save(playlist);
    	
        return playlistMapper.toDto(savedPlaylist);
    }

    public void delete(Long id) {
    	PlaylistEntity playlist = playlistRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Playlist not found with id " + id));
    	
        playlistRepository.delete(playlist);
    }
    
    @Transactional
    public PlaylistDTO addSongs(Long playlistId, List<Long> songIds) {

        PlaylistEntity playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        List<SongEntity> songs = songRepository.findAllById(songIds);

        if (songs.size() != songIds.size()) {
            throw new RuntimeException("Some songs not found");
        }

        playlist.getSongs().addAll(
            songs.stream()
                .filter(song -> !playlist.getSongs().contains(song))
                .toList()
        );

        return playlistMapper.toDto(playlist);
    }
    
    @Transactional
    public void removeSong(Long playlistId, Long songId) {

        PlaylistEntity playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        playlist.getSongs().removeIf(song -> song.getId().equals(songId));
    }
}
