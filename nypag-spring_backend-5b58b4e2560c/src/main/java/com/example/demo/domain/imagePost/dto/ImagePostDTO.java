package com.example.demo.domain.imagePost.dto;

import com.example.demo.core.generic.AbstractDTO;
import com.example.demo.domain.user.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class ImagePostDTO extends AbstractDTO {

    private int imagePostId;

    private String url;

    private String description;

    private Set<UserDTO> author;

    public ImagePostDTO(int imagePostId, String url, String description, Set<UserDTO> author) {
        this.imagePostId = imagePostId;
        this.url = url;
        this.description = description;
        this.author = author;
    }
}
