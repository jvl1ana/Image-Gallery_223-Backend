package com.example.demo.domain.imagePost.dto;

import com.example.demo.core.generic.AbstractDTO;
import com.example.demo.domain.user.dto.AuthorDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class UpdatedImagePostDTO extends AbstractDTO {

    private int imagePostId;

    private String url;

    private String description;

    private int likes;




    public UpdatedImagePostDTO(UUID id, int imagePostId, String url, String description) {
        super(id);
        this.imagePostId = imagePostId;
        this.url = url;
        this.description = description;
    }
}
