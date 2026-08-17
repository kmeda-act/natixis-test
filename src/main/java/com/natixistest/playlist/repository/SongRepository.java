package com.natixistest.playlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.natixistest.playlist.entity.SongEntity;

import java.util.List;

public interface SongRepository extends JpaRepository<SongEntity, Long> {

    List<SongEntity> findByTitleAndArtistIgnoreCase(String title, String artist);

    List<SongEntity> findByTitleIgnoreCase(String title);

    List<SongEntity> findByArtistIgnoreCase(String artist);
}
