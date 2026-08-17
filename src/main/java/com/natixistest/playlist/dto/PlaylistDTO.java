package com.natixistest.playlist.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PlaylistDTO {
    private Long id;
    
    @NotEmpty(message = "Playlist title must not be empty")
    private String title;
    
    @NotEmpty(message = "Playlist user must not be empty")
    private String userName;
    
    private List<SongDTO> songs;
}
