package com.natixistest.playlist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.natixistest.playlist.entity.PlaylistEntity;

public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Long> {
	List<PlaylistEntity> findByUserNameIgnoreCase(String userName);
}
