package com.example.demo.domain.imagePost.dto;

import com.example.demo.core.generic.AbstractDTO;
import com.example.demo.domain.user.dto.AuthorDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class ImagePostDTO extends AbstractDTO {

    private String url;

    private String description;

    private Set<AuthorDTO> likes;

    private AuthorDTO author;


    public ImagePostDTO(UUID id, String url, String description, AuthorDTO author) {
        super(id);
        this.url = url;
        this.description = description;
        this.author = author;
    }
}
