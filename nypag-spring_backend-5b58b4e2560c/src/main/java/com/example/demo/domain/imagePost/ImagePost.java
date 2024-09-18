package com.example.demo.domain.imagePost;

import com.example.demo.core.generic.AbstractEntity;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "image_post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ImagePost extends AbstractEntity {

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;

    /*@Column(name = "likes")
    private Integer likes;*/

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_like", joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "user_id"}))
    private Set<User> likes = new HashSet<>();

    @ManyToOne
    private User author;

}
