package com.natixistest.playlist.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.natixistest.playlist.dto.SongDTO;
import com.natixistest.playlist.enums.ShuffleType;

@Component
public class ShuffleService {
	public List<SongDTO> shuffle(List<SongDTO> musicList, ShuffleType shuffleType) {
		switch(shuffleType) {
		case RANDOM -> Collections.shuffle(musicList);
		case GENRE -> genreShuffle(musicList);
		case SMART -> smartShuffle(musicList);
		}
		
		return musicList;
	}

	private List<SongDTO> genreShuffle(List<SongDTO> musicList) {
		Map<String, List<SongDTO>> genresGroup = 
				musicList.stream().collect(Collectors.groupingBy(SongDTO::getGenre));
		
		return fieldShuffle(genresGroup);
	}

	private List<SongDTO> smartShuffle(List<SongDTO> musicList) {
		Map<String, List<SongDTO>> genresGroup = 
				musicList.stream().collect(Collectors.groupingBy(SongDTO::getArtist));
		
		return fieldShuffle(genresGroup);
	}
	
	private List<SongDTO> fieldShuffle(Map<String, List<SongDTO>> group) {
		List<Entry<String, List<SongDTO>>> genresList = new ArrayList<>(group.entrySet());
		
		genresList.sort(Comparator.comparingInt(entry -> entry.getValue().size()));
		
		List<SongDTO> newMusicList = new ArrayList<SongDTO>();
		
		while(!genresList.isEmpty()) {
			Iterator<Entry<String, List<SongDTO>>> iterator = genresList.iterator();
			
			while(iterator.hasNext()) {
				Entry<String, List<SongDTO>> entry = iterator.next();
				
				List<SongDTO> musics = entry.getValue();
				
				newMusicList.add(musics.remove(0));
				
				if(musics.isEmpty()) {
					iterator.remove();
				}
			}
			
		}
		
		return newMusicList;
	}

}
