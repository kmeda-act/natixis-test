package com.natixistest.playlist.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SongDTO {
	private Long id;

    @NotEmpty(message = "Song title must not be empty")
    private String title;

    @NotEmpty(message = "Song artist must not be empty")
    private String artist;

    @NotEmpty(message = "Song album must not be empty")
    private String album;

    @NotEmpty(message = "Song genre must not be empty")
    private String genre;
}
