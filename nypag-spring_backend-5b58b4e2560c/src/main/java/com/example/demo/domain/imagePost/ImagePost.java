package com.example.demo.domain.imagePost;

import com.example.demo.core.generic.AbstractEntity;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "image_post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Log4j2
@Accessors(chain = true)
public class ImagePost extends AbstractEntity {

    @Size(max = 10000, min = 5)
    @Column(name = "url")
    private String url;

    @Size(max = 75, min = 5)
    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_like", joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "user_id"}))
    private Set<User> likes = new HashSet<>();

    @ManyToOne
    private User author;

    @PrePersist
    public void logNewImagePostAttempt(){
        log.info("Attempting to add new image-post");
    }

    @PostPersist
    public void logNewImagePostAdded(){
        log.info("Added image-post");
    }

    @PreRemove
    public void logDeleteImagePostAttempt(){
        log.info("Attempting to delete image-post with id: " + getId());
    }

    @PostRemove
    public void logDeleteImagePostSuccess(){
        log.info("Image-post with id " + getId() + " successfully deleted");
    }

    @PreUpdate
    public void logUpdateImagePostAttempt(){
        log.info("Attempting to update image-post with id: " + getId());
    }

    @PostUpdate
    public void logUpdateImagePostSuccess(){
        log.info("Image-post with id " + getId() + " successfully updated");
    }

}
