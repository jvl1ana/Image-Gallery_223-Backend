package com.example.demo.domain.imagePost;

import com.example.demo.core.generic.AbstractEntity;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "image_post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ImagePost extends AbstractEntity{

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;

    @Column(name = "likes")
    private Integer likes;

    @ManyToOne
    private User author;

}
