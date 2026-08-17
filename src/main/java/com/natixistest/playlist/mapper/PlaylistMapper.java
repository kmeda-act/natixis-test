package com.natixistest.playlist.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.natixistest.playlist.dto.PlaylistDTO;
import com.natixistest.playlist.entity.PlaylistEntity;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
	PlaylistEntity toEntity(PlaylistDTO dto);
	PlaylistDTO toDto(PlaylistEntity entity);
	List<PlaylistEntity> toEntityList(List<PlaylistDTO> dtos);
	List<PlaylistDTO> toDtoList(List<PlaylistEntity> entities);
	
	void updateEntity(PlaylistDTO dto, @MappingTarget PlaylistEntity entity);
}
