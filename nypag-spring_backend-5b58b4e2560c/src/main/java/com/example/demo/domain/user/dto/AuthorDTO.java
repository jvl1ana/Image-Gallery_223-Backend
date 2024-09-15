package com.example.demo.domain.user.dto;

import com.example.demo.core.generic.AbstractDTO;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class AuthorDTO extends AbstractDTO {

    private String firstName;

    private String lastName;


    public AuthorDTO(UUID id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;

    }
}
