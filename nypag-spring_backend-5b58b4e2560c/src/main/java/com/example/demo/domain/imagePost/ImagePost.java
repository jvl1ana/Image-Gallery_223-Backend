package com.example.demo.domain.imagePost;

import com.example.demo.core.generic.AbstractEntity;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "image_post")
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ImagePost extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imagePostId;

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;

    @Column(name = "like")
    private int like;


    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    public ImagePost(int imagePostId, String url, String description, int like, User author) {
        this.imagePostId = imagePostId;
        this.url = url;
        this.description = description;
        this.like = like;
        this.author = author;
    }

}
