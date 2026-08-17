package com.natixistest.playlist.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.natixistest.playlist.dto.SongDTO;
import com.natixistest.playlist.entity.SongEntity;

@Mapper(componentModel = "spring")
public interface SongMapper {
	SongEntity toEntity(SongDTO dto);
	SongDTO toDto(SongEntity entity);
	List<SongEntity> toEntityList(List<SongDTO> dtos);
	List<SongDTO> toDtoList(List<SongEntity> entities);
	
	void updateEntity(SongDTO dto, @MappingTarget SongEntity entity);
}
